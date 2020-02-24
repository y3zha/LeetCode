package leetcode.swordtooffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 面试题51_数组中的逆序对_TODO {

    /**
     * 归并排序
     *
     * 举个例子: A=[1,4,6,7,9]  B=[2,3,5,10,13,21].
     * 在Merge中发现当前i号元素4比2大,那么4的逆序数需要+1,又因6,7,9都排在4后面,那么6,7,9的逆序数也应该+1
     * 所以总体的逆序数应该加上前半段最后一个位置，也就是mid ，mid-i+1.(如果i号元素比B[j]小（比如4比5小）,无法确定逆序数的变化,不作任何修改).
     */
    int count = 0;

    public int reversePairs(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(nums, temp, 0, nums.length - 1);
        return count;
    }

    private void mergeSort(int[] nums, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(nums, temp, left, mid);
            mergeSort(nums, temp, mid + 1, right);
            merge(nums, temp, left, mid, right);
        }
    }

    private void merge(int[] nums, int[] temp, int left, int mid, int right) {
        int p = left;
        int q = mid + 1;
        int index = 0;
        while (p <= mid && q <= right) {
            if (nums[p] <= nums[q]) {
                temp[index++] = nums[p++];
            } else {
                count += mid - p + 1;   //为什么呢，见注释
                temp[index++] = nums[q++];
            }
        }
        while (p <= mid) {
            temp[index++] = nums[p++];
        }
        while (q <= right) {
            temp[index++] = nums[q++];
        }
        index = 0;
        while (left <= right) {
            nums[left++] = temp[index++];
        }
    }


    /**
     * 树状数组
     */
    class BIT{
        int n;
        int[] c;

        public BIT(int n) {
            this.n = n;
            c = new int[n + 1];
        }

        public void add(int i, int val) {
            while (i <= n) {
                c[i] += val;
                i += i & -i;
            }
        }

        public int query(int i) {
            int res = 0;
            while (i > 0) {
                res += c[i];
                i -= i & (-i);
            }
            return res;
        }
    }

    public int reversePairs2(int[] nums) {
        //离散化
        int n = nums.length;
        if (n == 0) return 0;
        int[] arr = Arrays.stream(nums).distinct().sorted().toArray();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i + 1);
        }
        int res = 0;
        BIT bit = new BIT(n);
        for (int i = 1; i <= nums.length; i++) {
            bit.add(map.get(nums[i - 1]), 1);
            res += i - bit.query(map.get(nums[i - 1]));
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {7, 5, 6, 4};
        int[] b = {4, 5, 6, 7};
        面试题51_数组中的逆序对_TODO test = new 面试题51_数组中的逆序对_TODO();
        test.reversePairs2(a);
    }

}