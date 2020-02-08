package leetcode.coding;

import java.util.*;

/**
 * BFS
 *
 * 其实实际上就是求 "0000" 变化到 target串的最短路径，也就是最少变化次数是多少
 * 并且变化过程中的数字不能出现在 deadends 中
 *
 * 思路：
 *      把0000到9999 看作是图中的10000个节点，它和能变化到的节点之间都有一条边（这两个节点仅有一位不同）
 *      对于每一个状态，它可以扩展到最多 8 个状态，即将它的第 i = 0, 1, 2, 3 位增加 1 或减少 1，因为只能拨一次
 *
 */
public class _752_打开转盘锁 {

    public int openLock(String[] deadends, String target) {
        //首先把deadends中的都存到set中方便查询
        Set<String> deadSet = new HashSet<>();
        for (String deadend : deadends) {
            deadSet.add(deadend);
        }
        if (deadSet.contains("0000")) {
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        Set<String> visited = new HashSet<>();
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                //获取它能转换的所有节点，然后遍历
                List<String> list = getNext(poll);
                for (String s : list) {
                    if (deadSet.contains(s)) {
                        continue;
                    }
                    if (s.equals(target)) {
                        return steps;
                    }
                    if (!visited.contains(s)) {
                        visited.add(s);
                        queue.offer(s);
                    }
                }
            }
        }
        return -1;
    }

    private List<String> getNext(String s) {
        List<String> list = new ArrayList<>();
        char[] sc = s.toCharArray();
        //对四个位上做修改
        StringBuilder sb = new StringBuilder(s);
        int old = 0, now = 0;
        for (int i = 0; i < 4; i++) {
            old = sc[i] - '0';
            now = (old + 1) % 10;   //往后一位
            sb.setCharAt(i, (char) (now + '0'));
            list.add(sb.toString());

            now = (old + 9) % 10;   //往前一位
            sb.setCharAt(i, (char) (now + '0'));
            list.add(sb.toString());

            //复原
            sb.setCharAt(i, (char) (old + '0'));
        }
        return list;
    }

}