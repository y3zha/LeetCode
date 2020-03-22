package leetcode.competition.week181;

import java.util.HashSet;

public class _5178_四因数 {

    //这个题超时很多次，需要剪枝，比赛时发现自己写的还是比较凌乱的
    public int sumFourDivisors(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res += getAns(num);
        }
        return res;
    }

    //到根号就可以，一次可以添加两个，一个是 i 另一个是n/i
    private int getAns(int num) {
        HashSet<Integer> set = new HashSet<>();
        int sqrt = (int) Math.sqrt(num);
        for (int i = 1; i <= sqrt; i++) {
            if (num % i == 0) {
                set.add(i);
                set.add(num / i);
                if (set.size() > 4) {
                    return 0;
                }
            }
        }
        if (set.size() != 4) return 0;
        int ans = 0;
        for (Integer i : set) {
            ans += i;
        }
        return ans;
    }
}