package leetcode.competition.week181;

public class _5367_最长快乐前缀 {

    /**
     * 求最长公共前后缀是什么，看到这个题就可以用kmp思路解决
     * 如果用py就直接暴力，居然过了
     *
     * 在这里还要学一下多项式hash
     */

    public static void main(String[] args) {
        _5367_最长快乐前缀 test = new _5367_最长快乐前缀();
        test.longestPrefix2("level");
    }

    public String longestPrefix2(String s) {
        int[] next = getNext(s);
        int len = next[s.length()];
        return s.substring(0, len);
    }

    private int[] getNext(String s) {
        int n = s.length();
        int[] next = new int[n + 1];
        int i = 0, j = -1;
        next[0] = -1;
        while (i < n ) {
            if (j == -1 || s.charAt(i) == s.charAt(j)) {
                next[++i] = ++j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    //多项式hash
    class StrHash{
        private static final long P = 805306457;
        private static final long MOD = (int) (1e9 + 7);
        private long[] hash;
        private long[] power;
        private char[] arr;

        public StrHash(char[] arr) {
            this.arr = arr;
            int len = arr.length;
            hash = new long[len];
            power = new long[len];
            calHashAndPower();
        }

        private void calHashAndPower() {
            hash[0] = arr[0];
            power[0] = 1;
            for (int i = 1; i < arr.length; i++) {
                hash[i] = (hash[i - 1] * P + arr[i]) % MOD;
                power[i] = (power[i - 1] * P) % MOD;
            }
        }

        private long getSubStrHash(int l, int r) {
            if (l == 0) return hash[r];
            //相减就得到了这一段字符串的hash
            return ((hash[r] - hash[l - 1] * power[r - l + 1]) % MOD + MOD) % MOD;
        }
    }

    public String longestPrefix(String s) {
        char[] sc = s.toCharArray();
        StrHash sh = new StrHash(sc);
        int n = s.length();
        //从倒数第二个开始看，因为最长公共前后缀不能是自己本身最大的长度也就是0~n-2
        for (int i = n - 2; i >= 0; i--) {
            long prefix = sh.getSubStrHash(0, i);
            long suffix = sh.getSubStrHash(n - i - 1, n - 1);
            if (prefix == suffix) {
                return s.substring(0, i + 1);
            }
        }
        return "";
    }

}