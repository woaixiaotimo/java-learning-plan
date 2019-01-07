package test;


/**
 * @Auther: what
 * @Date: 2019/1/3 10:03
 * @Description:
 */
public class Die {
    public static void main(String[] args) throws InterruptedException {
        final String lock1 = new String("lock1");
        final String lock2 = new String("lock2");

        Thread t1 = new Thread(new Lock(lock1, lock2));
        Thread t2 = new Thread(new Lock(lock2, lock1));
        t1.start();
        Thread.sleep(500);
        t2.start();
    }

}

class Lock implements Runnable {

    private Object lock1;
    private Object lock2;

    public Lock(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        try {
            synchronized (lock1) {
                System.out.println("锁定" + lock1);

                Thread.sleep(2000);
                synchronized (lock2) {
                    System.out.println("锁定" + lock2);
                }
                System.out.println("解锁" + lock1);
                System.out.println("解锁" + lock2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
