package reentrantlock;

import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class WaitAndNotify {
    static Object lock = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + this.getName() + "准备获得锁!");
            synchronized (lock) {
                System.out.println(System.currentTimeMillis() + this.getName() + "获得锁成功！");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(System.currentTimeMillis() + this.getName() + "释放锁成功！");
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + this.getName() + "准备获得锁!");
            synchronized (lock) {
                System.out.println(System.currentTimeMillis() + this.getName() + "获得锁成功！");
                lock.notify();
                System.out.println(System.currentTimeMillis() + this.getName() + "Notify!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + this.getName() + "准备释放锁！");
            }
            System.out.println(System.currentTimeMillis() + this.getName() + "释放锁成功！");
        }
    }

    public static void main(String[] args) throws Exception {
        T1 t1 = new T1();
        t1.setName("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        T2 t2 = new T2();
        t2.setName("t2");
        t2.start();
    }
}
