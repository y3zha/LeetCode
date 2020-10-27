package leetcode.coding;

/**
 * 编写一个程序判断给定的数是否为丑数。
 * 丑数即一个数等于2^x * 3^y * 5^z
 * 符合条件的数如：1, 2, 3, 4, 5, 6, 8, 9, 10, 12...我们可以认为 1 也是一个丑数（2^0 * 3^0 * 5^0）
 *
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * 思路：用这个数不断除2或除3或除5，每次循环除1次，当结果为1时，那就是丑数
 *
 * 做完了可以看丑数II
 */
public class _263_丑数 {

    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        while (num != 1) {
            if (num % 2 == 0) {
                num /= 2;
                continue;
            }
            if (num % 3 == 0) {
                num /= 3;
                continue;
            }
            if (num % 5 == 0) {
                num /= 5;
                continue;
            }
            return false;
        }
        return true;
    }



}