package leetcode.coding;

/**
 * 方法一：直接遍历，看有没有这个元素，O(n)
 */
public class _081_搜索旋转排序数组II {

    //直接拿33题的代码过来改，发现有两个测试用例过不去 [1,3,1,1,1] 3
    //其实这边要处理个nums[start] == nums[mid]的情况
    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[mid] < nums[start]) {      //如果切在了后半部分,这边在纸上画一下就知道了
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else if (nums[mid] > nums[start]) {        //如果切在了前半部分
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {        //nums[start] == nums[]mid的情况
                start++;    //往前移一下把
            }
        }

        //double check
        if (nums[start] == target) {
            return true;
        }
        if (nums[end] == target) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 1, 1, 1};
        int[] nums2 = {1, 1, 3, 1};
        search(nums1, 3);
    }
}