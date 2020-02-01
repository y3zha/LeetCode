package leetcode.coding;

/**
 * 最长回文子串
 * 从最后一步出发，假设字符串str是最长回文子串（i..j）
 * 那么它的子串str'（i+1...j-1）也是最长回文子串
 */
public class _005_最长回文子串 {

    //使用memo的方法，中心扩展法
    public static String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) {
            return "";
        }
        boolean[][] memo = getPalindrome(s);
        String res = "";
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String temp = s.substring(i, j + 1);
                if (memo[i][j] && temp.length() > res.length()) {
                    res = temp;
                }
            }
        }
        return res;
    }

    public static boolean[][] getPalindrome(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        boolean[][] memo = new boolean[n][n];
        //初始都是false的，免去初始化步骤
        //以中心点扩展，共n个中心点
        for (int c = 0; c < n; c++) {
            int i = c, j = c;
            while (i >= 0 && j < n && chs[i] == chs[j]) {
                memo[i][j] = true;
                i--;
                j++;
            }
        }
        //以中心线扩展，共n-1条中心线
        for (int c = 0; c < n; c++) {
            int i = c, j = c + 1;
            while (i >= 0 && j < n && chs[i] == chs[j]) {
                memo[i][j] = true;
                i--;
                j++;
            }
        }
        return memo;
    }


    //区间型dp的解法，假设最长回文字串是s（i,j）,那么它的去头去尾的子串s'(i+1..j-1)也是在这个区间内的最长回文子串
    //假设最长回文子串的长度为len len的范围是0 <= len <= n
    //枚举len，只需要记录最长的长度以及起点，就能得到子串
    public static String longestPalindrome2(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int longest = 1;

        //初始化
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        //要单独处理下相邻的
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = chs[i] == chs[i + 1];
            if (dp[i][i + 1]) {
                longest = 2;
                start = i;
            }
        }
        //长度从3开始
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (dp[i + 1][j - 1] && chs[i] == chs[j]) {
                    dp[i][j] = true;
                }
                if (dp[i][j] && len > longest) {
                    longest = len;
                    start = i;
                }
            }
        }
        return s.substring(start, start + longest);
    }


    public static void main(String[] args) {
        String s = "bb";
        System.out.println(longestPalindrome2(s));
    }
}