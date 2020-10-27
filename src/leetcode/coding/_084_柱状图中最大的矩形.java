package leetcode.coding;

import java.util.Stack;

public class _084_柱状图中最大的矩形 {

    /**
     * 这个题就和接雨水一样啊，我们就只要找到柱子左边第一个小于它的，然后右边找到第一根小于它的，然后求之间面积即可
     * 方法一：暴力，遍历两遍 O n2
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
    public static int largestRectangleArea2(int[] height) {
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
                //如果栈空了，那宽度是i。为什么呢？因为它是所有柱子里最小的
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                res = Math.max(res, w * h);
            }
            stack.push(i);
        }
        return res;
    }
    /*
    cpp
    // 更简洁的写法,非严格单调递增栈，栈顶左边存的都是小于等于它的
    // 当来了一个元素，小于栈顶，那么栈顶元素到这个位置之间的矩形面积就能计算了，栈里存的都是小于等于它的，新来的也是小于它的，那么是可以计算这块面积的
    int largestRectangleArea3(vector<int> &heights) {
        int res = 0;
        int n = heights.size();
        stack<int> st;
        //对于最后一个位置的处理，一定要把它pop出去。这边使用了一个-1来让它pop出去
        for (int i = 0; i <= n; i++) {
            int curHeight = i == n ? -1 : heights[i];
            // 如果栈顶元素大于来的元素，这个来的其实就是栈顶元素右边第一个小于它的，而栈里存的是它左边第一个小于它的下标
            // 那就要开始pop了，pop 的时候也要计算面积,栈里存放的事下标
            while (!st.empty() && heights[st.top()] > curHeight) {
                int h = heights[st.top()];
                st.pop();
                //如果栈空了，那宽度是i。为什么呢？因为它是所有柱子里最小的
                int w = st.empty() ? i : i - st.top() - 1;
                res = max(res, w * h);
            }
            st.push(i);
        }
        return res;
    }
     */

    // 实际上就是要找到当前位置左边第一个比他小的数的下标以及它右边第一个比他小的数的下标，这就是要用单调栈
    // 要找左边第一个比他小的，从左往右遍历，如果栈顶是大于等于它的，那么栈顶就得滚蛋，直到栈为空或者栈顶符合条件
    // 要找右边第一个比他小的，从右往左遍历，如果栈顶是大于等于它的，那么栈顶就得滚蛋，直到栈为空或者栈顶符合条件
    // 下面是一个比较复杂的写法

    public int largestRectangleArea3(int[] heights) {
        int n = heights.length;
        Stack<Integer> s1 = new Stack<>();
        int[] a = new int[n];
        int[] b = new int[n];
        // 预处理：找到每个位置左边第一个比他小的数字的位置
        for (int i = 0; i < n; i++) {
            // 如果栈顶不符合就滚蛋
            while (!s1.isEmpty() && heights[s1.peek()] >= heights[i]) {
                s1.pop();
            }
            // 如果左边没有比他小的
            if (s1.isEmpty()) {
                a[i] = -1;
            } else {
                a[i] = s1.peek();
            }
            s1.push(i);
        }
        s1.clear();
        // 预处理：找到每个位置右边第一个比他小的数字的位置
        for (int i = n - 1; i >= 0; i--) {
            while (!s1.isEmpty() && heights[s1.peek()] >= heights[i]) {
                s1.pop();
            }
            if (s1.isEmpty()) {
                b[i] = n;
            } else {
                b[i] = s1.peek();
            }
            s1.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, heights[i] * (b[i] - a[i] - 2 + 1));
        }
        return ans;
    }



}