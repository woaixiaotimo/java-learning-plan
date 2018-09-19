package myWrite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Method5_CyclicBarrier {

    //内部使用Lock锁实现
    private CyclicBarrier cyclicBarrier;

    private final List<String> list;

    public Method5_CyclicBarrier() {
        list = Collections.synchronizedList(new ArrayList<String>());
        //为cyclicBarrier添加屏障解除后回调
        cyclicBarrier = new CyclicBarrier(2, newBarrierAction());
    }

    public static void main(String[] args) {
        Method5_CyclicBarrier m = new Method5_CyclicBarrier();
        Helper.instance.submit(m.newRunnableNoArrOne());
        Helper.instance.submit(m.newRunnableCharArrTwo());
        Helper.instance.shutdown();
    }

    public Runnable newRunnableNoArrOne() {
        final String[] noArr = Helper.bulidNoArr(52);
        return () -> {

            try {
                for (int i = 0; i < noArr.length; i += 2) {
                    list.add(noArr[i]);
                    list.add(noArr[i + 1]);
                    cyclicBarrier.await();
//                    Helper.print(noArr[i], noArr[i + 1]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
    }

    public Runnable newRunnableCharArrTwo() {
        final String[] charArr = Helper.bulidCharArr(26);
        return () -> {
            try {
                for (int i = 0; i < charArr.length; i++) {
                    list.add(charArr[i]);
                    cyclicBarrier.await();
//                    Helper.print(charArr[i]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
    }


    private Runnable newBarrierAction() {
        return new Runnable() {
            @Override
            public void run() {
                Collections.sort(list);
                list.forEach(c -> System.out.print(c));
                list.clear();
            }
        };
    }

}
