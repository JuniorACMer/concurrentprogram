package reentrantlock.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class WaitingForFood {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static class T extends Thread {
        int sleepTime;

        public T(String name, int sleepTime) {
            super(name);
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(this.sleepTime);
                long startTime = System.currentTimeMillis();
                cyclicBarrier.await();
                long endTime = System.currentTimeMillis();
                System.out.println(this.getName() + "--休眠了" + (this.sleepTime) + "--又等待了--" + (endTime - startTime) + "ms--开始吃饭了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new T("同事"+i,i).start();
        }
    }
}
