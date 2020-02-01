package leetcode.coding;

/**
 * 双指针
 *  设置双指针 i,j 分别位于容器壁两端，根据规则移动指针，并且更新面积最大值 res，直到 i == j 时返回 res。
 *  矩阵的面积和矩阵的长度、宽度有关，矩阵的长度是坐标之差，矩阵的宽度是高度较短的那条
 *  一开始left和right指针位于左右两端，计算此时的面积，较短的那一边往里移动，看能否找到更长的垂直线
 */
public class _011_盛最多水的容器 {

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while (left < right) {
            int len = right - left;     //不用+1，照着那张图比划下就知道。1～8中间是7段
            int width = Math.min(height[left], height[right]);
            res = Math.max(res, len * width);
            //移动垂直高度短的那段的指针
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }
}