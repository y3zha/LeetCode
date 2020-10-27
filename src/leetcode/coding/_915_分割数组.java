package leetcode.coding;

import java.util.Arrays;

public class _915_分割数组 {

    /*
    枚举分界点，然后验证左数组的最大值小于等于右数组的最小值。在遍历的同时，跟新左数组的最大值，右数组的最小值需要预处理。
     */
    public static int partitionDisjoint(int[] A) {
        int n = A.length;
        int max = A[0];
        // f[i] 代表 A中下标为 i 的元素右侧的最小值,包含它自身位置元素
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = n - 1; i >= 1; i--) {
            f[i] = Math.min(A[i], f[i + 1]);
        }
        // 枚举划分点
        for (int i = 1; i < n - 1; i++) {
            // 到 i-1的最大值
            max = Math.max(max, A[i - 1]);
            if (max <= f[i]) {
                return i;
            }
        }
        return n - 1;
    }

    public static void main(String[] args) {
        System.out.println(partitionDisjoint(new int[]{1, 1}));
    }
}