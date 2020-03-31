package threadpool.schedulethreadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class ScheduleFunctionDemo {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(()->{
            System.out.println(System.currentTimeMillis()+"开始执行");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis()+"结束执行");
        },5, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
    }
}
