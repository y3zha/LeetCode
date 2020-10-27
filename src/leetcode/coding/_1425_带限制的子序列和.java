package leetcode.coding;

import java.util.PriorityQueue;

public class _1425_带限制的子序列和 {
    //https://leetcode-cn.com/problems/constrained-subsequence-sum/solution/1425-dai-xian-zhi-de-zi-xu-lie-he-dong-tai-gui-hua/

    /**
     * 暴力枚举
     * arr[i] 代表以 i 结尾的子序列的最大的和是多少
     * arr[i] = nums[i] + max(0,arr[i-k],arr[i-k+1],,,arr[i-1])
     * 时间复杂度 O(NK) 超时
     */
    public static int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] arr = new int[n];
        int res = nums[0];
        for (int i = 0; i < n; i++) {
            int start = Math.max(0, i - k);
            // 拿到它前面最大的那个
            int temp = 0;
            for (int j = start; j < i; j++) {
                temp = Math.max(temp, arr[j]);
            }
            arr[i] = nums[i] + temp;
            res = Math.max(res, arr[i]);
        }
        return res;
    }

    /**
     * 优化，既然每次都要花 O(K)时间找最大，可以该用优先队列
     * 优先队列里要存下标和值，按照值降序排
     */
    public static int constrainedSubsetSum2(int[] nums, int k) {
        int n = nums.length;
        int res = nums[0];
        // int[0]存值 int[1]存下标，按照值降序排
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            while (!pq.isEmpty() && pq.peek()[1] < i - k) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                val += Math.max(0, pq.peek()[0]);
            }
            res = Math.max(res, val);
            pq.offer(new int[]{val, i});    // 这里是细节点，注意存的是{val,i}，val是当前下标下的最大值
        }
        return res;
    }




    public static void main(String[] args) {
        int[] nums = {10, 2, -10, 5, 20};
        constrainedSubsetSum2(nums, 2);
    }


}