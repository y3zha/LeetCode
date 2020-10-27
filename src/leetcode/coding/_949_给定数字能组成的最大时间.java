package leetcode.coding;

import java.util.Arrays;
import java.util.Collections;

public class _949_给定数字能组成的最大时间 {

    /**
     * 它这个题给的int[] A的长度限死为4了，如果不是4，如果很长怎么办，可以剪枝，因为小时的十位上不能超过二，并且分钟的十位上不能超过5
     */

    public String largestTimeFromDigits(int[] A) {
        StringBuilder sb = new StringBuilder();
        int n = A.length;
        Arrays.sort(A);
        //确定第一位
        for (int i = n - 1; i >= 0; i--) {
            if (A[i] > 2) {
                continue;
            }
            //确定第二位
            for (int j = n - 1; j >= 0; j--) {
                if (i == j || (A[i] == 2 && A[j] > 3)) {
                    continue;
                }
                //确定第三位
                for (int k = n - 1; k >= 0; k--) {
                    if (i == k || j == k || A[k] > 5) {
                        continue;
                    }
                    sb.append(A[i]).append(A[j]).append(":").append(A[k]).append(A[6 - i - j - k]);
                    return sb.toString();
                }
            }
        }
        return "";
    }


}