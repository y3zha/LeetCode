package leetcode.swordtooffer;

public class 面试题58_左旋转字符串II {

    public String reverseLeftWords(String s, int n) {
        System.out.println(s.substring(0, n));
        return s.substring(n, s.length()) + s.substring(0, n);
    }
}