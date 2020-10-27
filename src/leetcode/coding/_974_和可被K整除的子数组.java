package leetcode.coding;

import java.util.HashMap;

/**
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 */
public class _974_和可被K整除的子数组 {

    //暴力前缀和不可取，直接超时
    public int subarraysDivByK(int[] A, int k) {
        int n = A.length;
        int[] ps = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ps[i] = ps[i - 1] + A[i - 1];
        }
        int res = 0;
        for (int s = 0; s < n; s++) {
            for (int e = s + 1; e <= n; e++) {
                if ((ps[e] - ps[s]) % k == 0) {
                    res++;
                }
            }
        }
        return res;
    }



    // 我们要判断的是(sum[j] - sum[i])% K是否等于0。
    // 根据modmod运算的性质，我们知道(sum[j] - sum[i])%K = sum[j]%K - sum[i]%K
    // 因此，若要(sum[j] - sum[i])% K = 0，则要sum[j]%K = sum[i]%K
    // 所有满足nums[i:j]中元素之和可以被K整除的开始下标i，必有sum[j]%K = sum[i]%K。
    // 以sum[i]%K作为键值统计其出现的频率，从而对于每个下标j我们可以立即获得能和它组成满足要求的子数组的开始下标i的数量。
    // 生成前缀和数组的过程中，将key = sum[j]% k 出现的频率加入结果数组， 同时将 key = sum[j]%k 的出现频率加1
    // 由于数组中有可能出现负数，我们需要将其加KK从而使其 %K 之后的值为正数。

    public int subarraysDivByK2(int[] A, int k){
        int n = A.length, res = 0, sum = 0;
        int[] map = new int[k];
        map[0] = 1;
        for (int i = 0; i < n; i++) {
            sum += A[i];
            int key = (sum % k + k) % k;
            //在加进去前先统计下数量
            res += map[key];
            map[key]++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {4, 5, 0, -2, -3, 1};
        _974_和可被K整除的子数组 test = new _974_和可被K整除的子数组();
        test.subarraysDivByK2(a, 5);

    }






}