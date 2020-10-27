package leetcode.coding;

import java.util.Deque;
import java.util.LinkedList;

public class _321_拼接最大数 {

    /**
     * 和 316、 1081、 402 的 区别是这边有两个数组，字典序的最大最小其实是无关紧要的
     * 之前都是在一个数组中保持顺序取得 k 个，使得它最大/最小，现在问题扩展到两个了
     * 实际上本质没有发生变化
     * 【分】在 nums1 中取 k1 个，在 nums2 中取 k2 个，k1+k2 = k。这两个子问题是独立的，我们只需要分别求解，然后合并结果
     * 【治】merge 的时候要使得数字够大并且相对顺序不变，双指针，每次放大数的即可
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int k1 = 0; k1 <= k; k1++) {
            int k2 = k - k1;
            if (k1 <= nums1.length && k2 <= nums2.length) {
                int[] a1 = divide(nums1, k1);
                int[] a2 = divide(nums2, k2);
                int[] c = conquer(a1, a2);
                for (int i = 0; i < c.length; i++) {
                    if (notAns(ans, c)) {
                        ans = c;
                    }
                }
            }
        }
        return ans;
    }

    // a 不是答案，也就是 b 某个位置更大
    private boolean notAns(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) return false;
            if (a[i] < b[i]) return true;
        }
        return true;
    }

    // 在 nums 中保持顺序挑 k 个，使得它最大,单调栈 316题、1081题、402题
    // 挑 k 个等价于删除 n-k 个，这题就与 402 题一模一样了
    public int[] divide(int[] nums, int k) {
        int n = nums.length;
        if (k == 0) return new int[]{};
        if (n <= k) return nums;
        int del = n - k;
        Deque<Integer> dq = new LinkedList<>();
        for (int num : nums) {
            // 如果来的数字比栈顶的大，栈顶就得滚蛋了
            while (del > 0 && !dq.isEmpty() && num > dq.peekLast()) {
                dq.pollLast();
                del--;
            }
            dq.offerLast(num);
        }
        int[] ans = new int[k];
        int idx = 0;
        while (!dq.isEmpty() && idx < k) {
            ans[idx++] = dq.pollFirst();
        }
        return ans;
    }

    // 用 pq 感觉也可以 不过是 nlogn了
    // 不等简单的比较，如果碰到两个位置一样，则需要比较后面的！调用 deepCompare
    public int[] conquer(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[] ans = new int[n + m];
        int i = 0, j = 0, idx = 0;
        while (i < n && j < m) {
            ans[idx++] = deepCompare(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        while (i < n) ans[idx++] = nums1[i++];
        while (j < m) ans[idx++] = nums2[j++];
        return ans;
    }

    private boolean deepCompare(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                i++;
                j++;
                continue;
            }
            return nums1[i] > nums2[j];
        }
        return i < nums1.length;
    }

    public static void main(String[] args) {
        int[] nums1 = {6, 7};
        int[] nums2 = {6, 0, 4};
        _321_拼接最大数 test = new _321_拼接最大数();
        test.maxNumber(nums1, nums2, 5);
    }
}