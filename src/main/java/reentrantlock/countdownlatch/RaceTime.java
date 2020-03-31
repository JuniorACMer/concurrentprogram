package reentrantlock.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Spark
 */
public class RaceTime {
    public static class T extends Thread {
        int runCostTime;
        private CountDownLatch commanderCd;
        private CountDownLatch countDownLatch;

        public T(String name, int runCostTime, CountDownLatch commanderCd, CountDownLatch countDownLatch) {
            super(name);
            this.runCostTime = runCostTime;
            this.commanderCd = commanderCd;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                commanderCd.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long startTime = System.currentTimeMillis();
            System.out.println(this.getName() + "开始跑...");
            try {
                TimeUnit.SECONDS.sleep(this.runCostTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(this.getName() + "跑完全程花费了：" + (endTime - startTime));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "开始运行...");
        CountDownLatch commanderCd = new CountDownLatch(1);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        long startTime = System.currentTimeMillis();
        new T("小明", 5, commanderCd, countDownLatch).start();
        new T("小红", 10, commanderCd, countDownLatch).start();
        new T("小刚", 15, commanderCd, countDownLatch).start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("枪响了，比赛开始！");
        commanderCd.countDown();
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("比赛结束的时间：" + (endTime - startTime));
    }
}
