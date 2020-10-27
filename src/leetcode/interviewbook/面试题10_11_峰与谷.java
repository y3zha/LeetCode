package leetcode.interviewbook;

public class 面试题10_11_峰与谷 {
    /**
     * 摆动排序
     */

    /**
     * wiggle sort 摆动排序
     * 思路一：首先排序，排成[1,2,3,4,5,6]，然后利用双指针,i指向1,j指向j，先取i再取j，这样循环取，直到结束，就是1，4，2，5，3，6。因为排序左半部分一定是小于右半部分的
     * 思路三：从左到右扫描一遍,不满足条件的交换就好了。奇数位置上的数都是小于偶数位置上的数，偶数位置上的数都是大于奇数位置上的数
     *      证明前后交换了 nums[i] 和 nums[i - 1] 以后，nums[i - 2] 不会和 nums[i] 形成逆序
     *      原来：... nums[i - 2],nums[i - 1],nums[i]     后来：... nums[i - 2], nums[i], nums[i - 1]
     *      假设i为奇数，如果原来nums[i-2] <= nums[i-1]，那么交换的条件是nums[i] < nums[i-1]，这本身就符合，不用交换
     *                 如果原来nums[i-2] >= nums[i-1]，那么奇数交换的条件依旧是nums[i] < nums[i-1]，也是符合的。。。所以。。。
     *
     */
    public void wiggleSort(int[] nums) {

    }

}