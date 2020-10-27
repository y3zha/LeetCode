package leetcode.coding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _1074_元素和为目标值的子矩阵数量 {

    // 二维前缀和问题，对于每一行从前向后累加，对于每一列从前向后累加，得到的矩阵就是前缀和矩阵
    // 求(i1,j1)为左上角(i2,j2)为右下脚的矩阵内部和，就是
    // S(i1,j1,i2,j2)=S(0,0,i2,j2)+S(0,0,i1−1,j1−1)−S(0,0,i1−1,j2)−S(0,0,i2,j1−1)
    // 但是仅仅这样是不行的，因为我们需要枚举四个变量，时间复杂度就是O(r^2*c^2)，需要继续优化。
    // 这个题和560题差不多吧，之前是一维问题，现在是二维问题，但是处理思想是一样的。
    // 首先要将二维转化为一维，此时我们计算每一行的前缀和，而不是整个矩阵的前缀和
    // 然后可以取不同的两个列(j1,j2)，计算以这两个列为边界计算每一行的前缀和（这就是二维前缀和）。
    /*
    010    011
    111 -> 123
    010    011
    -----------
    j1, j2 = 0, 2
    pre(j1, j2, 0) = sum(011) = 2
    pre(j1, j2, 1) = sum(011) + sum(123) = 8
    pre(j1, j2, 2) = sum(011) + sum(123) + sum(011) = 10
    然后通过字典记录每个pre(j1,j2,k)出现的次数，那么每次结果主要加上pre(j1,j2,cur)-target对应的字典值即可（其中cur表示当前第几行）。
    最后还有一个边界情况需要考虑，就是当矩阵为空的时候，此时前后嘴和为0，字典需要记录0出现了1次。
     */

    public int numSubmatrixSumTarget(int[][] mat, int target) {
        int n = mat.length;
        int m = mat[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                mat[i][j] += mat[i][j - 1];
            }
        }
        int res = 0;
        int cur = 0;
        Map<Integer, Integer> map = new HashMap<>();
        //枚举两列
        //第i列
        for (int j1 = 0; j1 < m; j1++) {
             //第j列
            for (int j2 = j1; j2 < m; j2++) {
                map.clear();
                //矩阵为空的情况
                map.put(0, 1);
                cur = 0;
                //枚举行数
                for (int i = 0; i < n; i++) {
                    cur += mat[i][j2] - (j1 > 0 ? mat[i][j1 - 1] : 0);
                    res += map.getOrDefault(cur - target, 0);
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return res;
    }

    /**
     * 另一种写法，枚举上下边界。这样其实就转化为一维前缀和了
     */
    public int numSubmatrixSumTarget2(int[][] mat, int target) {
        int n = mat.length, m = mat[0].length;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int[] s = new int[m];   // 存放每一列的前缀和

        // 枚举上边界
        for (int i = 0; i < n; i++) {
            Arrays.fill(s, 0);
            // 枚举下边界
            for (int j = i; j < n; j++) {
                map.clear();
                map.put(0, 1);
                int cur = 0;    // 固定上下后，前k列的和
                // 枚举 k 列
                for (int k = 0; k < m; k++) {
                    s[k] += mat[j][k];
                    cur += s[k];
                    ans += map.getOrDefault(cur-target, 0);
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return ans;
    }


}