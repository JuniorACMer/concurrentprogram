package threadpool.day01;

import java.util.concurrent.*;

/**
 * @author Spark
 */
public class UserDefinedSaturationPolicy {
    static class Task implements Runnable{
        String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        TimeUnit unit;
        BlockingQueue workQueue;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2,60L ,TimeUnit.SECONDS ,
                new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                (r,e)->{
                    System.out.println("无法处理的线程"+r.toString());
                });
        for (int i = 0; i < 10; i++) {
            executor.execute(new Task("task"+i));
        }
        executor.shutdown();
    }
}
