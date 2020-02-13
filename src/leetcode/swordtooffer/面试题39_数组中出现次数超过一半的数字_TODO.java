package leetcode.swordtooffer;

import java.util.HashMap;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *
 * 方法1 哈说表
 *
 * 方法2 位运算
 * 思路：由于众数数组中出现次数大于n/2 ，那么众数对应的二进制每一个为1的位数出现的次数一定大于n/2，由此可以得出众数
 */
public class 面试题39_数组中出现次数超过一半的数字_TODO {

    //TODO
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int freq = nums.length / 2;
        for (Integer key : map.keySet()) {
            if (map.get(key) > freq) {
                return key;
            }
        }
        return -1;
    }
}