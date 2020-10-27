package leetcode.coding;

public class _1189_气球的最大数量 {

    // balloon
    public int maxNumberOfBalloons(String text) {
        int[] tmp = new int[26];
        for (int i = 0; i < text.length(); i++) {
            tmp[text.charAt(i) - 'a']++;
        }

        return min(tmp[0], tmp[1], tmp['l' - 'a'] / 2, tmp['o' - 'a'] / 2, tmp['n' - 'a']);
    }

    public int min(int... a) {
        int ans = 0x7fffffff;
        for (int i : a) {
            ans = Math.min(ans, i);
        }
        return ans;
    }

}