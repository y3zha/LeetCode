package leetcode.coding;

import java.util.*;

public class _316_去除重复字母 {

    /**
     * 和 402 题不同的是没有删除次数，那么对于每一个字母，它出现次数 m 次，当 m > 1 的情况下，我们就要进行删除 m-1 次，这个 m-1 就是 401 题的k
     * <p>
     * 1. 首先统计每个字母出现的次数
     * 2. 遍历 s，每遍历到一个字母，其出现次数 - 1，如果减之前出现次数大于 1，我们可以选择丢弃（也可以选择不丢弃），如果出现次数为1则不能丢弃
     * 3. 单调栈，字典顺序大的得滚蛋.
     *      遍历，我们把已经出现的字符存进 set 中，方便查询是否出现过
     *      如果没有出现过，则准备插入，插入就需要比较字典序，若字典序比栈顶的更小，且栈顶的元素还有（ 大于 0 个），栈顶就能滚蛋了
     *      如果出现过了，就往后走即可，存放的个数 - 1
     *  相关题目 401、 1081、321
     */
    public static String removeDuplicateLetters(String s) {

        // 统计每个字母出现次数
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        Deque<Character> dq = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // 如果不在栈中
            if (!set.contains(c)) {
                // 如果 c 的字典序比栈顶的更小，且栈顶的元素还有（ 大于 0 个），栈顶就能滚蛋了
                while (!dq.isEmpty() && c < dq.peekLast() && map.get(dq.peekLast()) > 0) {
                    set.remove(dq.pollLast());
                }
                set.add(c);
                dq.offerLast(c);
            }
            // 在字典序中就直接减
            map.put(c, map.get(c) - 1);
        }
        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            sb.append(dq.pollFirst());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        removeDuplicateLetters("bcabc");
    }


}