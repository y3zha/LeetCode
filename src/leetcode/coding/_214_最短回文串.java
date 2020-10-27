package leetcode.coding;

public class _214_最短回文串 {

    /**
     * 这个题是kmp变形题，非常的好啊
     * abcd -> dcbabcd
     * 相当于市 dcba + abcd,去掉了a，
     * 把dcba放到abcd后头就是 abcd dcba，针对这个求一个最长公共前后缀 得到a，
     * 那么对于abcd，去掉a放在前面即可
     * <p>
     * 应该是这样
     *
     *
     * "aabba"
     * 倒过来，放在尾部 aabba+abbaa = aabbaabbaa,最长公共前后缀就是aabbaa，这比原串都长了
     * 所以在求最长公共前后缀的时候要有条界限！，不能超过原串,求next还是正常求，如果超过原串长度了，那只取到原串长度
     * 把aabba 去掉 aa 就是 bba，再倒过来 abb，再接到头部即可 abbaabba
     *
     * 可以在构造字符串的时候，加个# 比如abcd#dcba 这样在匹配前后缀时，相同长度就一定不会超过#号了
     *
     * "aacecaaa" -> aacecaaa+aaacecaa-> aacecaaaaaacecaa，最长公共前后缀aacecaa
     *
     *
     * aaaabbaa -> aaaabbaa aabbaaaa-> 最长公共前后缀
     *
     */

    public static String shortestPalindrome(String s) {
        // 特判 如果s本身是回文则不需要
        if (new StringBuilder(s).reverse().toString().equals(s)) return s;
        String str = new StringBuilder(s).reverse().toString();
        String tmp = s + "#" + str; // 一种巧妙的方式！
        int[] ne = getNext(tmp);
        int len = ne[tmp.length()];
        // 如果超过原串长度了，那只取到原串长度
        String ss = new StringBuilder(s.substring(len)).reverse().toString();

        return ss + s;
    }

    private static int[] getNext(String s) {
        char[] sc = s.toCharArray();
        int n = s.length();
        int[] next = new int[n + 1];
        next[1] = 0;    // 第一个字符挂掉就是回调第0个位置开始,第0哥位置我们不动
        // 第（前）i个字符
        for (int i = 2, j = 0; i <= n ; i++) {
            // 如果不匹配，并且还有路可退，继续会退
            while (j != 0 && sc[i - 1] != sc[j]) {
                j = next[j];
            }
            if (sc[i - 1] == sc[j]) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static void main(String[] args) {
        shortestPalindrome("aaaabbaa");
    }
}