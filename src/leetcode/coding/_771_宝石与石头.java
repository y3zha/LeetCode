package leetcode.coding;

import java.util.HashSet;

public class _771_宝石与石头 {

    public int numJewelsInStones(String J, String S) {

        HashSet<Character> set = new HashSet<>();
        for (char c : J.toCharArray()) {
            set.add(c);
        }
        int ans = 0;
        for (char c : S.toCharArray()) {
            if (set.contains(c)) {
                ans++;
            }
        }
        return ans;
    }


}