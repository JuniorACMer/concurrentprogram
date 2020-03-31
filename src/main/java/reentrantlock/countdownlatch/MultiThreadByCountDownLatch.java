package reentrantlock.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class MultiThreadByCountDownLatch {
    public static class T extends Thread {
        int sleepTime;
        CountDownLatch countDownLatch;

        public T(String name, int sleepTime, CountDownLatch countDownLatch) {
            super(name);
            this.sleepTime = sleepTime;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            System.out.println("线程--" + this.getName() + "--start!");
            try {
                TimeUnit.SECONDS.sleep(this.sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            Long endTime = System.currentTimeMillis();
            System.out.println("线程--" + this.getName() + "--执行结束,执行时间：" + (endTime - startTime));
        }
    }

    public static void main(String[] args) throws InterruptedException{
        System.out.println("线程--" + Thread.currentThread().getName() + "--start!");
        CountDownLatch countDownLatch = new CountDownLatch(2);
        long startTime = System.currentTimeMillis();
        T aaa = new T("AAA", 2, countDownLatch);
        aaa.start();
        T bbb = new T("BBB", 5, countDownLatch);
        bbb.start();
        countDownLatch.await();
        System.out.println("线程--" + Thread.currentThread().getName() + "--end!");
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行总耗时：" + (endTime - startTime));

    }
}
