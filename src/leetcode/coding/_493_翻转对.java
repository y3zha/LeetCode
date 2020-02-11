package leetcode.coding;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * 实际上就是求逆序对，只不过多了个系数
 *
 * 求逆序对的思路有三种，都是nlogn的，分别是：归并排序、线段树、树状数组
 *
 * 归并排序：之前求逆序对的时候，我实在merge的时候求逆序对数，但是在这里是不适用的，可能导致 少算、漏算
 *          这里就需要在merge之前就进行统计，因为merge之后统计的话，原来的顺序就没有了！
 */
public class _493_翻转对 {

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 2, 4, 6};
        _493_翻转对 test = new _493_翻转对();
        // System.out.println(test.reversePairs2(a));

        // reversePairs(a);
    }

    //方法一：归并排序
    public static int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private static int mergeSort(int[] nums, int left, int right) {
        int count = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;
            count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
            //i为前半部分指针，j为后半部分指针
            int j = mid + 1;
            //对于前半段任意位置上的数，都要和后面半段的数逐个比较，前后半段已经是排好序的了
            for (int i = left; i <= mid; i++) {
                while (j <= right && nums[i] > (long) 2 * nums[j]) {    //放置溢出
                    j++;
                }
                count += j - mid - 1;
            }
            merge(nums, left, mid, right);
        }
        return count;
    }

    //不要再merge的时候统计
    private static void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int p = left;   //前半段指针
        int q = mid + 1;    //后半段指针
        int index = 0;  //temp的指针
        while (p <= mid && q <= right) {
            temp[index++] = nums[p] <= nums[q] ? nums[p++] : nums[q++];
        }
        while (p <= mid) {
            temp[index++] = nums[p++];
        }
        while (q <= right) {
            temp[index++] = nums[q++];
        }
        System.arraycopy(temp, 0, nums, left, temp.length);
    }

    /**
     * 树状数组写法
     * 根据题意分析，i < j 且 nums[i] > 2*nums[j]，这个条件我们可以解读为，当前元素后方有多少元素比这个元素的一半还小
     * 推荐博客 https://blog.csdn.net/gonganDV/article/details/88817416、https://blog.csdn.net/S_999999/article/details/99076076
     *
     *
     * 首先来看下怎么求【逆序对】的！！！！！！！！
     * 简要思路：
     *      我们知道逆序对的定义是 i < j 且 a[i] > a[j]
     *【为什么要用树状数组】
     *      当数据的范围较小时，比如 下标最大为len，那么我们可以开一个数组c[len],来记录前面数据的出现情况，初始化为0；当数据a出现时，就令c[a]=1。
     *      这样的话，欲求某个数a的逆序数，只需要算出在当前状态下c[a+1,len]中有多少个1,因为这些位置的数在a之前出现且比a大。
     *      但是若每添加一个数据a时，就得从a+1到 len搜一遍，复杂度太高了
     *
     *【怎么用树状数组】
     *      用树状数组能够很好的解决这个问题。把数按照数组中的顺序一个个插入到树状数组中，每插入一个数，就统计比它小的数的个数
     *      对应的逆序为 i - query(c[i])，其中 i 为当前已经插入的数的个数， query(c[i]）为比 c[i] 小的数的个数，i- query(c[i]) 即比c[i] 大的个数， 即逆序的个数。
     *      最后需要把所有逆序数求和，就是在插入的过程中边插入边求和.
     *
     *【求逆序对要怎么写】
     *      构建一个值的范围是1~n的BIT，按照j = 0，1，2，···，n-1的顺序进行如下操作。
     *          1、res += j - query(c[i])
     *          2、BIT c[j] 位置加上 1
     *
     *【更进一步】
     *      当数据较大时，直接开数组显然是不行了，这是的解决办法就是离散化。
     *      假如现在有一些数：1234 98756 123456 99999 56782,由于1234是第一小的数，所以num[1]=1;
     *      依此，有 num[5] = 2, num[2] = 3, num[4] = 4, num[3] = 5; 这样转化后并不影响原来数据的相对大小关系
     *
     * 所以有了我这篇博客 https://blog.csdn.net/weixin_44424668/article/details/104267735
     *
     *
     *【再回到这个题】
     *      这个题要的是 i < j 且 nums[i] > 2*nums[j] 的翻转对，也就是 对于第 i 个元素 nums[i]，我们希望知道有多少个已经遍历过的元素的两倍仍然小于 nums[i]
     *      所以从后往前遍历原数组，后面的先插入树状数组，这样就能知道当前元素 后面 大于它两倍的有多少个了
     *
     *      另外，给定的用例有个[10000000,99999999]这样子的，那这里就需要离散化了
     *
     *      1、首先对所有 num 以及 2*num + 1 排序后做映射 （给定数组的长度不会超过50000。）
     *      2、然后从后向前遍历，并不断更新数组
     *
     */

    class BIT{
        int n;  //数组长度
        int[] c;

        public BIT(int n) {
            this.n = n;
            c = new int[n + 1]; //树状数组下标必须从1开始
        }

        //增加操作
        public void add(int index, int val) {
            //树状数组除了更新它自己还要更新它父亲
            while (index <= n) {
                c[index] += val;
                index = index + lowbit(index);
            }
        }

        //查询,获得当前位置前面有多少个比它小的数
        public int query(int index) {
            int res = 0;
            while (index > 0) {
                res += c[index];
                index -= lowbit(index);
            }
            return res;
        }

        private int lowbit(int x) {
            return x & (-x);
        }
    }

    //TODO
    // public int reversePairs2(int[] nums) {
    //     Map<Long, Integer> map = new TreeMap<>(Collections.reverseOrder());
    //     for (int num : nums) {
    //         map.put((long) num, 1);
    //         map.put((long) (2 * num + 1), 1);
    //     }
    //     int rank = 1;
    //     for (Long num : map.keySet()) {
    //         map.put(num, rank++);
    //     }
    //     int res = 0;
    //     BIT bit = new BIT(100100);
    //     for (int i = nums.length - 1; i >= 0; i--) {
    //         res += bit.query(map.get((long) nums[i]));
    //         bit.add(map.get((long) 2 * nums[i] + 1), 1);
    //     }
    //     return res;
    // }
}