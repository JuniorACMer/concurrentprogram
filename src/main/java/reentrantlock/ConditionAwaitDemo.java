package reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Spark
 */
public class ConditionAwaitDemo {
    private static ReentrantLock lock = new ReentrantLock(false);
    private static Condition condition = lock.newCondition();

    public static class T extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                System.out.println("3、中断标志：" + this.isInterrupted());
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.setName("t");
        t.start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("1、中断标志：" + t.isInterrupted());
        t.interrupt();
        System.out.println("2、中断标志：" + t.isInterrupted());
    }
}
