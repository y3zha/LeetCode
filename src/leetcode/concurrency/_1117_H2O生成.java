package leetcode.concurrency;

import java.util.concurrent.Semaphore;

/**
 * 每个线程必须等候直到一个完整水分子能够被产生出来。
 *
 * 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
 * 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
 *
 * 那就相当于： h每获取一次释放一个o许可，o每次获取两个许可（即2次h后执行一次o）,用完之后也要给h释放两个许可，用多少释放多少
 */
public class _1117_H2O生成 {

    class H2O {
        Semaphore o;
        Semaphore h;

        public H2O() {
            o = new Semaphore(0);
            h = new Semaphore(2);
        }

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            h.acquire();
            releaseHydrogen.run();
            o.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            o.acquire(2);
            releaseOxygen.run();
            h.release(2);
        }
    }
}