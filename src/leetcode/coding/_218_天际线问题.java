package leetcode.coding;

import java.util.*;

public class _218_天际线问题 {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0) {
            return res;
        }
        List<int[]> list = new ArrayList<>();
        for (int[] b : buildings) {
            // 负数代表起点，正数代表终点
            list.add(new int[]{b[0], -b[2]});
            list.add(new int[]{b[1], b[2]});
        }
        list.sort(((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]));
        // 没用TreeMap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.offer(0);   //删除最后一个元素时，不加peek就报错
        int prevHeight = 0;
        for (int[] arr : list) {
            //如果是起点,加进堆里，如果是终点，删除该元素
            if (arr[1] < 0) {
                maxHeap.offer(-arr[1]);
            } else {
                maxHeap.remove(arr[1]);
            }
            //查看当前栈顶元素
            int curHeight = maxHeap.peek();
            if (curHeight != prevHeight) {
                res.add(Arrays.asList(arr[0], curHeight));
                prevHeight = curHeight;
            }
        }
        return res;
    }
}