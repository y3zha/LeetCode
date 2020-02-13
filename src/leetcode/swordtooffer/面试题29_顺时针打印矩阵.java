package leetcode.swordtooffer;

public class 面试题29_顺时针打印矩阵 {
    /**
     * 设置四个边界即可
     */

    public int[] spiralOrder(int[][] mat) {
        if (mat.length == 0) {
            return new int[]{};
        }
        int n = mat.length;
        int m = mat[0].length;

        int up = 0;
        int down = n - 1;
        int left = 0;
        int right = m - 1;

        int[] res = new int[n * m];
        int index = 0;

        while (up <= down && left <= right) {
            //模拟，先添加第一行
            for (int i = left; i <= right; i++) {
                res[index++] = mat[up][i];
            }
            //添加完缩小区间
            up++;
            //然后添加右边一列
            for (int i = up; i <= down; i++) {
                res[index++] = mat[i][right];
            }
            //添加完缩小区间
            right--;

            //此时要遍历最底行了，但是要判断下是不是到了最后一行,如果up便到了down的下面，right变到了left的左面，那就不行了
            if (up <= down && left <= right) {
                //遍历当前最底行
                for (int i = right; i >= left; i--) {
                    res[index++] = mat[down][i];
                }
                // 遍历玩缩小区间
                down--;
                //遍历左边一列
                for (int i = down; i >= up; i--) {
                    res[index++] = mat[i][left];
                }
                //遍历完缩小区间
                left++;
            }
        }
        return res;
    }
}