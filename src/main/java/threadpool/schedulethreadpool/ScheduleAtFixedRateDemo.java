package threadpool.schedulethreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Spark
 */
public class ScheduleAtFixedRateDemo {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
        //任务执行计数器
        AtomicInteger count = new AtomicInteger(1);
        executor.scheduleAtFixedRate(() -> {
            int currentCount = count.incrementAndGet();
            System.out.println(Thread.currentThread().getName());
            System.out.println("第" + currentCount + "次开始执行");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

}
