package reentrantlock;

/**
 * @author Spark
 */
public class ThreadSafeDemo {
    private static volatile int number = 0;

    public static synchronized void add() {
        number++;
    }

    public static class T1 extends Thread {
        @Override
        public void run() {
            for(int i  = 0;i< 10000;i++){
                ThreadSafeDemo.add();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new T1();
        Thread t2 = new T1();
        Thread t3 = new T1();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(ThreadSafeDemo.number);
    }
}
