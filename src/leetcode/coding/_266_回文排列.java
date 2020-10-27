package leetcode.coding;

import java.util.HashSet;
import java.util.Set;

public class _266_回文排列 {
    // 消消乐解法就很好
    // 相同字符两两相消。对于每个字符，如果哈希表里没有这个字符则插入，否则删掉。最后哈希表如果剩下 0 个或者 1 个字符则可以变成回文串，否则不行。
    public boolean canPermutePalindrome(String s) {
        int size = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.contains(c)) {
                set.add(c);
                size++;
            } else {
                set.remove(c);
                size--;
            }
        }
        return size <= 1;
    }

}