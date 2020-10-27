package leetcode.coding;

import java.util.Arrays;

public class _1504_统计全1子矩形 {

    // 第 196 场周赛，其实没用二维前缀和，压缩思路很好
    // 二维压缩成一维来优化
    // 枚举 i 和 j，表示上边界在第i行，下边界在第 j 行，检查这之间的子矩阵哪些是全1的
    // 现在上边界和下边界确定了，那么只要某一列全为1，这就是符合要求的，相邻两列都是1，那么相邻两列也可以这么操作，如果有连续的三列，那么就能够贡献 1 + 2 + 3 = 6
    // 也就是只要check 某列是否为1就可以，求个和就行了

    public int numSubmat(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;
        int ans = 0;

        int[] s = new int[m];   // 存放每一列前缀和的值

        // 枚举上边界
        for (int i = 0; i < n; i++) {
            // 清空 s 数组
            Arrays.fill(s, 0);
            // 枚举下边界
            for (int j = i; j < n; j++) {
                // 开始计算这之间的每列的前缀和
                for (int k = 0; k < m; k++) {
                    s[k] += mat[j][k];
                }
                int cnt = 0;
                int target = j - i + 1;
                for (int k = 0; k < m; k++) {
                    if (s[k] == target) {
                        cnt++;
                        ans += cnt;
                    } else {
                        cnt = 0;
                    }
                }
            }
        }
        return ans;
    }
}