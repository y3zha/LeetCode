package leetcode.swordtooffer;

import java.util.Arrays;

public class 面试题61_扑克牌中的顺子 {

    /**
     * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
     *
     * 思路
     *      前提 ：题目限制数组长度为 5，数组的数取值为 [0, 13]，如果不限制数组长度，那顺子长度必定>=5，可以做个特判
     *      1、顺子中肯定不能有重复的 非零数字
     *      2、先对数组排序，再去计算当前数和前面一个数的diff（间隔），如果是顺子 比如 1 2 3，那么最后计算下来的总的diff为0
     *      3、如果diff不为0，就要看0的个数了，diff-zero == 0？
     *
     *   [0,0,0,9,11] 三个大王用例
     *
     *   多出来的0不用管
     *   所以是 diff - zero <=0 即可
     */

    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int diff = 0;
        int zero = 0;
        int pre = -1;
        for (int i = 0; i < nums.length; i++) {
            //如果是0，统计 跳过
            if (nums[i] == 0) {
                zero++;
                continue;
            }
            //如果是第一个，跳过
            if (pre == -1) {
                pre = nums[i];
                continue;
            }
            //如果有重复的，return false
            if (nums[i] == nums[i - 1]) {
                return false;
            }
            diff += nums[i] - nums[i - 1] - 1;
        }
        return diff == 0 || (diff - zero <= 0);
    }

}