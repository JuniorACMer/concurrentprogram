package reentrantlock.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class SemaphoreMultiReleaseCorrect {
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
                semaphore.acquire();
                acquireSuccess = true;
                System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----获取许可！");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----运行结束！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (acquireSuccess) {
                    semaphore.release();
                    System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----释放许可成功！");
                }else{
                    System.out.println(System.currentTimeMillis() + "----" + this.getName() + "----无法释放许可！(压根没有成功获得锁)");
                }
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
