package reentrantlock;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Spark
 */
public class BlockingQueueDemo<E> {
    int size;
    ReentrantLock lock = new ReentrantLock();
    LinkedList<E> list = new LinkedList<>();
    Condition notFull = lock.newCondition(); //队列满时的等待条件
    Condition notEmpty = lock.newCondition(); //队列空时的等待条件

    public BlockingQueueDemo(int size) {
        this.size = size;
    }

    public void enqueue(E e) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == size) {
                notFull.await();
            }
            list.add(e);
            System.out.println("入队:" + e);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
    public E dequeue() throws InterruptedException{
        E e;
        lock.lock();
        try{
            while (list.size() == 0 ) {
                notEmpty.await();
            }
            e = list.removeFirst();
            System.out.println("出队:"+e);
            notFull.signal();
            return e;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockingQueueDemo<Integer> queue = new BlockingQueueDemo<>(1);//当长度为1的时候，为同步阻塞队列。
        for (int i = 0; i < 10; i++) {
            int data = i;
            new Thread(()->{
                try{
                    queue.enqueue(data);
//                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    queue.dequeue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
