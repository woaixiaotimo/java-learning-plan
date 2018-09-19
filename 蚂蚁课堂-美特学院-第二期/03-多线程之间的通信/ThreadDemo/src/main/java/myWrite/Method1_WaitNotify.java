package myWrite;

/**
 * 使用wait()&notify()，完成生产者消费者
 */
public class Method1_WaitNotify {

    public static void main(String[] args) {
        Method1_WaitNotify m1 = new Method1_WaitNotify();
        Helper.instance.submit(m1.newRunableNoOne());
        Helper.instance.submit(m1.newRunableCharTwo());
        Helper.instance.shutdown();

    }

    public final ThreadToGo threadToGo = new ThreadToGo();

    public Runnable newRunableNoOne() {
        //获取1-52的数组
        final String[] noArr = Helper.bulidNoArr(52);

        return () -> {
            try {
                for (int i = 0; i < noArr.length; i+=2) {
                    synchronized (threadToGo) {

                        //如果不是当前线程轮次则等待
                        while (threadToGo.value == 2)
                            threadToGo.wait();
                        Helper.print(noArr[i], noArr[i + 1]);
                        threadToGo.value = 2;
                        //通知另一个线程继续
                        threadToGo.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    public Runnable newRunableCharTwo() {
        //获取A-Z的数组
        final String[] charArr = Helper.bulidCharArr(26);

        return () -> {
            try {
                for (int i = 0; i < charArr.length; i++) {
                    synchronized (threadToGo) {
                        //如果不是当前线程轮次则等待
                        while (threadToGo.value == 1)
                            threadToGo.wait();
                        Helper.print(charArr[i]);
                        threadToGo.value = 1;
                        //通知另一个线程继续
                        threadToGo.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
    class ThreadToGo {
        volatile int value = 1;
    }

}


