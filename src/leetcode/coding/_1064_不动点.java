package leetcode.coding;

public class _1064_不动点 {

    //二分
    public static int fixedPoint(int[] A) {
        int left = 0, right = A.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (A[mid] < mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return A[left] == left ? left : -1;
    }

}