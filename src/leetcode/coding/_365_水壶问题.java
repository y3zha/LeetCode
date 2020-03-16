package leetcode.coding;

/**
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * 只能有三个操作 装满任意一个水壶、清空任意一个水壶、从一个水壶向另外一个水壶倒水，直到装满或者倒空
 */
public class _365_水壶问题 {

    /**
     * 方法一：暴力，因为每次水壶有三种操作，加满，倒出，相互倒，所以我们可以暴力枚举所有状态
     */
    public boolean canMeasureWater(int x, int y, int z) {
        return false;
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