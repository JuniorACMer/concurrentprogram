package reentrantlock.countdownlatch;

import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class MultiThreadByJoin {
    public static class T extends Thread {
        int sleepTime;

        public T(String name, int sleepTime) {
            super(name);
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            try {
                TimeUnit.SECONDS.sleep(this.sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("线程--" + this.getName() + "--处理结束耗时：" + (endTime - startTime));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        T aaa = new T("AAA", 2);
        aaa.start();
        T bbb = new T("BBB", 5);
        bbb.start();
        aaa.join();
        bbb.join();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行总耗时：" + (endTime - startTime));
    }
}
