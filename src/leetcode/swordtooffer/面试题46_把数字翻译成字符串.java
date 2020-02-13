package leetcode.swordtooffer;

/**
 * 划分型dp
 *
 * 最后一步肯定有一个字母，这个字母变成了0,1，2..,25
 * 设字符串的长度为N，要知道前N个字符串解密方式数，就要知道前N-1和前N-2解密方式数，因为最后一个字母可能转换成一位数，也可能转换成两位数
 * 即f[i] = f[i-1] + f[i-2]  i代表字符串长度
 * 初始条件f[0] = 1，空串也有一种方式解密（题目中规定的）
 * 边界条件：字符串长度为1，那只有一种方式解密
 */
public class 面试题46_把数字翻译成字符串 {

    public int translateNum(int num) {
        char[] sc = String.valueOf(num).toCharArray();
        int n = sc.length;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (sc[i - 1] >= '0' && sc[i - 1] <= '9') {
                dp[i] += dp[i - 1];
            }
            if (i > 1) {
                int a = (sc[i - 2] - '0') * 10 + (sc[i - 1] - '0');
                if (a >= 10 && a <= 25) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[n];
    }
}