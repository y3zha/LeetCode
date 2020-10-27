package leetcode.coding;

/**
 * 分割回文串,将 s 分割成一些子串，使每个子串都是回文串,求最少分割次数
 * 划分型dp，从最后一步出发，最后一段子串是s[i..n-1],只需要知道把s[0..i-1]划分成子串的最少次数是多少即可
 * dp[i]代表前i个字符划分回文串所花费的最小次数
 * dp[0] = 0
 */
public class _132_分割回文串II {

    public int minCut(String s) {
        char[] chs = s.toCharArray();
        int n = s.length();
        int[] dp = new int[n + 1];
        boolean[][] isPalindrome = getPalindrome(chs);
        dp[0] = 0;

        //前i个字符
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;

            //枚举i前面的每一个位置
            for (int j = 0; j < i; j++) {

                //如果j到i是回文串
                if (isPalindrome[j][i - 1]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        //切割次数 = 段数 - 1
        return dp[n] - 1;
    }

    private boolean[][] getPalindrome(char[] chs) {
        int n = chs.length;
        boolean[][] f = new boolean[n][n];

        //中心点与中心线判断两种方式判断所有子串是否为回文串
        //n个中心点,往两边扩展
        for (int c = 0; c < n; c++) {
            int i = c, j = c;
            while (i >= 0 && j < n && chs[i] == chs[j]) {
                f[i][j] = true;
                i--;
                j++;

            }
        }
        //n-1条中心线，往两边扩展
        for (int c = 0; c < n; c++) {
            int i = c, j = c + 1;
            while (i >= 0 && j < n && chs[i] == chs[j]) {
                f[i][j] = true;
                i--;
                j++;
            }
        }
        return f;
    }


}