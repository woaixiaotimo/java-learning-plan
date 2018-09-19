package myWrite;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用volatile+线程空转，完成生产者消费者
 */
public class Method4_Atomic {

    volatile AtomicInteger threadToGo = new AtomicInteger(1);

    public static void main(String[] args) {
        Method4_Atomic m = new Method4_Atomic();
        Helper.instance.submit(m.newRunnableNoArrOne());
        Helper.instance.submit(m.newRunnableCharArrTwo());
        Helper.instance.shutdown();
    }

    public Runnable newRunnableNoArrOne() {
        final String[] noArr = Helper.bulidNoArr(52);
        return () -> {

            for (int i = 0; i < noArr.length; i += 2) {
                while (threadToGo.get() == 2) {
                }
                Helper.print(noArr[i], noArr[i + 1]);
                threadToGo.set(2);
            }
        };
    }

    public Runnable newRunnableCharArrTwo() {
        final String[] charArr = Helper.bulidCharArr(26);
        return () -> {

            for (int i = 0; i < charArr.length; i++) {
                while (threadToGo.get() == 1) {
                }
                Helper.print(charArr[i]);
                threadToGo.set(1);
            }
        };
    }

}
