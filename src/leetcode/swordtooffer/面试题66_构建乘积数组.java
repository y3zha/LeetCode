package leetcode.swordtooffer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 面试题66_构建乘积数组 {

    /**
     * 这个题力扣也是有的，先左乘再右乘即可
     */
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) {
            return new int[]{};
        }
        int n = a.length;
        int[] res = new int[n];
        res[0] = 1;
        //左乘
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * a[i - 1];
        }
        //List<Integer> collect = Arrays.stream(res).boxed().collect(Collectors.toList());
        //System.out.println(collect);

        //右乘
        int temp = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= temp;
            temp *= a[i + 1];
        }
        return res;
    }
}