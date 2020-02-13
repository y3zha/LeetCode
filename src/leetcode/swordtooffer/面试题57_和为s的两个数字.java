package leetcode.swordtooffer;

public class 面试题57_和为s的两个数字 {

    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;

        while (i < j) {
            if (nums[i] + nums[j] == target) {
                return new int[]{nums[i], nums[j]};
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[]{-1, -1};
    }
}