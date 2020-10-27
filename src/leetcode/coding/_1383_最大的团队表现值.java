package leetcode.coding;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 公司有编号为 1 到 n 的 n 个工程师，给你两个数组 speed 和 efficiency 
 * 其中 speed[i] 和 efficiency[i] 分别代表第 i 位工程师的速度和效率。
 * 请你返回由最多 k 个工程师组成的 ​​​​​​最大团队表现值 ，由于答案可能很大，请你返回结果对 10^9 + 7 取余后的结果。
 * 团队表现值的定义为：一个团队中「所有工程师速度的和」乘以他们「效率值中的最小值」。
 *
 */
public class _1383_最大的团队表现值 {

    // 既要考虑速度和，也要考虑效率的最小值
    // 直观的解法是按照效率进行降序排序，每个人作为最低效率时，在其左侧找出至多K - 1个最大速度即可(再加上这个人的速度组成K个)，这一过程可以用堆，时间复杂度O(nlg(k-1))
    static int MOD = (int) (1e9 + 7);
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        // 构建出每个人及其效率的数组
        int[][] mat = new int[n][2];
        for (int i = 0; i < n; i++) {
            mat[i][0] = speed[i];
            mat[i][1] = efficiency[i];
        }
        //按照效率降序排序
        Arrays.sort(mat, (o1, o2) -> o2[1] - o1[1]);
        // 最多是k个，一个也可以～～
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long res = 0;
        // 存储当前和是多少
        long sum = 0;
        // 已经排序了，效率是从高到低来的，当前效率很定小于等于前面那个人的效率
        for (int i = 0; i < n; i++) {
            if (pq.size() > k - 1) {
                sum -= pq.poll();
            }
            res = Math.max(res, (sum + mat[i][0]) * mat[i][1]);
            pq.offer(mat[i][0]);
            sum += mat[i][0];
        }
        System.out.println(res);

        // 错误写法
        // return (int) (res % (int)(1e+7));
        // 正确写法
        // return (int)(res % ((int)1e9 + 7));
        return (int) (res % MOD);


    }
}