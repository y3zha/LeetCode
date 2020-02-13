package leetcode.swordtooffer;

public class 面试题11_旋转数组的最小数字 {

    /**
     * 普通O(n)就能解决
     *
     * 如果用二分得怎么写呢？
     * 正常思路就是找在前半部分还是后半部分
     * 但是有这种情况，比如[3,3,1,3]，我们记录了最后一个，last = 3，但是取mid，也是3，这就不知道该往左缩小区间还是往右缩小区间
     * 以前缩小区间都是end = mid这样，但若真出现这样的额情况，只能 end-- 这样缩小区间。最坏情况下时间复杂度还是O（n）
     */
    public int minArray(int[] nums) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == nums[end]) {
                end--;
            } else if (nums[mid] < nums[end]) { //说明在前半部分
                end = mid;
            } else if (nums[mid] > nums[end]) { //说明在后半部分
                start = mid;
            }
        }
        //double check
        if (nums[start] < nums[end]) {
            return nums[start];
        }
        return nums[end];
    }
}