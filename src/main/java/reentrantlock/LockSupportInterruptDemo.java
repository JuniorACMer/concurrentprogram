package reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Spark
 */
public class LockSupportInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",start!");
            System.out.println("线程park()之前的中断标志位：" + Thread.currentThread().isInterrupted());
            LockSupport.park();
            System.out.println("线程park()之后的中断标志位：" + Thread.currentThread().isInterrupted());
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",被唤醒!");
        });
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        thread.interrupt();
        System.out.println(System.currentTimeMillis() + " lock-support 执行完毕！!");
    }
}
