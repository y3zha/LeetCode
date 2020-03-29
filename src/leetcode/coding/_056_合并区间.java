package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 合并重叠区间
 * 输入:  [(1,3),(2,6),(8,10),(15,18)]
 * 输出: [(1,6),(8,10),(15,18)]
 *
 * 思路：
 *      直接合并，比如[1,3] [2,6] 合并成[1,6] ，然后不断合并。这话说了跟没说一样，其实意思是这样的
 *      我们判断两个区间是不是重叠，只要前面一个区间的右端点大于等于后面一个区间的左端点，那这就可以合并了。
 *      怎么合并比较方便。。当然是先按照区间的左端点从小到大排个序，然后遍历一遍依次合并即可。
 *      如果当前区间和下一个区间不能合并，直接看下一个区间，当前区间和下一个能合并，就合并，合并后的区间作为当前区间。
 *
 * 参考leetcode 156
 */
public class _056_合并区间 {

    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{};
        }
        //先把这个区间按照左端点升序排一下
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        List<int[]> res = new ArrayList<>();
        //先把第一个区间放进去
        res.add(intervals[0]);
        int[] last = intervals[0];  //指向当前res中的最后一行数组
        for (int i = 1; i < intervals.length; i++) {
            int end = last[1];
            if (end >= intervals[i][0]) {   //end>=后一个区间的start，可以合并
                // 直接修改last的end即可
                last[1] = Math.max(last[1], intervals[i][1]);   //这边持有的是上一个数组的引用，这里改了 res中的数组 的值也会修改
            } else {    //不能合并，直接添加，然后看下一个
                res.add(intervals[i]);
                last = intervals[i];
            }
        }
        return res.toArray(new int[][]{});  //由于list中装的是一维数组，所以这样一个个结果就变成二维数组了
    }

    public static void main(String[] args) {
        int[][] in = {{1, 3}, {2, 6}, {8, 10}};
        merge(in);
    }

}