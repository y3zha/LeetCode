package leetcode.coding;

import java.io.*;

public class _560_和为K的子数组 {

    /**
     * 这个题和713题很相似
     *
     * 一种写法是和760题我的滑动动窗口的写法一样，可能会超时，还有种写法就是760题的第二种写法
     * 双指针
     *
     * 这边写双指针，感觉不是很方便，还是用前缀和
     */
    public static int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = nums[i - 1] + prefixSum[i - 1];
        }
        int res = 0;;
        for (int start = 0; start < n; start++) {
            for (int end = start + 1; end <= n; end++) {
                if (prefixSum[end] - prefixSum[start] == k) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken();
        int size = (int) st.nval;
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            st.nextToken();
            a[i] = (int) st.nval;
        }
        st.nextToken();
        int k = (int) st.nval;
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        pw.print(subarraySum(a, k));
    }
}