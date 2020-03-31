package reentrantlock.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class SemaphoreTimeout {
    private static Semaphore semaphore = new Semaphore(1);

    public static class T extends Thread {
        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            boolean acquireSuccess = false;
            try {
                System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----准备获取！");
                acquireSuccess = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                if (acquireSuccess) {
                    System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----获取许可！" + "可用许可证：" + semaphore.availablePermits());
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----获取失败！" + "可用许可证：" + semaphore.availablePermits());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (acquireSuccess) {
                    semaphore.release();
                    System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----释放许可证成功！！"+ "可用许可证：" + semaphore.availablePermits());
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            T t1 = new T("t1");
            t1.start();
            TimeUnit.SECONDS.sleep(1);
            T t2 = new T("t2");
            t2.start();
            TimeUnit.SECONDS.sleep(1);
            T t3 = new T("t3");
            t3.start();
        }
    }
}
