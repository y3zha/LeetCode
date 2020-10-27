package leetcode.coding;

public class _516_最长回文子序列 {


    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int[][] dp = new int[n][n];

        //初始化一下
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 2);
                }
            }
        }
        return dp[0][n - 1];
    }

    //记忆化搜索
    public int longestPalindromeSubseq2(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        //每个位置标记为没访问过
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {   //从i开始
                dp[i][j] = -1;
            }
        }
        dfs(s, 0, n - 1,dp);
        return dp[0][n - 1];
    }

    private void dfs(String s, int i, int j, int[][] dp) {
        if (i > j) {
            return;
        }
        //如果计算过了就返回
        if (dp[i][j] != -1) return;
        //如果i == j，那设置下就返回
        if (i == j) {
            dp[i][j] = 1;
            return;
        }
        dfs(s, i + 1, j, dp);
        dfs(s, i, j - 1, dp);
        dfs(s, i + 1, j - 1, dp);
        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
        }
    }

    /**
     * 如果面试官要求打印一个最长回文子序列怎么写
     * 需要对去头、去尾，相等做一些标记，需要一个path数组
     */

    public static int longestPalindromeSubseq3(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int[][] path = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                //如果是去头,path上标记为0，如果是去尾标记为1，如果是相等，标记为2
                if (dp[i][j] == dp[i + 1][j]) {
                    path[i][j] = 0;
                } else if (dp[i][j] == dp[i][j - 1]) {
                    path[i][j] = 1;
                }
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                    if (dp[i][j] == dp[i + 1][j - 1] + 2) {
                        path[i][j] = 2;
                    }
                }
            }
        }

        char[] res = new char[dp[0][n - 1]];
        //遍历s的两个指针
        int i = 0, j = n - 1;
        //填充res数组的两个指针
        int p = 0, q = dp[0][n - 1] - 1;
        while (i <= j) {
            if (i == j) {
                res[p] = s.charAt(i);
                break;
            }
            if (i + 1 == j) {
                res[p] = s.charAt(i);
                res[q] = s.charAt(j);
                break;
            }
            //其他情况，去头、去尾、相等
            if (path[i][j] == 0) {
                i++;
            } else if (path[i][j] == 1) {
                j--;
            } else {
                res[p++] = s.charAt(i++);
                res[q--] = s.charAt(j--);
            }
        }
        for (int k = 0; k < res.length; k++) {
            System.out.print(res[k] + " ");
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        longestPalindromeSubseq3("bbbab");
    }



}