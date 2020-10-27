package leetcode.coding;

import com.sun.org.apache.bcel.internal.generic.FADD;

import java.util.HashMap;

/**
 * 设计并实现一个 TwoSum 的类，使该类需要支持 add 和 find 的操作。
 *
 * add 操作 -  对内部数据结构增加一个数。
 * find 操作 - 寻找内部数据结构中是否存在一对整数，使得两数之和与给定的数相等。
 *
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 *
 * ["TwoSum","add","add","add","find"]
 * [[],[0],[-1],[1],[0]]
 * [null,null,null,null,true]
 *
 * 因为这个用例，所以我们find的时候要遍历下所有的可能，然后用flag标记
 */
public class _170_两数之和III数据结构设计 {

    class TwoSum {
        // 存放每个数以及它出现的次数
        HashMap<Integer, Integer> map;

        public TwoSum() {
            map = new HashMap<>();
        }

        public void add(int num) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        public boolean find(int target) {
            boolean flag = false;
            for (Integer key : map.keySet()) {
                int rest = target - key;
                if (map.containsKey(rest)) {
                    flag = rest != key || map.get(key) > 1;
                    if (flag) return true;
                }
            }
            return false;
        }
    }
}