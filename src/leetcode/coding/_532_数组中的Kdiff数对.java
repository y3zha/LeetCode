package leetcode.coding;

import java.util.*;

public class _532_数组中的Kdiff数对 {


    // 对于任何一个n，寻找n - k和n + k，我们只需要记录diff对中左值(最小)即可
    // 存大值也可以，存小值存大值目的是去重
    public static int findPairs(int[] nums, int k) {
        if(k < 0) return 0;
        Set<Integer> set = new HashSet<>(); // 结果集 去重
        Set<Integer> vis = new HashSet<>();
        for (int num : nums) {
            if (vis.contains(num - k)) {
                set.add(num - k);
            }
            if (vis.contains(num + k)) {
                set.add(num);
            }
            vis.add(num);
        }
        return set.size();
    }

    public static void main(String[] args) {
        findPairs(new int[]{3, 1, 4, 1, 5}, 2);
    }

}