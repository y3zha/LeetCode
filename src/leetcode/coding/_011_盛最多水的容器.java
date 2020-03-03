package leetcode.coding;

/**
 * 双指针
 *  设置双指针 i,j 分别位于容器壁两端，根据规则移动指针，并且更新面积最大值 res，直到 i == j 时返回 res。
 *  矩阵的面积和矩阵的长度、宽度有关，矩阵的长度是坐标之差，矩阵的宽度是高度较短的那条
 *  一开始left和right指针位于左右两端，计算此时的面积，较短的那一边往里移动，看能否找到更长的垂直线
 *  //证明 https://leetcode-cn.com/problems/container-with-most-water/solution/on-shuang-zhi-zhen-jie-fa-li-jie-zheng-que-xing-tu/
 *
 *  这里一个简单的证明：
 *  问题的关键在于，为什么直接舍弃较短的高向中间挪动不会错过最大值？用反证法
 *  假定错过了最大值，并称最大值时的两个高为a、b，左指针L与右指针R;
 *  显然，开始的时候 a、b 必然是包含于 L 与 R 之内的，在某一时刻，不妨设 L 来到了 a 的位置，此时 b 仍然在R的左边，
 *  并且在这种算法下（短的移动）L离开了a，那就说明a的高度不如此时R的高度。
 *  这时，矛盾出现了，相比于最大值的 a 与 b
 *  a与R此时的解，底更大，容器高度等于a，而a与b的底更小，容器高度小于等于a，那么a与b就不是最优解了。
 *  所以原假设不成立
 *
 *
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