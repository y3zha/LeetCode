package leetcode.coding;

public class _1085_最小元素各数位之和 {

    // 按照题目意思来即可
    public int sumOfDigits(int[] A) {
        // 找最小元素
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            min = Math.min(min, A[i]);
        }
        // 计算其各位之和
        int sum = calc(min);
        return (sum & 1) == 1 ? 0 : 1;
    }

    private int calc(int num) {
        int res = 0;
        while (num > 0) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }
}