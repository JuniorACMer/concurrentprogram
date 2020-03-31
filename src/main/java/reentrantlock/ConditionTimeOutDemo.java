package reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Spark
 */
public class ConditionTimeOutDemo {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static class T extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + ",start");
                boolean flag = condition.await(2, TimeUnit.SECONDS);
                System.out.println("等待超时返回标志：" + flag);
                System.out.println(System.currentTimeMillis() + "," + this.getName() + ",end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        t.setName("线程T");
        t.start();
    }
}
