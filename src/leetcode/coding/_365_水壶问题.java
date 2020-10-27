package leetcode.coding;

import java.util.*;

/**
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * 只能有三个操作 装满任意一个水壶、清空任意一个水壶、从一个水壶向另外一个水壶倒水，直到装满或者倒空
 */
public class _365_水壶问题 {

    /**
     * 方法一：bfs 因为每次水壶有三种操作，加满，倒出，相互倒，所以我们可以暴力枚举所有状态
     */

    private LinkedList<int[]> queue;
    private Set<Long> visited;
    private int capY;

    private void handle(int a, int b) {
        // y最大就是capY。这个相当于（capY + 1）进制，你假设capY = 9。就能理解了。
        // 或者就是将两个数字拼成string来看。比如2和3，capY = 9。那么拼起来就是23。用上面那个算出来也是23。
        // 而且能得出这个数的只有2和3组合可以。所以不会重复
        long key = a * (capY + 1L) + b;
        if (!visited.contains(key)) {
            queue.offer(new int[]{a, b});
            visited.add(key);
        }
    }

    private boolean bfs(int x, int y, int z) {
        capY = y;
        queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited = new HashSet<>();
        visited.add(0L);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int a = poll[0];
                int b = poll[1];

                if (a + b == z || a == z || b == z) {
                    return true;
                }

                // 六种状态
                // 给 a 中倒满水
                handle(x, b);
                // 给 b 中倒满水
                handle(a, y);
                // 清空 a 中水
                handle(0, b);
                // 清空 b 中水
                handle(a, 0);

                // 水壶 a 往水壶 b 倒
                int diff1 = Integer.min(a, y - b);
                handle(a - diff1, b + diff1);

                // 水壶 b 往水壶 a 倒
                int diff2 = Integer.min(b, x - a);
                handle(a + diff2, b - diff2);
            }
        }

        return false;
    }

    public boolean canMeasureWater(int x, int y, int z) {
        if (z == x || z == y || z == 0) {
            return true;
        }

        if (x + y < z || x == y && x + y != z) {
            return false;
        }

        return bfs(x, y, z);
    }


    /**
     * 方法二，数学，就是找ax + by = z，能否找到整数a, b使得方程有解
     * 有整数解时当且仅当z是a和b的最大公约数d的倍数。
     * 裴蜀定理
     */

    public boolean canMeasureWater2(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        if (x == z || y == z || x + y == z) {
            return true;
        }
        int gcd = gcd(x, y);
        return z % gcd == 0;
    }
    //gcd(a,b) = gcd(b,a % b)
    int gcd(int a,int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

}