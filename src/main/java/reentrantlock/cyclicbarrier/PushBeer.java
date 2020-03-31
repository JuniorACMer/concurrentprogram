package reentrantlock.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class PushBeer {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10,()->{
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("不好意思，让大家久等了，在下"+Thread.currentThread().getName()+"给大家斟酒赔罪！");
    });
    public static class T extends Thread{
        int sleep;
        public T(String name , int sleep){
            super(name);
            this.sleep = sleep;
        }

        @Override
        public void run() {
            try {
                Long start = System.currentTimeMillis();
                TimeUnit.SECONDS.sleep(sleep);
                cyclicBarrier.await();
                Long end = System.currentTimeMillis();
                System.out.println(this.getName()+"休眠了，"+sleep+"ms"+"-----等待了"+(end-start)+"ms，开始吃饭了！");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new T("同学"+i,i).start();
        }
    }
}
