package threadpool.day01;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Spark
 */
public class UserDefinedThreadFactory {
    static AtomicInteger threadNum = new AtomicInteger(1);
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("自定义线程" + threadNum.getAndIncrement());
                    return thread;
                });
        for (int i = 0; i < 5; i++) {
            String taskName = "任务-"+i;
            executor.execute(()->{
                System.out.println(Thread.currentThread().getName()+"--处理--"+taskName);
            });
        }
        executor.shutdown();
    }
}
