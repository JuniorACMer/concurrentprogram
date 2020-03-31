package threadpool.day01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 50 ; i++) {
            int j= i;
            String taskName = "Task-"+j;
            executor.execute(()->{
                System.out.println(Thread.currentThread().getName()+"--处理--"+taskName);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
