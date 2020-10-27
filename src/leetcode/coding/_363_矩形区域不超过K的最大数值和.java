package leetcode.coding;

import java.util.HashMap;
import java.util.TreeSet;

public class _363_矩形区域不超过K的最大数值和 {

    // 这个题的思路和1074其实是一样的，1074是找面积等于 k 的，这个题是找小于等于 k 的最大矩形和
    // 但是也没给数据范围，直接莽的话 n^2 * m^2吧，不太合适，还是要转成先求行的前缀和，再枚举两列、枚举行来求
    // 如果是和1074一样用map的话，我怎么找到当前情况下，某个值 + curSum <= k,并且我要求这个符合条件的最大矩形和
    // 直接遍历，那就不好了，所以我们想二分，加快查找！那么这里就要用到TreeSet的ceiling了，之前在 300 题也用过
    // ceiling() 方法返回的是大于这个值的最小值，如果不存在返回null
    // 我们只要利用 ceiling(curSum - k)，得到这个值，判断它能否使得当前前缀与该值之间得和最接近K
    // 时间复杂度 m^2 nlogn

    //把题目转化一下，求矩阵连续行组成面积最大？什么意思呢？就是固定左右边界，只考虑行，在哪两个行之间组成矩形的面积最大。
    //这样我们就可以通过前缀和的方式，我们把每行的总和求得，通过前缀和的找差值最大就能找到最大矩阵了！

    //用set保存之前的子矩阵的和sum，然后在set中找出大于等于（sum - k）的最小值。set中都是存放的是startCol和endCol相同的矩阵的和
    //求出set中大于等于（curSum - k）最小值，设找到的数为p，则 p >= cur-k，必有 cur - p <= k,所有最小的p，使得cur-p的值最大，且该值小于等于k

    public int maxSumSubmatrix(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        //首先计算每一行的前缀和
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                mat[i][j] += mat[i][j - 1];
            }
        }
        int res = 0x80000000;
        TreeSet<Integer> set = new TreeSet<>();
        // 枚举j1列
        for (int j1 = 0; j1 < m; j1++) {
            // 枚举j2列
            for (int j2 = j1; j2 < m; j2++) {
                set.clear();
                set.add(0);
                int curSum = 0;
                int curMax = 0x80000000;
                // 枚举每一行
                for (int i = 0; i < n; i++) {
                    curSum += mat[i][j2] - (j1 > 0 ? mat[i][j1 - 1] : 0);
                    System.out.println(curSum);
                    Integer c = set.ceiling(curSum - k);
                    if (c != null) {
                        curMax = Math.max(curMax, curSum - c);
                    }
                    set.add(curSum);
                }
                res = Math.max(res, curMax);
                System.out.println();
            }
            System.out.println();
        }
        return res;
    }

    /**
     * 写法二，固定上下边界
     */


}