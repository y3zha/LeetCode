package leetcode.coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _1044_最长重复子串 {

    /**
     * 假设存在一个长度为L的重复子串，那么必定存在长度为L0（L0 < L）的重复子串，如字符串"banana"中存在长度为3的重复子串“ana”
     * 那么"ana"的子串"an"也一定是重复子串。这就可以使我们可以使用二分去猜可能的最大长度。
     * 假设每次二分猜的长度为subLen，那么我们可以使用滑动窗口（长度即为subLen），从左往右滑动，判断是否存在重复子串。其中由于字符串长度较长，因此我么需要引入字符串hash
     */

    int[] res;
    StrHash sh;

    public String longestDupSubstring(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        this.sh = new StrHash(sc);
        this.res = new int[2];
        // right 要开n
        int left = 0, right = n;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (hasDuplicated(sc, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return s.substring(res[0], res[1]);
    }

    private boolean hasDuplicated(char[] sc, int len) {
        // 一个hash 可能对应多个字符串，所以就算hash值一样，还是需要再double check一下
        // list，用于存放相同hash值的子串的不同左边界坐标
        HashMap<Long, List<Integer>> map = new HashMap<>();
        int n = sc.length;
        for (int i = 0; i <= n - len; i++) {
            long hash = sh.getSubHash(i, i + len - 1);
            // 如果map里面有，需要看下是不是重复的
            if (map.containsKey(hash)) {
                List<Integer> list = map.get(hash);
                for (Integer pos : list) {
                    if (reallyExist(sc, pos, i, len)) {
                        res[0] = i;
                        res[1] = i + len;
                        return true;
                    }
                }
                // 没有通过double check，那就直接添加进list
                list.add(i);
            } else {
                map.computeIfAbsent(hash, k -> new ArrayList<>()).add(i);
            }
        }
        return false;
    }

    private boolean reallyExist(char[] sc, int oldPos, int newPos, int len) {
        //一个个字符比较就完事了
        for (int i = 0; i < len; i++) {
            if (sc[oldPos + i] != sc[newPos + i]) {
                return false;
            }
        }
        return true;
    }


    // 这个题和最长快乐前缀其实是一个思路
    // 推荐文章 https://segmentfault.com/a/1190000021665249
    class StrHash {
        private static final long P = 80536457;
        private static final int MOD = (int) (1e9 + 7);
        private long[] hash;
        private long[] power;
        private char[] s;

        public StrHash(char[] arr) {
            int n = arr.length;
            hash = new long[n];
            power = new long[n];
            this.s = arr;
            init();
        }

        private void init() {
            hash[0] = s[0];
            power[0] = 1;
            for (int i = 1; i < s.length; i++) {
                hash[i] = (hash[i - 1] * P + s[i]) % MOD;
                power[i] = (power[i - 1] * P) % MOD;
            }
        }

        private long getSubHash(int l, int r) {
            if (l == 0) {
                return hash[r];
            }
            // https://segmentfault.com/a/1190000021665249
            return ((hash[r] - hash[l - 1] * power[r - l + 1]) % MOD + MOD) % MOD;
        }
    }
}