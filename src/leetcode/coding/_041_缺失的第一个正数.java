package leetcode.coding;

public class _041_缺失的第一个正数 {

    /**
     * 相当于我们自己编写哈希函数，这个哈希函数的规则特别简单，那就是数值为 i 的数映射到下标为 i - 1 的位置
     * 比如数值 2 应该放在下标1的位置上，数值 4 应该放在下标 3 的位置上
     */
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // 满足在范围内的，但是不在自己位置上的的，一直交换
            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                // 例如：数值 3 应该放在索引 2 的位置上
                swap(nums, nums[i] - 1, i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 都正确则返回数组长度 + 1
        return n + 1;
    }
    private static void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 4};
        firstMissingPositive(nums);
    }
}