package leetcode.competition.week175;

import java.util.HashMap;
import java.util.Set;

public class _5332_检查整数及其两倍数是否存在 {

    public static boolean checkIfExist(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int zeros = 0;
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
            if (arr[i] == 0) {
                zeros++;
            }
        }
        if (zeros >= 2) {
            return true;
        }
        for (Integer key : map.keySet()) {
            if (map.containsKey(2 * key) && key != 0) {
                // System.out.println(key);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {-2, 0, 10, -19, 4, 6, -8};
        checkIfExist(a);
    }
}