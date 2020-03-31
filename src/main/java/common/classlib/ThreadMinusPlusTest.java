package common.classlib;

/**
 * @author Spark
 */
class Resource {
    private int number = 0;
    private boolean flag = false;

    public synchronized void add() {
        if (this.flag == false) {
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.number++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("【" + Thread.currentThread().getName() + "】执行加法操作，操作结果为：" + this.number);
        this.flag = false;
        super.notify();
    }

    public synchronized void sub() {
        if (this.flag == true) {
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.number--;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("【" + Thread.currentThread().getName() + "】执行减法操作，操作结果为：" + this.number);
        this.flag = true;
        super.notify();
    }
}

public class ThreadMinusPlusTest {
    public static void main(String[] args) {
        Resource resource = new Resource();
        for (int x = 0; x < 6; x++) {
            if (x % 2 == 0) {
                new Thread(() -> {
                    for (int y = 0; y < 50; y++) {
                        synchronized (resource){
                            resource.sub();
                        }
                    }
                }
                        , "减法线程-" + x).start();
            } else {
                new Thread(() -> {
                    for (int y = 0; y < 50; y++) {
                        synchronized (resource){
                            resource.add();
                        }
                    }
                }, "加法线程-" + x).start();
            }
        }
    }
}
