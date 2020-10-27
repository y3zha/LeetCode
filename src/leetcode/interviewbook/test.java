package leetcode.interviewbook;

import java.util.Arrays;

public class test {

    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 最后一个参数表示我们要找的是下标为k-1的数
        return quickSearch(arr, 0, arr.length - 1, k - 1);
    }

    private int[] quickSearch(int[] nums, int l, int r, int k) {
        // 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于k就返回j以及j左边所有的数；
        int j = partition(nums, l, r);
        if (j == k) {
            return Arrays.copyOf(nums, j + 1);
        }
        // 否则根据下标j与k的大小关系来决定继续切分左段还是右段。
        return j > k ? quickSearch(nums, l, j - 1, k): quickSearch(nums, j + 1, r, k);
    }

    // 快排切分，返回下标j，使得比nums[j]小的数都在j的左边，比nums[j]大的数都在j的右边。
    private int partition(int[] nums, int l, int r) {
        int v = nums[l];
        int i = l, j = r ;
        while (i < j) {
            while (i < j && nums[i] < v) i++;
            while (i < j && nums[j] > v) j--;
            if (i < j) {
                int t = nums[j];
                nums[j] = nums[i];
                nums[i] = t;
            }
        }
        nums[l] = nums[j];
        nums[j] = v;
        return j;
    }
}