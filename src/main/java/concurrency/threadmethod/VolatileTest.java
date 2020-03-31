package concurrency.threadmethod;

/**
 * @author Spark
 */
public class VolatileTest {
    public volatile static boolean  flag = true;
    public static class T1 extends Thread{
        public T1(String name){
            super(name);
        }

        @Override
        public void run() {
            System.out.println("线程:"+this.getName()+" in");
            while (flag){
               System.out.println("*********************"); ;
            }
            System.out.println("线程:"+this.getName()+" stop");
        }
    }
    public static void main(String[] args) throws Exception{
        new T1("AAAAA").start();
        Thread.sleep(50);
        flag = false;
        new Thread();
    }
}
