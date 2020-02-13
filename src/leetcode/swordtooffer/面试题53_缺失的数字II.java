package leetcode.swordtooffer;

public class 面试题53_缺失的数字II {

    /**
     * 看测试用例数组并不是递增有序的
     * 如果是递增有序  直接二分查找
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int s1 = n * (n + 1) / 2;
        int s2 = 0;
        for (int i = 0; i < n; i++) {
            s2 += nums[i];
        }
        return s1 - s2;
    }
}