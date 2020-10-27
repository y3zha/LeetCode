package leetcode.coding;

import java.util.Arrays;
import java.util.Collections;

/**
 * 和书本分发那个题一样
 */
public class _410_分割数组的最大值 {

    //二分
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        long start = 0, end = 0;
        for(int i = 0; i < n; i++){
            end += nums[i];
        }
        while(start + 1 < end){
            long mid = start + (end - start)/2;
            if(check(nums,m,mid)){
                end = mid;
            }else{
                start = mid;
            }
        }
        if(check(nums,m,start)){
            return (int)start;
        }else{
            return (int)end;
        }
    }

    private boolean check(int[] nums,int m, long limit){
        int cnt = 0;
        long val = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > limit) return false;
            if(val+ nums[i] > limit){
                cnt += 1;
                val = 0;
            }
            val += nums[i];
        }
        return cnt < m;
    }

    //dp
    public int splitArray2(int[] nums, int m) {
        int n = nums.length;
        long[][] dp = new long[m+1][n+1];
        //初始化第一行
        for(int i = 1; i <= n; i++){
            dp[0][i] = 0x7fffffff;
        }
        dp[0][0] = 0;
        //枚举拆分段数
        for(int i = 1; i<=m; i++){
            //枚举前j个数字
            for(int j = 1; j <= n; j++){
                dp[i][j] = 0x7fffffff;
                long sum = 0;
                for(int k = j; k >= 0; k--){
                    dp[i][j] = Math.min(dp[i][j],Math.max(dp[i-1][k],sum));
                    if(k >0){
                        sum += nums[k-1];
                    }
                }
            }
        }
        return (int)dp[m][n];
    }


    public int splitArray3(int[] nums, int m) {
        int n = nums.length;
        long[] ps = new long[n + 1];
        for (int i = 0; i < n; i++) {
            ps[i + 1] = ps[i] + nums[i];
        }
        long[] a = Arrays.copyOf(ps, n + 1);
        long[] b = Arrays.copyOf(ps, n + 1);
        for (int i = 2; i <= m; i++) {

            int k = 0;
            for (int j = 1; j <= nums.length; j++) {
                while (b[k] < ps[j] - ps[k]) {
                    k++;
                }
                a[j] = Math.min(b[k], ps[j] - ps[k - 1]);
            }
            swap(a, b);
        }
        return (int) b[n];
    }

    private static void swap(long[] a, long[] b) {
        long[] temp = a;
        a = b;
        b = temp;
    }

    public static void main(String[] args) {
        long[] a = new long[]{1};
        long[] b = new long[]{2};
        swap(a, b);
        System.out.println(a[0]);
    }


}