package leetcode.interviewbook;
/**
 * leetcode 161
 *
 * 给定两个字符串 S 和 T, 判断T是否可以通过对S做刚好一次编辑得到。
 * 每次编辑可以选择以下任意一个操作：
 *      在S的任意位置插入一个字符
 *      删除S中的任意一个字符
 *      将S中的任意字符替换成其他字符
 *
 * 想一想特殊情况，两字符串长度相差太大时?两个字符串长度一样?
 * 三种情况:
 *      1.两个字符串长度差>1 直接false
 *      2.两个字符串长度差=1 只能插入/删除一个字符    //两根之前从前往后扫描，一旦发现不一样了，把长的那个字符串的那一位删掉继续比较后面的
 *      3.两个字符串长度差=0 只能有一位不同
 */
public class 面试题01_05_一次编辑 {

    public boolean oneEditAway(String s, String t) {
        // 较长的串作为s串，较短的作为t串
        if (s.length() < t.length()) {
            String temp = s;
            s = t;
            t = temp;
        }
        int n = s.length();
        int m = t.length();
        if (n - m > 1) {
            return false;
        }
        if (n - m == 1) {
            //遍历短串
            for (int i = 0; i < m; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    return s.substring(i + 1).equals(t.substring(i));
                }
            }
        }
        if (n == m) {
            int diff = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    diff++;
                    if (diff > 1) {
                        return false;
                    }
                }
            }
            return true;
        }
        return true;
    }
}