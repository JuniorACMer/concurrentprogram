package reentrantlock.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class WaitingForFood2 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static class T extends Thread {
        int sleepTime;

        public T(String name, int sleepTime) {
            super(name);
            this.sleepTime = sleepTime;
        }

        void eat() {
            try {
                TimeUnit.SECONDS.sleep(this.sleepTime);
                long startTime = System.currentTimeMillis();
                cyclicBarrier.await();
                long endTime = System.currentTimeMillis();
                System.out.println(this.getName() + "--休眠了" + (this.sleepTime) + "--又等待了--" + (endTime - startTime) + "ms--开始吃饭了");
                TimeUnit.SECONDS.sleep(this.sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        void drive() {
            try {
                long startTime = System.currentTimeMillis();
                cyclicBarrier.await();
                long endTime = System.currentTimeMillis();
                System.out.println(this.getName() + "--休眠了" + (this.sleepTime) + "--又等待了--" + (endTime - startTime) + "ms--去下一景点的路上。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            eat();
            drive();
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new T("同事" + i, i).start();
        }
    }
}
