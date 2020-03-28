package leetcode.coding;

import java.util.Arrays;

/**
 * 算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * 思路：找下一个排列从后往前找，要找到一个降序，也就是后一个比前一个要大,nums[i]>nums[i-1]，
 *      比如1,2,3，，找到了第一个降序，就是3>2,然后要找到2后面比2大的最小的那个数，跟2换下位置，换好位置后，还要对2后面的数进行排序
 *      再比如[1,4,3,2],找到第一个降序,就是4>1，然后也就是从1开始，找到1后面比它的大数中最小的那个，也就是2，交换，变为[2,4,3,1]，
 *      接着，要对2后面的数进行排序变为[2,1,3,4]
 *      其实这里可以先对后面的排个序再交换的，反正后面的数都比它大
 */
public class _031_下一个排列 {

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            if (i == 0) {   //说明没找到,只能这个都是降序的，只要重新排序即可
                Arrays.sort(nums);
                return;
            } else {
                if (nums[i] > nums[i - 1]) {
                    //找比i-1大的最小的那个数,交换下位置
                    Arrays.sort(nums, i, n);    //给后面排个序，第一个nums[j] > nums[i - 1]的j位置上的元素就是我们想要的
                    for (int j = i; j < n; j++) {
                        if (nums[j] > nums[i - 1]) {
                            int temp = nums[j];
                            nums[j] = nums[i - 1];
                            nums[i - 1] = temp;
                            return;
                        }
                    }
                }
            }
        }
    }
}