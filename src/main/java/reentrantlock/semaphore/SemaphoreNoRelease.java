package reentrantlock.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class SemaphoreNoRelease {
    private static Semaphore semaphore = new Semaphore(5);

    public static class T extends Thread {
        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----获取许可！");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----运行结束！");
                System.out.println("当前剩余的许可证数目：" + semaphore.availablePermits());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                new T("t-" + i).start();
            }
        }
    }
}
