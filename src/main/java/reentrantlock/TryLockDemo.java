package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Spark
 */
public class TryLockDemo {
    private static ReentrantLock lock = new ReentrantLock(false);
    public static class T extends Thread{
        public T(String name){
            super(name);
        }

        @Override
        public void run() {
            try{
                System.out.println(System.currentTimeMillis()+this.getName()+"准备获得锁！");
                if(lock.tryLock()){
                    System.out.println(System.currentTimeMillis()+this.getName()+"获得锁！");
                }
                System.currentTimeMillis();
            } catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
