package leetcode.interviewbook;

public class 面试题01_09_字符串轮转 {

    /**
     * 力扣原题
     */

    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != 0 && s2.length() == 0) {
            return false;
        }
        return (s1 + s1).contains(s2);
    }
}