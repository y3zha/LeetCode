package leetcode.interviewbook;

public class 面试题17_10_主要元素 {

    //摩尔投票
    public int majorityElement(int[] nums) {
        int cand = 0;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {

            if (cnt == 0) {
                cand = nums[i];
            }
            cnt += cand == nums[i] ? 1 : -1;
        }
        return cand;
    }
}