package com.materWork;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.println(
                Runtime.getRuntime().availableProcessors()
        );
        Master master = new Master(new Worker(), Runtime.getRuntime().availableProcessors());
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Task t = new Task();
            t.setId(i);
            t.setName("任务" + i);
            t.setPrice(random.nextInt(1000));

            master.submit(t);
        }
        master.execute();
        long start = System.currentTimeMillis();
        while (true) {
            if (master.isComplete()) {
                long result = master.getResult();

                System.out.println("执行完成 result = " + result + "！");
                System.out.println("耗时" + (System.currentTimeMillis() - start));
                break;
            }
        }
    }
}
