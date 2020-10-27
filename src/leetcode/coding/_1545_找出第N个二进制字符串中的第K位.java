package leetcode.coding;

public class _1545_找出第N个二进制字符串中的第K位 {

    /**
     * Sn 的长度是 2^n - 1 ，那么该串的中间字符下标为 2^(n-1)
     * 递归截止条件为 n==1
     * 如果 k == mid ,则返回 '1',
     * 如果 k < mid,左子串为 Sn-1，相当于求 Sn-1 的第 k 个
     * 如果 k > mid, Sn 的第 k 位相当于右子串的 k - mid 位,由于右子串是翻转的，故相当于左子串的 Ln-1 - (k - mid) + 1处取反
     */
    public char findKthBit(int n, int k) {
        if (n == 1) {
            return '0';
        }
        int mid = 1 << (n - 1);
        if (k == mid) {
            return '1';
        } else if (k < mid) {
            return findKthBit(n - 1, k);
        } else {
            return findKthBit(n - 1, (1 << n) - k) == '0' ? '1' : '0';
        }
    }
}