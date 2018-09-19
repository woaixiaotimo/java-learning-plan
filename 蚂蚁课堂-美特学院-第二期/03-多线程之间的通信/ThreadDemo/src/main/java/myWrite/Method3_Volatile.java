package myWrite;


/**
 * 使用volatile+线程空转，完成生产者消费者
 */
public class Method3_Volatile {

    volatile ThreadToGo threadToGo = new ThreadToGo();

    public static void main(String[] args) {
        Method1_WaitNotify m = new Method1_WaitNotify();

        Helper.instance.submit(m.newRunableNoOne());
        Helper.instance.submit(m.newRunableCharTwo());
        Helper.instance.shutdown();
    }

    public Runnable newRunnableNoArrOne() {
        final String[] noArr = Helper.bulidNoArr(52);

        return () -> {

            for (int i = 0; i < noArr.length; i += 2) {
                while (threadToGo.value == 2) {

                }
                Helper.print(noArr[i], noArr[i + 1]);
                threadToGo.value = 2;
            }

        };
    }

    public Runnable newRunnableCharArrTwo() {
        final String[] charArr = Helper.bulidCharArr(26);

        return () -> {

            for (int i = 0; i < charArr.length; i++) {
                while (threadToGo.value == 1) {
                }
                Helper.print(charArr[i]);
                threadToGo.value = 1;
            }
        };
    }


    class ThreadToGo {
        int value = 1;
    }
}
