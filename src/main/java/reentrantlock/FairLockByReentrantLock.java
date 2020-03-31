package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Spark
 */
public class FairLockByReentrantLock {
    private static int num = 0;
    private static ReentrantLock fairLock = new ReentrantLock(true);

    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                fairLock.lock();
                try {
                    System.out.println(this.getName() + "获得锁！");
                } finally {
                    fairLock.unlock();
                    System.out.println(this.getName()+"释放锁！");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new T1("t1");
        Thread t2 = new T1("t2");
        Thread t3 = new T1("t3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

//        System.out.println(ReentrantLockDemo.number);
    }
}
