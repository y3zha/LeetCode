package leetcode.coding;

import java.util.*;

public class _381_O1时间插入删除和获取随机元素允许重复 {
    // 问题中最困难的部分就是要在 O(1) 的时间找到要删除元素的索引。我们可以通过一个哈希表将元素值映射到它们的索引。

    // 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
    static class RandomizedCollection {
        List<Integer> list;
        Map<Integer, Set<Integer>> map;
        Random rand;

        public RandomizedCollection() {
            list = new ArrayList<>();
            map = new HashMap<>();
            rand = new Random();
        }

        // 向集合中插入元素 val。
        public boolean insert(int val) {
            // 如果集合中已经包含 返回false，未包含返回true（这个比较傻逼）
            map.computeIfAbsent(val, k -> new HashSet<>()).add(list.size());
            list.add(val);
            return map.get(val).size() == 1;
        }

        // 当 val 存在时，从集合中移除一个 val。
        public boolean remove(int val) {
            Set<Integer> set = map.get(val);
            if (set == null || set.isEmpty()) {
                return false;
            }

            Integer remove_idx = set.iterator().next();
            // 如果最后一个元素和要删除的元素相同，得要先删除，而不能在最后 去 set remove
            // 因为如果在最后去 remove，下面map获取去add的时候，是add不进去的，因为集合中已经有了
            set.remove(remove_idx);
            int lastIdx = list.size() - 1;
            int last = list.get(lastIdx);

            list.set(remove_idx, last);
            list.remove(lastIdx);
            map.get(last).add(remove_idx);
            map.get(last).remove(lastIdx);

            return true;
        }

        // 从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }


    }

    public static void main(String[] args) {
        RandomizedCollection randomizedCollection = new RandomizedCollection();
        randomizedCollection.insert(4);
        randomizedCollection.insert(3);
        randomizedCollection.insert(4);
        randomizedCollection.insert(2);
        randomizedCollection.insert(4);
        randomizedCollection.remove(4);
        randomizedCollection.remove(3);
        randomizedCollection.remove(4);
        randomizedCollection.remove(4);
    }


}