package leetcode.swordtooffer;

public class 面试题21_调整数组顺序使奇数位于偶数前面 {

    /**
     * 这个题没有要求保持相对顺序，所以简单的相向双指针即可
     * 如果要保持相对顺序，就需要用同向双指针
     * 指针i 从左向右遍历 找偶数，指针j从i+1开始向后找奇数
     * 这里写个保持相对顺序的，空间换时间
     */
    public int[] exchange(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        //统计原数组中奇偶个数
        int odd = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 1){
                odd++;
            }
        }
        int o = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 1) {
                res[o++] = nums[i];
            } else {
                res[odd++] = nums[i];
            }
        }
        return res;
    }
}