package leetcode.coding;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class _975_奇偶跳 {
    /*
    你可以按以下方式从索引 i 向后跳转到索引 j（其中 i < j）：

在进行奇数跳跃时（如，第 1，3，5... 次跳跃）
   你将会跳到索引 j，使得 A[i] <= A[j]，A[j] 是可能的最小值。如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
   这句话是说要跳到i后面的索引j，A[j] 要大于等于 A[i]，如果有多个符合条件的A[j]，要在A[j]中选择最小的，如果有多个最小的，要选择索引最小的
   首先是满足最小，别管它能不能跳


在进行偶数跳跃时（如，第 2，4，6... 次跳跃）
    你将会跳到索引 j，使得 A[i] >= A[j]，A[j] 是可能的最大值。如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
    这句话是说要跳到i后面的索引j,A[j] 要小于等于 A[i]，如果有多个符合条件的A[j]，要在A[j]中选择最大的，如果有多个最大的，要索引最小的

（对于某些索引 i，可能无法进行合乎要求的跳跃。）
如果从某一索引开始跳跃一定次数（可能是 0 次或多次），就可以到达数组的末尾（索引 A.length - 1），那么该索引就会被认为是好的起始索引。


对于跳过的，我们要记录它是奇数次还是偶数次跳到最后一步的

     */

    // tle
    int[] A;
    int n;
    // [i][0] 偶数次跳跃，[i][1]，奇数次跳跃
    boolean[][] v;


    public int oddEvenJumps(int[] A) {
        this.A = A;
        n = A.length;
        v = new boolean[n][2];
        v[n - 1][0] = v[n - 1][1] = true;
        int res = 1;
        for (int i = n - 2; i >= 0; i--) {
            // 测试奇数次跳跃
            int a = dfs(i, 1);
            // 测试偶数次跳跃
            int b = dfs(i, 2);
            if (a == 1) {
                res += 1;
                v[i][1] = true;
            }
            if (b == 1) {
                // 从后往前，不是第一次跳跃，不要算进去，如果a为0，b为1，这种情况就不要算b，b只是用来看偶数次能不能跳
                // if (a != 1) {
                //     res++;
                // }
                v[i][0] = true;
            }
        }
        return res;
    }

    // i 为起跳点,cnt为起跳的次数
    private int dfs(int i, int cnt) {
        if (i == n - 1) {
            return 1;
        }
        // 如果是奇数次跳跃,
        if (cnt % 2 == 1) {
            // 在这个位置之后找能跳的，下一个一定要是偶数次能跳
            int temp = Integer.MAX_VALUE;
            int idx = -1;
            for (int j = i + 1; j < n; j++) {
                if (A[i] <= A[j] && A[j] < temp) {
                    temp = A[j];
                    idx = j;
                }
            }

            if (idx == -1 || !v[idx][0]) {
                return 0;
            } else {
                return dfs(idx, cnt + 1);
            }
        } else {
            int temp = Integer.MIN_VALUE;
            int idx = -1;
            for (int j = i + 1; j < n; j++) {
                if (A[i] >= A[j] && A[j] > temp) {
                    temp = A[j];
                    idx = j;
                }
            }
            if (idx == -1 || !v[idx][1]) {
                return 0;
            } else {
                return dfs(idx, cnt + 1);
            }
        }
    }

    // 超时主要是因为，找这个数它右面第一个大于等于它的，和右面第一个小于等于它的这个过程重复了
    // 看到求这个就想到用单调栈了
    // 我们先求好，再用
    // 求右面第一个大于等于它的，用单调递减栈
    // 求右面第一个小于等于它的，用单调递增栈
    // 注意，返回的是下标数组
    // 但是吧，单调栈只能找到第一个大于等于/小于等于的，而不是后面最小的，我们要求的是后面最小的里面，索引最小的

    // 看到一个用TreeMap的写法 非常不错
    // 从右边往左边计算，遇到相同的值的时候，因为总是取索引最小的，因此刷新treeMap中value的值为新的索引就好了。

    // https://www.geeksforgeeks.org/java-util-treemap-floorentry-floorkey-java/

    public int oddEvenJumps2(int[] A) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int n = A.length;
        boolean[] oddJump = new boolean[n];
        boolean[] evenJump = new boolean[n];
        oddJump[n - 1] = evenJump[n - 1] = true;
        // val ->idx
        treeMap.put(A[n - 1], n - 1);
        int ans = 1;
        for (int i = n - 2; i >= 0; i--) {
            Integer key = A[i];
            // 在它没放进去前先检索

            // 偶数次跳跃就是找到小于等于这个 key 的 entry， floorEntry返回小于等于给定键的最大值
            Map.Entry<Integer, Integer> evenEntry = treeMap.floorEntry(key);
            evenJump[i] = evenEntry != null && oddJump[evenEntry.getValue()];

            // 奇数次跳跃就是找大于等于这个 key 的 entry，ceilingEntry返回大于等于给定键的最小值
            Map.Entry<Integer, Integer> oddEntry = treeMap.ceilingEntry(key);
            oddJump[i] = oddEntry != null && evenJump[oddEntry.getValue()];

            ans = oddJump[i] ? ans + 1 : ans;
            treeMap.put(key, i);
        }
        return ans;
    }

    public static void main(String[] args) {
        _975_奇偶跳 test = new _975_奇偶跳();
        int[] a = {1, 2, 3, 2, 1, 4, 4, 5};
        test.oddEvenJumps(a);
    }
}