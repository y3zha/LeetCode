package leetcode.coding;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.HashSet;
import java.util.Set;

public class _1143_最长公共子序列 {

    //带打印的
    public int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i = 0; i<= m; i++){
            dp[0][i] = 0;
        }
        for(int i = 0; i <= n; i++){
            dp[i][0] = 0;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m ; j++){
                //如果字符串A的最后一个不在这个LCS中，那就是看A串的前i-1个和B的前j个
                //如果字符串B的最后一个不再这个LCS中，那就是看B的前j-1个和A的前i个
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1] + 1);
                }
            }
        }

        Set<String> set = new HashSet<>();
        dfs("", s1, s2, set, n, m, dp);
        for (String s : set) {
            System.out.println(s);
        }
        return dp[n][m];
    }

    private void dfs(String temp, String s1, String s2, Set<String> set, int i, int j, int[][] dp) {
        if (temp.length() == dp[s1.length()][s2.length()]) {
            set.add(new StringBuilder(temp).reverse().toString());
            return;
        }
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            temp += s1.charAt(i - 1);
            dfs(temp, s1, s2, set, i - 1, j - 1, dp);
        } else {
            //上面更大
            if (dp[i - 1][j] >= dp[i][j - 1]) {
                dfs(temp, s1, s2, set, i - 1, j, dp);
            }
            //左面更大
            if (dp[i][j - 1] >= dp[i - 1][j]) {
                dfs(temp, s1, s2, set, i, j - 1, dp);
            }
        }

    }


}