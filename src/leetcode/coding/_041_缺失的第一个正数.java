package leetcode.coding;

/**
 * 要求时间复杂度O（n），空间为常数
 *
 * 桶排序思想，一个萝卜一个坑分两步
 * 第一步：将数据放到他本来应该在的位置，比如5应该放在index为4上, 4应该放在index为3上，也就是nums[index] = index+1
 *        那么我们就遍历这个数组，看这个位置上有没有放正确的数，如果不是，则一直找，直到找到这个正确的数交换过来，如果没找到，就往后走
 * 第二步：遍历数组，如果哪个位置nums[i]!=i+1,则他就是缺失的数。
 *
 * 我们可以把数组进行一次“排序”，“排序”的规则是：如果这个数字 i 落在“区间范围里”，i 就应该放在索引为 i - 1 的位置上，下面具体解释。
 * 1、数字 i 落在“区间范围里”；
 * 例如：[3, 4, -1, 1]，一共 4 个数字，那么如果这个数组中出现 “1”、“2”、“3”、“4”，就是我们重点要关注的数字了；
 * 又例如：[7, 8, 9, 11, 12] 一共 5 个数字，每一个都不是 “1”、“2”、“3”、“4”、“5” 中的一个，因此我们无须关注它们；
 * 这句话也可以这么说 “索引为 i 的位置上应该存放的数字是 i + 1”。
 * 数字 1 应该放在索引为 0 的位置上，数字 3 应该放在索引为 2 的位置上，数字 4 应该放在索引为 3 的位置上。一个数字放在它应该放的位置上，我们就认为这个位置是“和谐”的，看起来“顺眼”的。
 * 按照以上规则排好序以后，缺失的第 1 个正数一下子就看出来了，那么“最不和谐”的数字的索引 + 1，就为所求。那如果所有的数字都“和谐”，数组的长度 + 1 就为所求。
 *
 * https://leetcode-cn.com/problems/first-missing-positive/solution/tong-pai-xu-python-dai-ma-by-liweiwei1419/
 */
public class _041_缺失的第一个正数 {

    public static int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            //落在区间范围内的才需要把它归位，如果像碰到-1这样子的往后走 ，不管它
            //直到nums[nums[i] - 1] = nums[i]时候跳出循环，也就是 nums[i] - 1 = i的时候，（比如1放到了0的位置上的时候，就是放对了位置）
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;
    }

    private static void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
        System.out.println("hello");
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 4};
        firstMissingPositive(nums);
    }
}