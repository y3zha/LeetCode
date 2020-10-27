package leetcode.coding;

/**
 * hard
 * <p>
 * 中位数：指的是该数左右个数相等。奇数情况下只有一个，偶数情况下有两个
 */
public class _004_寻找两个有序数组的中位数 {

    /**
     * 方法1. 将两个有序数组合并，时间复杂度(m+n)，空间复杂度(m+n)，查找中间的那个
     * 方法2. 也是合并的策略，写一个循环，判断是否到了中位数的位置，如果到了就返回结果
     *       用 len 表示合并后的长度，如果是奇数，比如长度为3，，我只要知道第 (len+1)/2 个数即可，遍历 len/2 + 1次
     *                            如果是偶数，比如长度为4，我们要知道两个数，第2个和第3个，也就是 len/2 和 len/2 + 1，也是遍历 len/2+1次
     *       时间复杂度也是 O(m+n),但是空间复杂度 O(1)
     * 方法3. quickSelect 时间复杂度 O(log(m+n)) , 空间复杂度O(1)
     */


    // 方法1不写了
    // 方法2
    public static double findMedianSortedArrays2(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;

        int left = -1;  // left保存前一次循环的结果，因为偶数的话要用到两个值
        int right = -1; // right保存当前循环的结果
        int p = 0, q = 0;   // 两个数组的指针
        // j奇数和偶数都是遍历 len/2 + 1 次
        for (int i = 0; i < len / 2 + 1; i++) {
            left = right;   // 保存上次循环的值
            // 循环什么时候后移
            // 如果 A数组还没到最后一个，并且 （B数组到最后一个了，或者是B数组的当前位置的值更大），那么A数组指针后移
            if (p < m && (q >= n || A[p] < B[q])) {
                right = A[p++];
            } else {
                right = B[q++];
            }
        }

        if ((len & 1) == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }



    /**
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/javashi-pin-jiang-jie-xi-lie-median-of-two-sorted-/
     * 优化下使用quick select log(m+n)，类似二分查找
     * 1. 他不是每次取中间的数去比，而是每次删掉 k/2 个数字，最后只有一个数的情况就非常好比了
     * 2. 删哪部分？需要证明被删除的 k/2个数都不是我们要找的
     *    比如 我们要找第7个数 k=7，现在要删除3 7/2=3个数
     *    数组 1 3 5 7 9 11 12 13 14
     *    数组 2 4 6 8 10
     *    删除 1 3 5 还是删除 2 4 6呢？
     *    删5比删6保险，因为删135能保证第7个数一定不在里面，反证法证明
     *
     * 3. 每次减半，最后k为1的时候取两个数组中较小的那个即可（总长度为奇数的话），如果总长度为偶数就找两个数
     *
     */

    public static double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int len = n + m;
        // 如果是偶数，比如14，则要找到第7个和第8个
        if ((len & 1) == 0) {
            return ((double) getKth(nums1, 0, nums2, 0, len / 2) +
                    (double) getKth(nums1, 0, nums2, 0, len / 2 + 1)) / 2;
        }
        // 比如奇数，为5，那就要找第3个
        return getKth(nums1, 0, nums2, 0, (len + 1) / 2);
    }


    /**
     * 在两个数组中找第k个,第 k 个
     * @param nums1
     * @param p nums1 的指针
     * @param nums2
     * @param q nums2 的指针
     * @param k 要找的第k个数
     * @return
     */
    public static int getKth(int[] nums1,int p,int[] nums2,int q,int k){
        // 情况1：其中一个数组被用完了，可以直接在另一个数组中找第k个
        if (p >= nums1.length) {
            return nums2[q + k - 1];    // 极限情况k为1，就要找下一个数，而nums1用完了，那么nums2现在指针指的的就是我们要的
        }
        if (q >= nums2.length) {
            return nums1[p + k - 1];
        }

        // 情况2：k为1，只要找两个数组最左端最小的数即可
        if (k == 1) {
            return Math.min(nums1[p], nums2[q]);
        }

        // 考虑怎么删 k/2 个，怎么删除一个数组中的另一半
        // 首先就是要找到每个数组中从当前下标开始数 k/2 个
        // 如果还在当前数组长度内，那么可以考虑删，如果不再当前数组长度内了，只能删另一个数组了
        // 那么这个mid我们就赋值为无穷大，因为我们删的是nums[mid]较小的那个数组的的 k/2 个
        int mid1 = p + k / 2 - 1 < nums1.length ? nums1[p + k / 2 - 1] : Integer.MAX_VALUE;
        int mid2 = q + k / 2 - 1 < nums2.length ? nums2[q + k / 2 - 1] : Integer.MAX_VALUE;
        // 如果 mid1 更小（或相同，相同情况下删哪个都可以），就把mid1所在的数组中删除 k/2个
        if (mid1 <= mid2) {
            // 指针前移，k也减半
            return getKth(nums1, p + k / 2, nums2, q, k - k / 2);
        }
        return getKth(nums1, p, nums2, q + k / 2, k - k / 2);
    }



}