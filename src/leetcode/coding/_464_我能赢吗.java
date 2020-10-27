package leetcode.coding;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class _464_我能赢吗 {
    /*
    大致思路

    题目中说了，maxChoosableInteger 小于等于20

    dp[cur] 对于当前状态 cur，它表示在剩下的集合中，先手能否必胜。
    如果先手在剩下的集合中取走一个数字（也就是这个数字之前没被用过），然后现在剩下数字的集合变为 next（对应一个状态 cur | (1<<i)）
    那么后手如果此时是必败的，先手就必胜了。
    或者说，在这个状态之前，我已经知道和是多少了，如果在剩下的集合中，我取一个最大的数字，加上累计和
    如果大于 desiredTotal，那就先手必胜了


    // 直接dfs过不去，要加memo来搜索
     */

    int maxChoosableInteger;
    HashMap<Integer, Boolean> map;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        this.maxChoosableInteger = maxChoosableInteger;
        // 两次特判
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        if (maxChoosableInteger * (1 + maxChoosableInteger) / 2 < desiredTotal) {
            return false;
        }
        map = new HashMap<>();
        // 第一个入参表述哪些数字已经被使用了
        return dfs(0, desiredTotal);
    }

    private boolean dfs(int used, int desiredTotal) {
        if (map.containsKey(used)) {
            return map.get(used);
        }
        // 遍历数字
        boolean res = false;
        for (int i = maxChoosableInteger; i >= 1; i--) {
            // 如果这个数字(1 << i) 没被用过，我尝试去使用
            if ((used & (1 << i)) == 0) {
                // 如果这个数字加上之前的和大于等于当前的 desiredTotal，或者后者必败，那么就必胜
                // 这里的 desiredTotal是自减的了，我们这楼关注的是后者必败，如果
                if (i >= desiredTotal || !dfs(used | (1 << i), desiredTotal - i)) {
                    res = true;
                    break;
                }
            }
        }
        map.put(used, res);
        return res;
    }


}