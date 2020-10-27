package leetcode.coding;

import java.util.HashSet;

/**
 * 若一个数是快乐数，最终变换会回到1，因此确定循环终结条件；
 * 若不是快乐数，会进入死循环，如何终至死循环，将每次变换过后的值存入HashSet中，判断是否出现过重复值，出现则return false;
 *
 * 碰到环的问题，永远都是两个方向：hash或快慢指针
 */
public class _202_快乐数 {

    //碰到环的问题：hash做法
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        set.add(n);
        while (n != 1) {
            int temp = helper(n);
            if (set.contains(temp)) {
                return false;
            } else {
                set.add(temp);
            }
            n = temp;
        }
        return true;
    }

    private int helper(int n) {
        int sum = 0;
        while (n != 0) {
            int temp = n % 10;
            sum += temp * temp;
            n /= 10;
        }
        return sum;
    }

    //快慢指针做法,看题解写的
    //快指针每次走两步，慢指针走一步，两者相等时，即为一个周期，此时判断是不是1引起的循环，如果是，那就找到快乐数了，否则return false
    //helper不变
    public boolean isHappy2(int n) {
        int fast = n;
        int slow = n;
        do {
            slow = helper(slow);
            fast = helper(fast);
            fast = helper(fast);
        } while (slow != fast);
        if (slow == 1 && fast == 1) {
            return true;
        }
        return false;
    }




}