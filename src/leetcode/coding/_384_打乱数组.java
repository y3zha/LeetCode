package leetcode.coding;

import java.util.Arrays;
import java.util.Random;

/**
 * 打乱一个没有重复元素的数组。没有重复元素！！
 *
 * Fisher-Yates 洗牌算法
 *
 * 1、在每次迭代中，生成一个 0～arr.len-1 之间的随机整数。
 * 2、将当前元素和随机选出的下标所指的元素互相交换
 * 3、细节是，当前元素是可以和它本身互相交换的 - 否则生成最后的排列组合的概率就不对了
 * 4、由于需要重置，所以我们要拷贝原数组
 *
 * https://leetcode-cn.com/problems/shuffle-an-array/solution/xi-pai-suan-fa-shen-du-xiang-jie-by-labuladong/
 */
public class _384_打乱数组 {

    class Solution {
        private int[] array;
        private int[] original;
        private Random rand;

        public Solution(int[] nums) {
            array = nums;
            original = Arrays.copyOf(array, array.length);
            rand = new Random();
        }

        public int[] reset() {
            //重置，就是把当前数组变为原数组
            array = Arrays.copyOf(original, original.length);
            return array;
        }

        public int[] shuffle() {
            for (int i = 0; i < array.length; i++) {
                //randRange(i, array.length)，而不是(0,arr.len)，因为洗牌算法产生的结果必须有 n! 种可能
                //比如5个数，每个位置的洗牌可能必须是递减的 5*4*3*2*1
                swap(i, randRange(i, array.length));
            }
            return array;
        }

        private int randRange(int min, int max) {
            return rand.nextInt(max - min) + min;
        }

        private void swap(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}