package reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Spark
 */
public class ConditionDemo {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    public static class T1 extends Thread{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+","+this.getName()+"准备获得锁");
            lock.lock();
            try{
                System.out.println(System.currentTimeMillis()+","+this.getName()+"获得锁成功");
                condition.await();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            System.out.println(System.currentTimeMillis()+","+this.getName()+"释放锁成功");
        }
    }
    public static class T2 extends Thread{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+","+this.getName()+"准备获得锁");
            lock.lock();
            try{
                System.out.println(System.currentTimeMillis()+","+this.getName()+"获得锁成功");
                condition.signal();
            }catch (Exception e){
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+","+this.getName()+"准备锁成功");
            }finally {
                lock.unlock();
            }
            System.out.println(System.currentTimeMillis()+","+this.getName()+"释放锁成功");
        }
    }

    public static void main(String[] args) throws  Exception{
        WaitAndNotify.T1 t1 = new WaitAndNotify.T1();
        t1.setName("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        WaitAndNotify.T2 t2 = new WaitAndNotify.T2();
        t2.setName("t2");
        t2.start();
    }
}
