package leetcode.coding;

import java.util.Stack;

public class _084_柱状图中最大的矩形 {

    /**
     * 这个题就和接雨水一样啊，我们就只要找到柱子左边第一个小于等于它的，然后右边找到第一根小于等于它的，然后求面积即可
     * 方法一：暴力，遍历两边 O n2
     * 方法二：单调栈
     * 方法三：分治
     *      可以先找到整个区间的最矮的柱子，根据木桶原理，计算上述矩形面积，然后递归地计算最矮柱子左右区间的矩形面积。
     *      平均O(nlogn)，如果数字是排序的，退化为O(n^2)
     * 方法四：分治+线段树。在方法四的基础上，解决最坏情况，就是用线段树，解决区间查询。
     */

    //暴力法，以i为中心，向左找第一个小于 heights[i] 的位置 left_i；向右找第一个小于于 heights[i] 的位置 right_i，即最大面积为 heights[i] * (right_i - left_i -1)
    public static int largestRectangleArea(int[] heights) {
        int res = 0;
        int n = heights.length;
        for (int i = 0; i < n; i++) {
            int left = i;
            int right = i;
            while (left >= 0 && heights[left] >= heights[i]) {
                left--;//left首先必自减1
            }
            while (right < n && heights[right] >= heights[i]) {
                right++;//right首先必自增1
            }
            res = Math.max(res, heights[i] * (right - left - 1));       //实际上就是right - left - 2 + 1

        }
        return res;
    }

    //使用单调递增栈，因为我们要找得是左边第一个小的和右边第一个小的，所以用递增栈，去峰留谷，
    //单调递增栈,时间复杂度O(2n)，for循环O(n)，里头每个元素只会进栈出栈一次，O(2n),总体来说，O(2n)
    public int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int res = 0;
        Stack<Integer> stack = new Stack<>();   //放的是下标
        //对于最后一个位置的处理，一定要把它pop出去。这边使用了一个-1来让它pop出去
        for (int i = 0; i <= height.length; i++) {
            int curHeight = i == height.length ? -1 : height[i];
            //如果当前来的比栈顶元素小，那就要开始pop了，pop 的时候也要计算面积,栈里存放的事下标
            while (!stack.isEmpty() && curHeight <= height[stack.peek()]) {
                int h = height[stack.pop()];
                //如果栈空了，那宽度是i。为什么呢？因为它是当前柱子i左边最小的，它左边也没了（也就是说都高于它，被弹光了）
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                res = Math.max(res, w * h);
            }
            stack.push(i);
        }
        return res;
    }
}