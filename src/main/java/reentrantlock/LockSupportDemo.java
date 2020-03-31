package reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Spark
 */
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException{
        Thread thread = new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
           System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getName()+",start!");
           LockSupport.park();
            System.out.println(System.currentTimeMillis()+","+Thread.currentThread().getName()+",被唤醒!");
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        LockSupport.unpark(thread);
        System.out.println(System.currentTimeMillis()+" lock-support 执行完毕！!");
    }
}
