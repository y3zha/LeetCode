package leetcode.coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class _380_常数时间插入删除和获取随机元素 {
    // 常数级别插入删除：hash、链表，但是这两个getRandom怎么解决呢？
    // getRandom 的思想是选择一个随机索引，然后使用该索引返回一个元素。
    // 而哈希表中没有索引，因此要获得真正的随机值，则要将哈希表中的键转换为列表，这需要线性时间。
    // 解决的方法是用一个列表存储值，并在该列表中实现常数时间的 getRandom。

    // 列表有索引可以实现常数时间的 insert 和 getRandom，则接下来的问题是如何实现常数时间的 remove。
    // 删除任意索引元素需要线性时间，这里的解决方案是总是删除最后一个元素。将要删除元素和最后一个元素交换。将最后一个元素删除。

    class RandomizedSet {

        // val -> idx
        private HashMap<Integer, Integer> map;
        private List<Integer> list;
        private Random rand;

        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
            rand = new Random();
        }

        // 当元素 val 不存在时，向集合中插入该项。
        public boolean insert(int val) {
            if (!map.containsKey(val)) {
                map.put(val, list.size());
                list.add(list.size(), val);
                return true;
            }
            return false;
        }

        // 元素 val 存在时，从集合中移除该项。
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            Integer index = map.get(val);
            // 交换list[index]与list[idx]上的元素
            Integer last = list.get(list.size() - 1);
            list.set(index, last);
            map.put(last, index);
            map.remove(val);
            list.remove(list.size() - 1);
            return true;
        }

        // 随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }
}