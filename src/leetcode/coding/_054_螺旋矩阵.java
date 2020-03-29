package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _054_螺旋矩阵 {

    //每次遍历完一行或者一列，缩下边界即可
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }

        //设定上下左右初始边界
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (up <= down && left <= right) {
            //先添加第一行
            for (int i = left; i <= right; i++) {
                res.add(matrix[up][i]);
            }
            //添加完缩小区间
            up++;
            //再添加列，行从刚才缩小的区间那一行开始
            for (int i = up; i <= down; i++) {
                res.add(matrix[i][right]);
            }
            //添加完缩小区间
            right--;
            //此时要遍历最底行了，但是要判断下是不是到了最后一行,如果up便到了down的下面，right变到了left的左面，那就不行了
            if (up <= down && left <= right) {
                for (int i = right; i >= left; i--) {
                    res.add(matrix[down][i]);
                }
                down--;
                for (int i = down; i >= up; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;
    }
}