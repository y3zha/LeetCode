package leetcode.coding;

/**
 *  异或运算，相同为0，不同为1，异或完看有多少个位上为1即可
 */
public class _461_汉明距离 {

    public int hammingDistance(int x, int y) {
        int a = x ^ y;
        int count = 0;
        while (a != 0) {
            if ((a & 1) == 1) {
                count++;
            }
            a = a >> 1;
        }
        return count;
    }
}