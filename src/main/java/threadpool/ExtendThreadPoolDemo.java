package threadpool;

import java.util.concurrent.*;

/**
 * @author Spark
 */
public class ExtendThreadPoolDemo {
    static class Task implements Runnable {
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
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                (r) -> {
                    Thread thread = new Thread(r);
                    System.out.println("当前线程：" + Thread.currentThread().getName());
                    return thread;
                },
                (r,e) -> {
                    System.out.println("无法处理的线程："+r.toString());
                }){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(t.getName()+"--开始执行任务--"+r.toString());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(Thread.currentThread().getName()+"--任务--"+r.toString()+"执行完毕");
            }

            @Override
            protected void terminated() {
                System.out.println(Thread.currentThread().getName()+"关闭线程池！");
            }
        };
        for (int i = 0; i < 10; i++) {
            executor.execute(new Task("任务"+i));
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
