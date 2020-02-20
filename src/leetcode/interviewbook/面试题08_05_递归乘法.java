package leetcode.interviewbook;

public class 面试题08_05_递归乘法 {

    /**
     * 不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
     * <p>
     * 很重要的是保证乘法范围内不溢出
     */

    public int multiply(int A, int B) {
        int temp = A + B;
        A = Math.min(A, B);
        B = temp - A;
        if (A == 1) {
            return B;
        } else {
            return B + multiply(A - 1, B);
        }
    }
}