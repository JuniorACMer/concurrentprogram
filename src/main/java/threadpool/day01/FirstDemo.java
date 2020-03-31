package threadpool.day01;

import java.util.concurrent.*;

/**
 * @author Spark
 */
public class FirstDemo {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(3,
            5,
            10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int j = i ;
            String taskName = "任务"+j;
            executor.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前线程："+Thread.currentThread().getName()+"--"+taskName+"--处理完毕");
            });
        }
        executor.shutdown();
    }
}
