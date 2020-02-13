package leetcode.swordtooffer;

import java.util.ArrayList;
import java.util.List;

public class 面试题57_和为s的连续正数序列II {

    /**
     * 双指针
     */

    public int[][] findContinuousSequence(int target) {
        List<List<Integer>> lists = new ArrayList<>();
        int i = 1, j = 1;
        int sum = 0;
        while (i < target) {
            sum += j;
            if (sum > target) {
                i++;
                sum = 0;
                continue;
            }
        }
    }

}