package leetcode.coding;

public class _5308_或运算的最小翻转次数 {

    //位运算牛逼
    public int minFlips(int a, int b, int c) {
        int res = 0;
        for (int i = 0; i < 31; i++) {
            int p = (a >> i) & 1;
            int q = (b >> i) & 1;
            int r = (c >> i) & 1;
            if (r == 1) {   //如果是1，只有在pq都为0的情况下才需要加1
                if (p == 0 && q == 0) {
                    res += 1;
                }
            } else {    //如果是0的情况
                res += p + q;
            }
        }
        return res;
    }
}