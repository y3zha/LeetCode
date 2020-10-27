package leetcode.coding;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//和lintcode612一样的，这边还简单了点，思路是利用最大堆
public class _973_最接近原点的数 {

    public int[][] kClosest(int[][] points, int k) {
        int[][] res = new int[k][2];
        Queue<int[]> pq = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //建大顶堆，降序的，x2^2 + y2^2 - x1^2 - y1^2
                //题目只要任意顺序返回答案，，lintcode是有要求的。。
                return o2[0] * o2[0] + o2[1] * o2[1] - (o1[0] * o1[0] + o1[1] * o1[1]);
            }
        });

        for (int i = 0; i < points.length; i++) {
            //获取每一行的坐标，并放入pq
            int[] point = new int[2];
            point[0] = points[i][0];
            point[1] = points[i][1];
            pq.offer(point);
            if (pq.size() > k) {        //pq有自动扩容机制
                pq.poll();
            }
        }

        //输出结果
        int index = k - 1;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            res[index][0] = poll[0];
            res[index][1] = poll[1];
            index--;
        }
        return res;
    }

}