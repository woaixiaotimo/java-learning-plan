package myWrite;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock+Condition，完成生产者消费者
 */
public class Method2_LockCondition {

    //公平锁
    private final Lock lock = new ReentrantLock(true);
    //锁-条件
    private final Condition condition = lock.newCondition();

    private final ThreadToGo threadToGo = new ThreadToGo();

    public static void main(String[] args) {
        Method1_WaitNotify m = new Method1_WaitNotify();

        Helper.instance.submit(m.newRunableNoOne());
        Helper.instance.submit(m.newRunableCharTwo());
        Helper.instance.shutdown();
    }

    public Runnable newRunnableNoArrOne() {
        final String[] noArr = Helper.bulidNoArr(52);

        return () -> {
            try {
                lock.lock();
                for (int i = 0; i < noArr.length; i += 2) {
                    while (threadToGo.value == 2)
                        condition.await();
                    Helper.print(noArr[i], noArr[i + 1]);
                    threadToGo.value = 2;
                    condition.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    public Runnable newRunnableCharArrTwo() {
        final String[] charArr = Helper.bulidCharArr(26);

        return () -> {
            try {
                lock.lock();
                for (int i = 0; i < charArr.length; i++) {
                    while (threadToGo.value == 1)
                        condition.await();
                    Helper.print(charArr[i]);
                    threadToGo.value = 1;
                    condition.signal();
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


