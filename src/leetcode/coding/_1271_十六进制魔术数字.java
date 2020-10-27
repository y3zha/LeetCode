package leetcode.coding;

import java.util.Arrays;
import java.util.HashSet;

public class _1271_十六进制魔术数字 {

    public String toHexspeak(String num) {
        String ss = Long.toHexString(Long.parseLong(num)).replace('1', 'I').replace('0', 'O').toUpperCase();
        HashSet<Character> set = new HashSet<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'I', 'O'));
        for (int i = 0; i < ss.length(); i++) {
            Character c = ss.charAt(i);
            if (!set.contains(c)) return "ERROR";
        }
        return ss;
    }
}