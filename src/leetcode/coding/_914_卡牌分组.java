package leetcode.coding;

import java.util.HashMap;

public class _914_卡牌分组 {

    // 遍历计数数组并求出最大公约数
    public boolean hasGroupsSizeX(int[] nums) {
        int n = nums.length;
        if (n < 2) return false;
        int[] cnt = new int[10000];
        for (int i = 0; i < n; i++) {
            cnt[nums[i]]++;
        }
        // 先得到第 1 个数的个数，以避免在循环中赋值
        int x = cnt[nums[0]];
        for (int i = 0; i < 10000; i++) {
            if (cnt[i] == 1) {
                return false;
            }
            if (cnt[i] > 1) {
                x = gcd(x, cnt[i]);
                if (x == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}