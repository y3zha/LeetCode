package leetcode.coding;

/**
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *
 * 滑动窗口解法
 *      结果分成两部分，不生气时候的人数+生气时候的人数，生气时候的人数就是那个窗口里面的最多人数，所以要用滑动窗口求生气时候的最多人数即可
 *      因为生气人最多，如果不抑制，走的人最多
 *
 *      总的来说：先计算不使用技能时满意顾客数，再滑动窗口计算使用技能后新满意的顾客数，结果相加即可
 *
 */
public class _1052_爱生气的书店老板 {

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int max = 0;  //记录窗口最大值
        int curMax = 0; //记录当前窗口的最大值
        int res = 0;    //结果
        
        //滑动窗口计算窗口最大值
        for (int i = 0; i < customers.length; i++) {
            // 直接加上不生气时候的顾客数
            if (grumpy[i] == 0) {
                res += customers[i];
            } else {
                curMax += customers[i];
            }
            if (i >= X) {
                curMax -= grumpy[i - X] == 1 ? customers[i - X] : 0;
            }
            max = Math.max(max, curMax);
        }
        return res + max;
    }

}