package leetcode.基础面试算法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//leetcode 912
//快排天生尾递归啊，优化尼玛。。
public class 递归快排 {

    public List<Integer> sortArray(int[] nums) {
        int l = 0, r = nums.length - 1;
        quickSort(nums, l, r);
        return Arrays.stream(nums).boxed().collect(Collectors.toList());
    }

    private void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }

        int pivot = nums[l];
        int p = l,q = r;
        while (p < q) {
            while (p < q && nums[q] >= pivot) {
                q--;
            }
            while (p < q && nums[p] <= pivot) {
                p++;
            }
            if (p < q) {
                int temp = nums[p];
                nums[p] = nums[q];
                nums[q] = temp;
            }
        }
        //此时p == q,要把此时的元素和pivot交换下
        nums[l] = nums[p];
        nums[p] = pivot;
        quickSort(nums, l, p - 1);//排基准元素左边的
        quickSort(nums, p + 1, r);//排序基准元素右边的
    }
}