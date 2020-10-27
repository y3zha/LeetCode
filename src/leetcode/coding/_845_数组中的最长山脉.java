package leetcode.coding;

public class _845_数组中的最长山脉 {

    /**
     * 思路：记录上升和下降的长度即可，山脉数组当且仅当上升和下降的长度大于0
     *
     * @param A 输入数组
     * @return 最长山脉数组长度
     */
    public int longestMountain(int[] A) {
        int ans = 0, up = 0, down = 0;

        for (int i = 1; i < A.length; i++) {
            // check 是否需要重置
            if (down > 0 && A[i - 1] < A[i] || A[i - 1] == A[i]) up = down = 0;
            up += A[i - 1] < A[i] ? 1 : 0;
            down += A[i - 1] > A[i] ? 1 : 0;
            if (up > 0 && down > 0) ans = Math.max(ans, up + down + 1);
        }
        return ans;
    }
}