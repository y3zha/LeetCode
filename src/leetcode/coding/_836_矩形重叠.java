package leetcode.coding;

/* 分析
 * 假设一个固定，另一个从左往右移，那么在一个维度上分别是:不重叠 重叠（右边刚进入） 重叠（大的包含小的） 重叠（左边还在里面） 不重叠
 * 这个是以x轴为例，有5种情况，同样在y轴也是有五种情况
 * 特别要注意：两个维度上都重叠矩形才重叠
 *
 * 五种情况分类讨论有点麻烦，反过来想，考虑不重叠的时候是什么情况，只要一个在上一个在下，或者一个在左一个在右，那就不会重叠
 */
public class _836_矩形重叠 {

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int x1 = rec1[0];   //左下角
        int y1 = rec1[1];
        int x2 = rec1[2];   //右上角
        int y2 = rec1[3];
        int x3 = rec2[0];   //左下角
        int y3 = rec2[1];
        int x4 = rec2[2];   //右上角
        int y4 = rec2[3];

        if (x2 >= x3 || x4 >= x1 || y1 >= y4 || y3 >= y2) {
            return false;
        }
        return true;
    }
}