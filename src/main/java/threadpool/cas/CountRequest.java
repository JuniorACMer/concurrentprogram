package threadpool.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 * 1. 获取count的值，记做A：A=count
 * 2. 将A的值+1，得到B：B = A+1
 * 3. 让B赋值给count：count = B
 * <p>
 * 1. 获取锁
 * 2. 第三步获取一下count最新的值，记做LV
 * 3. 判断LV是否等于A，如果相等，则将B的值赋给count，并返回true，否者返回false
 * 4. 释放锁
 */
public class CountRequest {
    volatile static int count = 0;

    public static int getCount() {
        return count;
    }

    public static void request() throws InterruptedException {
        TimeUnit.MICROSECONDS.sleep(5);
        int exceptCount;
        do {
            exceptCount = getCount();
        } while (!compareAndSwap(exceptCount, exceptCount + 1));
    }

    public static synchronized boolean compareAndSwap(int expectCount, int newCount) {
        if (getCount() == expectCount) {
            count = newCount;
            return true;
        }
        return false;
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
        System.out.println(Thread.currentThread().getName() + "--耗时--" + (end - start) + ",count=" + count);
    }
}
