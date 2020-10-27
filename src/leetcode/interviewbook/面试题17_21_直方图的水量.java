package leetcode.interviewbook;

import java.util.Stack;

public class 面试题17_21_直方图的水量 {
    /**
     * 这不就是接雨水那个题吗
     */

    //最笨的方法，对每，找它左右最高的高度，然后计算，o(n2)
    public int trap(int[] height) {
        int res = 0;
        int n = height.length;
        for (int i = 1; i < n - 1; i++) {
            int lMax = 0;
            int rMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                lMax = Math.max(lMax, height[j]);
            }
            for (int j = i + 1; j < n; j++) {
                rMax = Math.max(rMax, height[j]);
            }
            int h = Math.min(lMax, rMax);
            if (h > height[i]) {
                res += h - height[i];
            }
        }
        return res;
    }

    //改动态规划，先求好每个位置左右的最大值，拿来直接用就行了
    public int trap2(int[] height) {
        int n = height.length;
        int[] f1 = new int[n];
        int[] f2 = new int[n];
        //求当前位置左边、右边最大值
        for (int i = 1; i < n - 1; i++) {
            f1[i] = Math.max(f1[i - 1], height[i - 1]);
        }
        for (int i = n - 2; i >= 1; i--) {
            f2[i] = Math.max(f2[i + 1], height[i + 1]);
        }
        int res = 0;
        for (int i = 1; i < n - 1; i++) {
            int h = Math.min(f1[i], f2[i]);
            res += h > height[i] ? h - height[i] : 0;
        }
        return res;
    }

    //由于动态规划中每个值只用到了了一次，可以改成双指针，必须要用矮的一方灌水
    //liweiwei 双指针     //双指针图解 https://leetcode-cn.com/problems/trapping-rain-water/solution/dong-tai-gui-hua-shuang-zhi-zhen-tu-jie-by-ml-zimi/
    //每个位置得左边看一下最大值，右边看一下最大值。
    //有两种情况最特殊，就是最左边（索引为 1，只用看右边）和最右边（索引为 len - 1，只用看左边），它们只用看一边就够了，事实上连扫描都不用扫，就能确定一个位置的存水量。
    //当一根柱子左边柱子的高度小于右边柱子的高度时，肯定看左边的啊，如果它右边柱子更矮，就是看右边的就好了
    //
    // 只要rmax > lmax 积水高度将由lmax决定，反之亦然。如果右端高了，左端不就矮了吗？这就更依赖于从左到右这个方向

    public int trap3(int[] height) {
        int n = height.length;
        int res = 0;
        int i = 1, j = n - 2;    //左右指针
        int lMax = 0, rMax = 0; //左右高度
        while (i <= j) {
            //从左到右更新
            if (height[i - 1] < height[j + 1]) {    //lmax和rmax比较
                //更新lMax
                lMax = Math.max(lMax, height[i - 1]);
                res += lMax > height[i] ? lMax - height[i] : 0;
                i++;
            } else {
                //更新rMax
                rMax = Math.max(rMax, height[j + 1]);
                res += rMax > height[j] ? rMax - height[j] : 0;
                j--;
            }
        }
        return res;
    }

    //栈，维护一个单调递减栈，符合则继续放入，碰到高的，就说明有积水了，要开始pop，直到碰到比这堵墙搞的，或是空，就把这堵墙放进去
    public int trap4(int[] height) {
        int n = height.length;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        return res;


    }


}