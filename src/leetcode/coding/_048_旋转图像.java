package leetcode.coding;

/**
 * 旋转图像
 */
public class _048_旋转图像 {

    //思路：先转置，再每列两两换一下
    //n*n的矩阵
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //transpose,转置实际上就是 i,j换了一下,比如(0,2) -> (2,0)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {   //矩阵转秩从对角线开始，不然就再换回来了
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        //每列两两交换下,第一列和最后一列互换，第二列和倒数第二列互换...这样子
        int start = 0;      //第start列
        int end = n - 1;    //第end列
        while (start < end) {
            //每列n个元素
            for (int i = 0; i < n; i++) {
                int temp = matrix[i][start];
                matrix[i][start] = matrix[i][end];
                matrix[i][end] = temp;
            }
            start++;
            end--;
        }
    }
}