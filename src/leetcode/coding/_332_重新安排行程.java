package leetcode.coding;

import java.util.*;

/**
 * 大意：找一条从JFK出发把所有路径走完，并按字典排序
 * 需要用到一个map来存放它能到达的地方，可能有多个,如何按字典排序。。使用list，需要得到map后再对key的list排序，不是很方便
 * 可以直接使用优先队列
 */
public class _332_重新安排行程 {

    public static List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        if (tickets == null) {
            return res;
        }
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            //若key对应的value为空，会将第二个参数的返回值存入并返回
            map.computeIfAbsent(from, k -> new PriorityQueue<>()).add(to);
        }
        dfs(map, "JFK", res);
        Collections.reverse(res);
        return res;
    }

    private static void dfs(Map<String, PriorityQueue<String>> map, String from, List<String> res) {
        PriorityQueue<String> queue = map.get(from);
        //必须是从后往前添加，因为我从前往后添加是有问题的，先找到最后一个，也就是没有邻居的，它必定是最后访问的
        while (queue != null && !queue.isEmpty()) {
            dfs(map, queue.poll(), res);
        }
        res.add(from);
    }

    public static void main(String[] args) {
        //[["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
        List<List<String>> list = new ArrayList<>();
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        List<String> l3 = new ArrayList<>();
        l1.add("JFK");
        l1.add("KUL");
        l2.add("JFK");
        l2.add("NRT");
        l3.add("NRT");
        l3.add("JFK");
        list.add(l1);
        list.add(l2);
        list.add(l3);
        findItinerary(list);
    }

}