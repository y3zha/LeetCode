package leetcode.coding;

public class _546_移除盒子 {

    /**
     * 以3 4 3为例， dp[i][j] 计算出来的值大小为 3， 实际上先移除中间的4， 结果是5
     * <p>
     * 参考 https://leetcode-cn.com/problems/remove-boxes/solution/guan-fang-fang-fa-2ji-yi-hua-sou-suo-dong-hua-tu-j/
     */

    // 递归写法
    private int[][][] f = new int[100][100][100];

    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        return cal(boxes, 0, n - 1, 0);
    }

    private int cal(int[] boxes, int l, int r, int k) {
        if (l > r) {
            return 0;
        }
        if (f[l][r][k] != 0) {
            return f[l][r][k];
        }
        // 计算右边有多少个和 boxes[r] 相等的，如果相等，r左移
        while (r > l && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        // 此时 r 指向最后一个相等的位置，采用策略一
        f[l][r][k] = cal(boxes, l, r - 1, 0) + (k + 1) * (k + 1);
        // 策略二：从 l 开始走，找到与r相同的，消掉 i+1到r-1之间的，看图解就好了
        for (int i = l; i <= r - 1; i++) {
            if (boxes[i] == boxes[r]) {
                f[l][r][k] = Math.max(f[l][r][k], cal(boxes, i + 1, r - 1, 0) + cal(boxes, l, i, k + 1));
            }
        }
        return f[l][r][k];
    }


}