package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _1213_三个有序数组的交集 {

    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> list = new ArrayList<>();
        int[] arr = new int[2001];
        for (int i = 0; i < arr1.length; i++) {
            arr[arr1[i]]++;
        }
        for (int i = 0; i < arr2.length; i++) {
            arr[arr2[i]]++;
        }
        for (int i = 0; i < arr3.length; i++) {
            arr[arr3[i]]++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 3) {
                list.add(i);
            }
        }
        return list;
    }
}