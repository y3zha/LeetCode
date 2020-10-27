package leetcode.interviewbook;

public class 面试题05_04_下一个数 {

    //暴力
    public int[] findClosedNumbers(int num) {
        int[] res = new int[2];
        int key = Integer.bitCount(num);
        int t = num + 1;
        while (t < Integer.MAX_VALUE && key != Integer.bitCount(t)) {
            t++;
        }
        res[0] = t == Integer.MAX_VALUE ? -1 : t;
        t = num - 1;
        while (t >= 0 && Integer.bitCount(t) != key) {
            t--;
        }
        res[1] = t < 0 ? -1 : t;
        return res;
    }
}