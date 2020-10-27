package leetcode.swordtooffer;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 面试题40_最小的k个数 {

    /**
     * 给定一个数字找出其中最小的k个数
     * 方法一：排序取前k个
     * 方法二：quick select算法
     * 方法三：优先队列
     */
    //方法三
    public int[] getLeastNumbers(int[] arr, int k) {
        //搞一个小顶堆，默认就是
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //都放进去
        for (int i : arr) {
            pq.offer(i);
        }
        //只要k个
        int[] res = new int[k];
        int index = 0;
        //poll k 个即可
        while (k > 0 && !pq.isEmpty()) {
            res[index++] = pq.poll();
            k--;
        }
        return res;
    }

    public int[] getLeastNumbers2(int[] arr, int k) {
        quickselect(arr, 0, arr.length - 1, k);
        return Arrays.copyOfRange(arr, 0, k);
    }

    private void quickselect(int[] nums, int start, int end, int k) {
        while (start < end) {
            // int pivot = nums[start];
            int mid = start + (end - start) / 2;
            if (nums[start] > nums[end]) swap(nums, start, end);
            if (nums[mid] > nums[end]) swap(nums, mid, end);
            if (nums[mid] > nums[start]) swap(nums, mid, start);
            int pivot = nums[start];

            int i = start, j = end;
            while (i <= j) {
                //按照升序来的
                while (i <= j && nums[i] < pivot) {
                    i++;
                }
                while (i <= j && nums[j] > pivot) {
                    j--;
                }
                if (i <= j) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                    j--;
                }
            }
            //如果i太后面了，需要往前走，否则往后走
            if (i >= k) {
                end = i - 1;
            } else {
                start = i;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * BFPRT算法 https://blog.csdn.net/LaoJiu_/article/details/54986553
     * 通过修改快速排序中主元的选取方法可以降低快速排序在最坏情况下的时间复杂度（即BFPRT算法），并且我们的目的只是求出前k，故递归的规模变小，速度也随之提高。
     * 下面来简单回顾下快速排序的过程，以升序为例：
     * （1）：选取主元（首元素，尾元素或一个随机元素）；
     * （2）：以选取的主元为分界点，把小于主元的放在左边，大于主元的放在右边；
     * （3）：分别对左边和右边进行递归，重复上述过程。
     *
     * BFPRT算法步骤如下：
     * （1）：选取主元；
     *   （1.1）：将n个元素划分为1/5个组，每组5个元素，若有剩余，舍去；
     *   （1.2）：使用插入排序找到1/5个组中每一组的中位数；
     *   （1.3）：对于（1.2）中找到的所有中位数，调用BFPRT算法求出它们的中位数，作为主元；
     * （2）：以（1.3）选取的主元为分界点，把小于主元的放在左边，大于主元的放在右边；
     * （3）：判断主元的位置与k的大小，有选择的对左边或右边递归。
     */





    public static void main(String[] args) {
        int[] arr = {0,1,1,2,4,4,1,3,3,2};

    }

}