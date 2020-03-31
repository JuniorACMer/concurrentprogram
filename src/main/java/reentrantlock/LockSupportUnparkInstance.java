package reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Spark
 */
public class LockSupportUnparkInstance {
    static class BlockDemo {

    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            LockSupport.park();
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            LockSupport.park(new BlockDemo());
        });
        thread2.start();
    }
}
