package leetcode.coding;

import java.util.*;

/**
 * 这个题 bfs好一点吧
 * bfs/dfs/并查集 其实都能做
 *
 * 构造一个双向图，比如a/b=2.0，那么a->b权重2.0，b->a权重0.5，要查的时候就用BFS遍历，每到一个新节点，就乘以权重，找到目标节点时返回当前值即可。
 * 没有出现过的字母直接返回-1.0，然后一样的字母直接返回1.0。这时就可以计算两点距离了。
 */
public class _399_除法求值 {

    HashMap<String, HashMap<String, Double>> map = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        int n = equations.size();
        for (int i = 0; i < n; i++) {
            List<String> list = equations.get(i);
            String s1 = list.get(0);
            String s2 = list.get(1);
            map.computeIfAbsent(s1, k -> new HashMap<String, Double>()).put(s2, values[i]);
            map.computeIfAbsent(s2, k -> new HashMap<String, Double>()).put(s1, 1 / values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String dividend = queries.get(i).get(0);     //被除数，除号前面的
            String divisor = queries.get(i).get(1);    //除数，除号后面的
            System.out.println(dividend + divisor);

            res[i] = bfs(dividend, divisor);
        }
        return res;
    }

    class Point{
        String start;
        double dis;

        public Point(String start, double dis) {
            this.start = start;
            this.dis = dis;
        }
    }

    private double bfs(String dividend, String divisor) {
        //1、如果这两个顶点中的任意一个都不是邻接矩阵的key，直接return -1；
        //2、如果输入的两个顶点是同一个，直接return 1.0
        if (!map.containsKey(dividend) || !map.containsKey(divisor)) {
            return -1.0;
        }
        if (dividend.equals(divisor)) {
            return 1.0;
        }
        //构建队列，存放着某个 结点 和 该结点与初始结点的累积距离 组成的pair
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(dividend, 1.0));
        Set<String> set = new HashSet<>();

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            //如果是我们找的结点，那么直接返回dis，也就是累积距离
            if (p.start.equals(divisor)) {
                return p.dis;
            }
            set.add(p.start);
            HashMap<String, Double> next = map.get(p.start);
            for (String s : next.keySet()) {
                if (!set.contains(s)) {
                    queue.offer(new Point(s, p.dis * map.get(p.start).get(s)));
                }
            }
        }
        return -1.0;
    }
}