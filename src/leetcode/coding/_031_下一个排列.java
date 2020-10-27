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

    /*
    O（1）写法
     我们观察到对于任何给定序列的降序，没有可能的下一个更大的排列。
     [9, 5, 4, 3, 1] 这个就没有下一个排列

     我们需要从右边找到第一对两个连续的数字 a[i] 和 a[i-1]，它们满足 a[i-1]<a[i]。现在，没有对 a[i-1] 右侧的重新排列可以创建更大的排列
     因为该子数组由数字按降序组成。因此，我们需要重新排列 a[i-1] 右边的数字，包括它自己。
    什么样子的重新排列将产生下一个更大的数字呢？我们想要创建比当前更大的排列。
     因此，我们需要将数字 a[i-1] 替换为位于其右侧区域的数字中比它更大的数字，例如 a[j]

     交换数字 a[i-1] 和 a[j] 。我们现在在索引 i-1  处有正确的数字。
     但目前的排列仍然不是我们正在寻找的排列。
     我们需要通过仅使用 a[i-1] 右边的数字来形成最小的排列。 因此，我们需要放置那些按升序排列的数字，以获得最小的排列。

     但是，请记住，在从右侧扫描数字时，我们只是继续递减索引直到我们找到 a[i] 和 a[i-1] 这对数。其中，a[i] > a[i-1] 。
     因此，a[i-1] 右边的所有数字都已按降序排序。此外，交换 a[i-1] 和 a[j] 并未改变该顺序。
     因此，我们只需要反转 a[i-1] 之后的数字，以获得下一个最小的字典排列。

    例如
     [9,1,3,5,4] -> 找到第一对连续的 a[i-1] < a[i] : [3,5]
     3 右侧比他大的里面最小的是 4，交换一下变为 [9,1,4,5,3]
     [5,3,x,x,x],这其实依旧是一个降序的序列，反转一下即可
 */
    public void nextPermutation1(int[] nums) {
        int n = nums.length;
        int i = n - 1;
        while (i >= 1 && nums[i - 1] >= nums[i]) {
            i--;
        }
        // 如果 i >= 1此时 nums[i-1] < nums[i]
        if (i >= 1) {
            // 在i-1右边找到比他大的中最小的哪个元素的位置，从后往前找，因为从后往前是升序
            int j = n - 1;
            while (j >= i && nums[j] <= nums[i - 1]) {
                j--;
            }
            swap(nums, i - 1, j);
        }
        // reverse 从i开始的即可
        reverse(nums, i);
    }

    private void reverse(int[] nums, int st) {
        int start = st, end = nums.length - 1;
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}