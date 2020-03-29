package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路：在合并区间的基础上做这个题就是非常容易的一件事，由于给定区间时按起始端点排序的，我们只要把后来的区间先插入到所有区间内再合并即可
 *
 * 这题和lintcode上不一样我们首先要找到插入位置这是不现实的，因为它不像lintcode是用list来存放的，而是二维数组
 * 所以先把二维数组中的数据放入到临时list中去，再插入
 */
public class _057_插入区间 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();    //存放结果
        List<int[]> list = new ArrayList<>();   //临时数组

        //先把二维数组中的数据放入到临时list中去，再插入
        for (int i = 0; i < intervals.length; i++) {
            list.add(intervals[i]);
        }

        //找插入位置
        int index = 0;
        while (index < list.size() && list.get(index)[0] < newInterval[0]) {
            index++;
        }
        list.add(index, newInterval);
        //合并
        res.add(list.get(0));
        int[] last = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (last[1] >= list.get(i)[0]) {
                last[1] = Math.max(last[1], list.get(i)[1]);
            } else {
                res.add(list.get(i));
                last = list.get(i);
            }
        }
        return res.toArray(new int[][]{});
    }
}