package reentrantlock.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class SemaphoreMultiReleaseWrong {
    private static Semaphore semaphore = new Semaphore(1);

    public static class T extends Thread {
        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----准备获取！");
                semaphore.acquire();
                System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----获取许可！");
                TimeUnit.SECONDS.sleep(100);
                System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----运行结束！");
                System.out.println("当前剩余的许可证数目：" + semaphore.availablePermits());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----释放许可！");
            }
            System.out.println("当前剩余的许可证数目：" + semaphore.availablePermits());
        }

        public static void main(String[] args) throws InterruptedException {
            T t1 = new T("t1");
            t1.start();
            T t2 = new T("t2");
            t2.start();
            TimeUnit.SECONDS.sleep(1);
            T t3 = new T("t3");
            t3.start();
            t2.interrupt();
            t3.interrupt();
        }
    }
}
