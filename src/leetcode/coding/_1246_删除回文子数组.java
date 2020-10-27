package leetcode.coding;

public class _1246_删除回文子数组 {

    /**
     * dp[i][j]代表把从下标为i到j区间的字符给删除所要花费的最小代价
     * 1. 区间长度为1 直接删除，代价为1
     * 2. 区间长度为2的时候，最小代价得看情况了，如果是回文串，那么代价就是1，可以一起删除，如果不是回文串，那么就要一个个删，代价为2
     * 3. 区间长度大于2的时候
     *      如果a[i] = a[j], 那么可以和[i+1,j-1]这块一起删除，代价是 dp[i+1][j-1], 不需要额外的代价
     *      如果a[i] != a[j], 那么就是要枚举左边删除哪一部分代价最小，右边删除哪一部分代价最小，有一个划分的点，需要枚举这个划分点,让这两部分加起来，最小
     */
    public int minimumMoves(int[] a) {
        int n = a.length;
        // 特判
        if (n == 1) return 1;
        if (n == 2) return a[0] == a[1] ? 1 : 2;
        int[][] f = new int[n][n];
        // 初始化区间长度为 1
        for (int i = 0; i < n; i++) {
            f[i][i] = 1;
        }
        // 初始化区间长度为2
        for (int i = 1; i < n; i++) {
            f[i - 1][i] = a[i - 1] == a[i] ? 1 : 2;
        }
        // 从len为3开始
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                f[i][j] = Integer.MAX_VALUE;
                // 这里不是 if 再else
                for (int k = i; k < j; k++) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j]);
                }
                if (a[i] == a[j]) {
                    f[i][j] = Math.min(f[i][j], f[i + 1][j - 1]);
                }

            }
        }
        return f[0][n - 1];
    }


    /**
     * 改写成搜索，我下面这个不对啊
     */
    // public int minimumMoves2(int[] arr) {
    //     int n = arr.length;
    //     int[][] dp = new int[n][n];
    //     for (int i = 0; i < n; i++) {
    //         for (int j = 0; j < n; j++) {
    //             dp[i][j] = 0x3f3f3f3f;
    //         }
    //     }
    //     dfs(arr, dp, 0, n - 1);
    //     return dp[0][n - 1];
    // }
    //
    // private void dfs(int[] arr, int[][] dp, int i, int j) {
    //     if (j < i) {
    //         return;
    //     }
    //     if (dp[i][j] != 0x3f3f3f3f) {
    //         return;
    //     }
    //     if (i == j) {
    //         dp[i][j] = 1;
    //         return;
    //     }
    //     if (j == i + 1) {
    //         dp[i][j] = arr[i] == arr[i + 1] ? 1 : 2;
    //         return;
    //     }
    //     dfs(arr, dp, i + 1, j);
    //     dfs(arr, dp, i, j - 1);
    //     dfs(arr, dp, i + 1, j - 1);
    //     for (int k = i; k < j; k++) {
    //         dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
    //     }
    //     if (arr[i] == arr[j]) {
    //         dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
    //     }
    // }

    /**
     * 搜索姿势
     * 最坏的情况是 1 + dp[i+1,j]
     * 当arr[i] = arr[i+1] 的时候，我们需要的步数是1 + dp[i+2][j]
     * 当arr[i] = arr[k] k != i + 1的时候，我们需要的步数是 dp[i][k-1] + dp[k+1][j]
     */
    int[][] dp;
    int[] arr;
    public int minimumMoves2(int[] arr) {
        this.arr = arr;
        int n = arr.length;
        dp = new int[n][n];
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i > j) {
            return 0;
        }
        if (dp[i][j] > 0) {
            return dp[i][j];
        }
        if (i == j) {
            dp[i][j] = 1;
            return 1;
        }
        if (j == i + 1) {
            dp[i][j] = arr[i] == arr[i + 1] ? 1 : 2;
            return dp[i][j];
        }
        int res = Integer.MAX_VALUE;
        // 最坏的情况是 1 + dp[i+1,j]
        res = Math.min(res, dfs(i + 1, j) + 1);
        // 当arr[i] = arr[i+1] 的时候，我们需要的步数是1 + dp[i+2][j]
        for (int k = i + 1; k <= j; k ++) {
            if (arr[k] == arr[i]) {
                if (i + 1 == k) {
                    res = Math.min(res, 1 + dfs(k + 1, j));
                } else {
                    int returnValue = dfs(i + 1, k - 1) + dfs(k + 1, j);
                    res = Math.min(returnValue, res);
                }
            }
        }
        return dp[i][j] = res;
    }


}