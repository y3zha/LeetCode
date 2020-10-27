package leetcode.coding;

import java.util.HashMap;

public class _760_找出变位映射 {

    public int[] anagramMappings(int[] A, int[] B) {
        int n = A.length;
        int[] res = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(B[i], i);
        }
        int idx = 0;
        for (int num : A) {
            res[idx++] = map.get(num);
        }
        return res;

    }
}