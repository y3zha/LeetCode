package leetcode.coding;

/**
 * 这个题有通用模版
 *
 * 思路，题目中限定每个数字出现最多两次，那么就是看第三个数字和【往它前面数两个】的数字相不想等(更新的数组)，如果不想等，那这个位置可以放它，如果相等，不能放，跳过吧
 *      //扩展为k哥也是一样的思路
 */
public class _080_删除排序数组中的重复项II {

    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }

        int index = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[index - 2] == nums[i]) {
                continue;
            }
            nums[index++] = nums[i];
        }
        return index;
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 1, 2, 2, 3};
        removeDuplicates(a);
    }
}