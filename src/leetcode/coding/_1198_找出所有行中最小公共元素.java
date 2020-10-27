package leetcode.coding;

public class _1198_找出所有行中最小公共元素 {

    // 一种暴力的写法，暴力也能过
    public int smallestCommonElement(int[][] mat) {
        // 有多少组
        int n = mat.length;
        int m = mat[0].length;
        int[] arr = new int[10001];
        for (int[] ints : mat) {
            for (int i = 0; i < m; i++) {
                arr[ints[i]]++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == n) {
                return i;
            }
        }
        return -1;
    }
}