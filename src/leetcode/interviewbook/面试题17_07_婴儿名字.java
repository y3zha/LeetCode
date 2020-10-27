package leetcode.interviewbook;

import java.util.*;

public class 面试题17_07_婴儿名字 {

    /**
     * 并查集,同时也很考验字符串的处理
     * 每个名字看做一个点，把相同的名字用一条边相连，这样能构成若干个联通块，输出每个块的频率总和以及块中字典序最小的名字。
     * <p>
     * 输入：names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"],
     * synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
     * 输出：["John(27)","Chris(36)"]
     */

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        //name->freq
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : names) {
            String name = s.substring(0, s.indexOf('('));
            Integer age = Integer.parseInt(s.substring(s.indexOf('(')+1, s.indexOf(')')));
            map.put(name, age);
        }
        Map<Integer, String> idxToStrMap = new HashMap<>();
        Map<String, Integer> strToIdxMap = new HashMap<>();
        int count = 0, len = synonyms.length, memo_idx = 0;
        int[][] memo = new int[len][2];
        for (String str : synonyms) {
            int idx = str.indexOf(',');
            String a = str.substring(1, idx), b = str.substring(idx + 1, str.length() - 1);
            if (!strToIdxMap.containsKey(a)) {
                strToIdxMap.put(a, count);
                idxToStrMap.put(count++, a);
            }
            if (!strToIdxMap.containsKey(b)) {
                strToIdxMap.put(b, count);
                idxToStrMap.put(count++, b);
            }
            memo[memo_idx][0] = strToIdxMap.get(a);
            memo[memo_idx++][1] = strToIdxMap.get(b);
        }
        UF uf = new UF(count);
        for (int[] pair : memo)
            uf.union(pair[0], pair[1]);

        Map<Integer, LinkedList<Integer>> target_map = new HashMap<>();
        for (int i = 0; i < count; ++i) {
            int key = uf.find(i);
            LinkedList<Integer> list = target_map.computeIfAbsent(key, unused -> new LinkedList<>());
            list.addLast(i);
        }
        LinkedList<String> res = new LinkedList<>();
        for (LinkedList<Integer> list : target_map.values()) {
            PriorityQueue<String> priorityQueue = new PriorityQueue<>(list.size());
            for (int idx : list)
                priorityQueue.offer(idxToStrMap.get(idx));
            String base_name = priorityQueue.poll();
            int base_val = map.remove(base_name);
            for (String str : priorityQueue) {
                Integer val = map.remove(str);
                base_val += val == null ? 0 : val;
            }
            res.addLast(base_name + "(" + base_val + ")");
        }
        map.forEach((k, v) -> res.addLast(k + "(" + v + ")"));
        return res.toArray(new String[0]);

    }

    class UF{
        private int[] father;

        public UF(int n) {
            father = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                father[i] = i;
            }
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB) {
                father[rootA] = rootB;
            }
        }

        public int find(int x) {
            if (x == father[x]) {
                return x;
            }
            return father[x] = find(father[x]);
        }
    }


}