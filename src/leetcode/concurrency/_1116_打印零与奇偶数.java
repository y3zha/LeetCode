package leetcode.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class _1116_打印零与奇偶数 {

    /**
     * 使用Semaphore解决
     */

    class ZeroEvenOdd {
        private int n;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        Semaphore zero = new Semaphore(1);
        Semaphore even = new Semaphore(0);
        Semaphore odd = new Semaphore(0);

        //IntConsumer类表示接受单个int值参数的操作，不返回任何结果。其方法为accept
        public void zero(IntConsumer ic) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                zero.acquire();
                ic.accept(0);
                //0消耗完就是要打印奇数的，实际上是i = 0、2、4的时候把权限放出去
                if ((i & 1) == 0) {
                    odd.release();
                } else {
                    even.release();
                }
            }
        }

        public void even(IntConsumer ic) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                even.acquire();
                ic.accept(i);
                zero.release();
            }
        }

        public void odd(IntConsumer ic) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                odd.acquire();
                ic.accept(i);
                zero.release();
            }
        }
    }

}