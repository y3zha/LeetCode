package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _228_汇总区间 {

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n;) {
            int start = i;
            while (i < n - 1 && nums[i] + 1 == nums[i + 1]) {
                i++;
            }
            //说明只有它一个
            if (start == i) {
                res.add(String.valueOf(nums[start]));
            } else {
                String temp = nums[start] + "->" + nums[i];
                res.add(temp);
            }
            i++;
        }
        return res;
    }
}