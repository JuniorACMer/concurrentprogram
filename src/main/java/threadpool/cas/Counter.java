package threadpool.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Spark
 */
public class Counter {
    static AtomicInteger count = new AtomicInteger();

    public static void request() throws InterruptedException {
        TimeUnit.MICROSECONDS.sleep(5);
        count.incrementAndGet();
    }


    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "--耗时--" + (end - start));
    }
}
