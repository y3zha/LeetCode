package leetcode.competition.week178;

public class _5344_有多少小于当前数字的数字 {

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < nums.length; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && nums[j] < nums[i]) {
                    cnt++;
                }
            }
            res[i] = cnt;
        }
        return res;
    }
}