package leetcode.swordtooffer;

public class 面试题50_第一个只出现一次的字符 {

    /**
     * 计数即可
     */
    public char firstUniqChar(String s) {
        int[] arr = new int[256];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (arr[s.charAt(i)] == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }
}