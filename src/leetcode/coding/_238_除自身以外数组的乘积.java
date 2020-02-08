package leetcode.coding;

public class _238_除自身以外数组的乘积 {

    //要求不能使用除法
    //方法是 ； 乘积 = 当前数左边的乘积 * 当前数右边的乘积
    public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            int left = 1;

        //计算当前位置左边的乘积
        for (int i = 0; i < n; i++) {
            res[i] = left;
            left = left * nums[i];
        }
        
        //计算当前位置右边的乘积
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right = right * nums[i];
        }
        return res;
    }
}