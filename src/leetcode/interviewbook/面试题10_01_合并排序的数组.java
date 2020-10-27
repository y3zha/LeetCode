package leetcode.interviewbook;

public class 面试题10_01_合并排序的数组 {

    //巧妙的做法是从后往前走
    public void merge(int[] A, int m, int[] B, int n) {
        int i = m - 1, j = n - 1, k = A.length - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[k--] = A[i--];
            } else {
                A[k--] = B[j--];
            }
        }
        while (i >= 0) {
            A[k--] = A[i--];
        }
        while (j >= 0) {
            A[k--] = B[j--];
        }
    }
}