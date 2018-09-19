package myWrite;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Helper {

    //当前类实例
    public static Helper instance = new Helper();

    //初始化一个固定线程数量的线程池
    private static final ExecutorService tPool = Executors.newFixedThreadPool(2);

    //创建String类型的数字数组并返回
    public static String[] bulidNoArr(int max) {
        String[] noArr = new String[max];
        for (int i = 0; i < max; i++) {
            noArr[i] = Integer.toString(i + 1);
        }
        return noArr;
    }

    //创建String类型的char数组并返回
    public static String[] bulidCharArr(int max) {
        String[] charArr = new String[max];
        int initChar = 65;
        for (int i = 0; i < max; i++) {
            charArr[i] = String.valueOf((char) (initChar + i));
        }
        return charArr;
    }

    //打印传入参数
    public static void print(String... input) {
        if (input == null || input.length <= 0)
            return;

        for (String each : input) {
            System.out.print(each);
        }
    }

    //提交到线程池
    public void submit(Runnable runnable) {
        tPool.submit(runnable);
    }

    //关闭线程池
    public void shutdown() {
        tPool.shutdown();
    }

}
