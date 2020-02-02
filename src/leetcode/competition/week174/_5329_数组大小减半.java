package leetcode.competition.week174;

import lintcode.海量数据处理.MapReduce.Anagram;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _5329_数组大小减半 {

    //arr.length 为偶数
    public int minSetSize(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        //按照个数降序排序
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                int diff = o2.getValue() - o1.getValue();
                return diff;
            }
        });


        int count = 0;
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            Map.Entry<Integer, Integer> entry = list.get(i);
            Integer value = entry.getValue();
            sum += value;
            count++;
            if (sum >= n / 2) {
                break;
            }
        }
        return count;
    }
}