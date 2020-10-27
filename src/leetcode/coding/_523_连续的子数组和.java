package leetcode.coding;

/**
 * 前缀和
 */
public class _523_连续的子数组和 {

    public static boolean checkSubarraySum(int[] nums, int k) {
        //前缀和数组
        int n = nums.length;
        if(n < 2) return false;
        int[] prefixSum = new int[n+1];
        for(int i = 1; i <= n; i++){
            prefixSum[i] = nums[i-1] + prefixSum[i-1];
        }

        //枚举起点
        for (int i = 0; i <= n - 1; i++) {
            for (int j = i + 2; j <= n; j++) {  //为什么是从i+2开始，而不能从i+1开始
                int temp = prefixSum[j] - prefixSum[i]; //sum[2] - sum[0]，实际上是1~2这段数组的和
                if (k == 0 && temp == 0) return true;
                if (k != 0 && temp % k == 0) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 0};
        checkSubarraySum(a, 0);
    }



}