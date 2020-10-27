package leetcode.coding;

import java.util.HashMap;
import java.util.Map;

/**
 * 一.采用分为两组，HashMap存一组，另一组和HashMap进行比对。
 * 二.这样的话情况就可以分为三种：
 * 1.HashMap存一个数组，如A。然后计算三个数组之和，如BCD。时间复杂度为：O(n)+O(n^3),得到O(n^3).
 * 2.HashMap存三个数组之和，如ABC。然后计算一个数组，如D。时间复杂度为：O(n^3)+O(n),得到O(n^3).
 * 3.HashMap存两个数组之和，如AB。然后计算两个数组之和，如CD。时间复杂度为：O(n^2)+O(n^2),得到O(n^2).
 * 三.根据第二点我们可以得出要存两个数组算两个数组。
 * 四.我们以存AB两数组之和为例。首先求出A和B任意两数之和sumAB，以sumAB为key，sumAB出现的次数为value，存入hashmap中。
 * 然后计算C和D中任意两数之和的相反数sumCD，在hashmap中查找是否存在key为sumCD。
 * 算法时间复杂度为O(n2)。
 *
 */
public class _454_四数相加II {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> mapAB = new HashMap<>();
        Map<Integer, Integer> mapCD = new HashMap<>();
        int res = 0;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                sum = A[i] + B[j];
                mapAB.put(sum, mapAB.getOrDefault(sum, 0) + 1);
            }
        }
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                sum = -C[i] - D[j];
                if (mapAB.containsKey(sum)) {
                    res += mapAB.get(sum);
                }
            }
        }
        return res;
    }
}