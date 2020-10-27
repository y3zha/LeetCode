package leetcode.coding;

public class _861_翻转矩阵后的得分 {

    /**
     * 枚举的话，时间复杂度就太高了
     * 这个题是贪心做的
     * 对于任意一行而言，如果这一行的第一个数 1，那么它的分数一定会比不是 1 要高。
     * 如果第一位是 0，那么即使后面所有的位置都是 1，那也不会高到哪去
     * 因此我们不需要枚举每一行是否需要翻转，而是变为：如果该行的第一位是 0，则翻转，否则不翻转。
     * 随后枚举每一列是否翻转
     *
     * 0异或任何数＝任何数
     * 1异或任何数－任何数取反
     * 任何数异或自己＝把自己置0
     */
    public int matrixScore(int[][] A) {
        int n = A.length, m = A[0].length;
        int res = 0;
        //第一列全为1先都加上
        res += (1 << m - 1) * n;
        //把第0列变为1
        for (int i = 0; i < n; i++) {
            if (A[i][0] == 0) {
                for (int j = 0; j < m; j++) {
                    A[i][j] = 1 ^ A[i][j];
                }
            }
        }
        //统计每一列0,1个数，比较多的那个就是想要的数目
        for (int i = 1; i < A[0].length; i++) {
            int num = 0;
            for (int j = 0; j < n; j++) {
                if (A[j][i] == 0) {
                    num++;
                }
            }
            num = Math.max(num, n - num);
            res += (1 << A[0].length - i - 1) * num;
        }
        return res;
    }
}