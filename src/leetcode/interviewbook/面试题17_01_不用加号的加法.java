package leetcode.interviewbook;

public class 面试题17_01_不用加号的加法 {

    public int add(int a, int b) {
        while (b != 0) {
            int temp = a ^ b;
            b = a & b << 1;
            a = temp;
        }
        return a;
    }
}