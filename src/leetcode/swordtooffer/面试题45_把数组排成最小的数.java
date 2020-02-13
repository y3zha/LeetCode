package leetcode.swordtooffer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 * 一开始想到最直接的办法是排列组合找最小
 * 然后思考了下 给定两个数a b，它的全排列是 ab ba，如果 a < b 我们就应该选 ab，也就是开头小的在前面，我们可以自定义排序规则
 * 提交答案的时候发现超出范围了，所以改用了字符串处理
 *
 * 在比较两个字符串 S1 和 S2 的大小时，应该比较的是 S1+S2 和 S2+S1 的大小，如果
 * S1+S2 < S2+S1，那么应该把 S1 排在前面，否则应该把 S2 排在前面。
 */
public class 面试题45_把数组排成最小的数 {

    public String minNumber(int[] nums) {
        ArrayList<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        list.sort(Comparator.comparing(s -> s, (s1, s2) -> (s1 + s2).compareTo(s2 + s1)));
        return String.join("", list).toString();
    }

}