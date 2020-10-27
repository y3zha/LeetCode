package leetcode.coding;

public class _892_三维形体的表面积 {

    /**
     * 非常好的题，威威哥的思路就非常的好
     *
     * 计算出一共有多少块正方体，每块正方体的表面积都是6，再减去重叠部分数量*2
     *
     * 有三种重叠的情况
     *  第一种是上下重叠，那么中间那部分就没了，面积-2
     *  第二种是行重叠，那中间的也没了，但是吧计算的时候高度得选小的，也就是重叠部分的
     *  第三种是列重叠，那中间那部分也没了，但是吧计算时候高度也得选小的，也就是重叠部分的
     *
     */

    public int surfaceArea(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        // 统计小正方形的个数、垂直重叠个数、行重叠个数、列重叠个数
        int cnt = 0;
        int verticalOverlap = 0;
        int rowOverlap = 0;
        int colOverlap = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                cnt += grid[i][j];
                // 垂直重叠的情况
                if (grid[i][j] > 0) {
                    // 两个重叠在一起，那么就会有一个重叠部分
                    verticalOverlap += grid[i][j] - 1;
                }
                // 行重叠的情况（也就是列要 > 0）
                if (j > 0) {
                    rowOverlap += Math.min(grid[i][j], grid[i][j - 1]);
                }
                // 列重叠的情况（也即是行要 > 0）
                if (i > 0) {
                    colOverlap += Math.min(grid[i][j], grid[i - 1][j]);
                }
            }
        }
        return cnt * 6 - (verticalOverlap + rowOverlap + colOverlap) * 2;
    }
}