package leetcode.coding;

public class _713_乘积小于K的子数组 {

    /**
     * 这个题我想到了滑动窗口，枚举每一个窗口，从1枚举到数组的大小
     * 但是这边可以提前停下来，就是我窗口长度为k的时候，如果没有一个满足条件的，那么后面窗口大小为k+1到n的窗口就可以直接不看了
     * 0 < nums[i] < 1000
     *
     * 这个题和560题很相似的
     */

    //自己写的超时了，碰到很多个1的情况就超时了
    public int numSubarrayProductLessThanK(int[] nums, int threshold) {
        int n = nums.length;
        int res = 0;
        //枚举长度为k的窗口
        for (int k = 1; k <= n; k++) {
            //这个窗口有多少个连续子数组是满足条件的
            int cnt = 0;
            int a = 0,j = 0;    //a统计窗口大小
            long temp = 1;  //统计窗口内的乘积
            for (int i = 0; i < n; i++) {
                while (j < n && a < k) {
                    temp *= nums[j];
                    j++;
                    a++;
                }
                if (a == k) {
                    if (temp < threshold) {
                        cnt++;
                    }
                }
                //窗口往前移动,nums[i]是大于0 的，不用考虑除数为0的情况
                temp /= nums[i];
                a--;
            }
            res += cnt;
            System.out.println(cnt);
            if (cnt == 0) {
                break;
            }
        }
        return res;
    }

    /**
     * 更好的思路是双指针
     * 到满足从(left,right)之间乘积 < k的最小的left。比如（2,6）也满足，（3,6）也满足，我们要找的left是2
     * 然后 在这之间 其实都是满足的
     */

    public int numSubarrayProductLessThanK2(int[] nums, int threshold) {
        if (threshold <= 1) {   //我们要找的是< threshold的，当threshold为1的时候，不存在这样数
            return 0;
        }
        int n = nums.length;
        int i = 0,j = 0;
        int temp = 1;
        int res = 0;
        for (i = 0; i < n; i++) {
            temp *= nums[i];
            while (temp >= threshold) {
                temp /= nums[j++];
            }
            res += i - j + 1;
        }
        return res;
    }
}