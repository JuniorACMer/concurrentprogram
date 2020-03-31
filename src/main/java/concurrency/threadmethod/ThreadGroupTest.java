package concurrency.threadmethod;

import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class ThreadGroupTest {
    public static class T1 implements Runnable {

        public void run() {
            System.out.println("线程名称：" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("thread-group-1");

        Thread t1 = new Thread(threadGroup, new T1(), "thread-01");
        Thread t2 = new Thread(threadGroup, new T1(), "thread-02");
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("活动线程数：" + threadGroup.activeCount());
        System.out.println("活动线程组：" + threadGroup.activeGroupCount());
        System.out.println("线程组名称：" + threadGroup.getName());
        System.out.println("父线程的名称："+threadGroup.getParent().getName());
    }
}
