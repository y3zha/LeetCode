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

    //做完这个做315题
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

    // 下面的有问题，因为mid - p + 1，比如后半段数组，p其实不为0！，如果设置p为0，就会导致多加了
    static class Solution {
        int count;
        public int reversePairs(int[] nums) {
            count = 0;
            mergeSort(nums,0,nums.length - 1);
            return count;
        }

        private int[] mergeSort(int[] nums,int left,int right){
            if(left > right){
                return new int[]{};
            }
            if(left == right){
                return new int[]{nums[left]};
            }
            int mid = left + (right - left) / 2 ;
            int[] arr1 = mergeSort(nums,left,mid);
            int[] arr2 = mergeSort(nums,mid + 1,right);
            // merge
            int n = arr1.length,m = arr2.length;
            int[] arr = new int[n + m];
            int idx = 0,p = 0, q = 0;
            while(p < n && q < m){
                if(arr1[p] > arr2[q]){
                    count += mid - p + 1;
                    arr[idx++] = arr2[q++];
                }else{
                    arr[idx++] = arr1[p++];
                }
            }
            while(p < n) arr[idx++] = arr1[p++];
            while(q < m) arr[idx++] = arr2[q++];
            return arr;
        }
        public static void main(String[] args) {
            Solution solution = new Solution();
            solution.reversePairs(new int[]{7, 5, 6, 4});
        }
    }

    //过了的
    class Solution2 {
        public int reversePairs(int[] nums) {
            return mergeSort(nums,0,nums.length - 1);
        }

        private int mergeSort(int[] nums, int left, int right) {
            int count = 0;
            if (left < right) {
                int mid = left + (right - left) / 2;
                count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
                //i为前半部分指针，j为后半部分指针
                int j = mid + 1;
                //对于前半段任意位置上的数，都要和后面半段的数逐个比较，前后半段已经是排好序的了
                for (int i = left; i <= mid; i++) {
                    while (j <= right && nums[i] > nums[j]) {    //防止溢出
                        j++;
                    }
                    count += j - mid - 1;
                }
                merge(nums, left, mid, right);
            }
            System.out.println(count);
            return count;
        }

        //不要再merge的时候统计
        private void merge(int[] nums, int left, int mid, int right) {
            int[] temp = new int[right - left + 1];
            int p = left;   //前半段指针
            int q = mid + 1;    //后半段指针
            int index = 0;  //temp的指针
            while (p <= mid && q <= right) {
                temp[index++] = nums[p] <= nums[q] ? nums[p++] : nums[q++];
            }
            while (p <= mid) temp[index++] = nums[p++];
            while (q <= right) temp[index++] = nums[q++];
            System.arraycopy(temp, 0, nums, left, temp.length);
        }

    }



}