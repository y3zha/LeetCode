package leetcode.coding;

/**
 * 直接用count统计窗口内的0的个数。当窗口内0的个数大于K时，我们需要缩小窗口；
 * 当窗口内0的个数小于等于k时，我们就可将窗口大小来与result来进行比较来确定是否更新result了。

 */
public class _1004_最大连续1的个数III {

    public int longestOnes(int[] A, int K) {
        int res = 0;
        int count = 0;
        int left = 0, right = 0;
        while (right < A.length) {
            if (A[right] == 0) {
                count++;
            }
            while (count > K) {
                if (A[left] == 0) {
                    count--;
                }
                left++;
            }
            //此时count=k
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}