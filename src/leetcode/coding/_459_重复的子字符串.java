package leetcode.coding;

/**
 * 非常巧妙的方法！
 * 原来字符串为s，我们构造新的字符串为s+s，对新的字符串去头去尾得到字符串，如果处理过后的字符串包含原字符串，那么为true
 *
 * 解释：假设原字符串是由一个字符串重复多次构成的，那么至少包含两个子串，s+s后，那么至少包含4个子串
 * 对s+s去头去尾，就相当于破坏头尾两个子串，然后只要这个字符串还包含s，那么必定是由重复串构成的
 */
public class _459_重复的子字符串 {

    public boolean repeatedSubstringPattern(String s) {
        String ns = s + s;
        return ns.substring(1, ns.length() - 1).contains(s);
    }

    /**
     * follow up 找出重复的字符串
     * kmp
     */
    public static String getRepeatSubstr(String s) {
        int[] ne = getNext(s);
        int n = s.length();
        int len = n - ne[n];
        return s.substring(0, len);
    }

    private static int[] getNext(String s) {
        int n = s.length();
        int[] next = new int[n + 1];
        // 如果第一个不匹配就只能从第 0 个位置开始了
        next[1] = 0;

        // 从第二个字符开始遍历
        for (int i = 2, j = 0; i <= n; i++) {
            // 如果不匹配且还能退，就退而求其次
            while (j != 0 && s.charAt(i - 1) != s.charAt(j)) {
                j = next[j];
            }
            // 如果相等就继续往后走
            if (s.charAt(i - 1) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static void main(String[] args) {
        System.out.println(getRepeatSubstr("abab"));
    }


}