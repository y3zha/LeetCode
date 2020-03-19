package leetcode.interviewbook;

public class 面试题05_06_整数转换 {

    //相同位置不用管，也就是需要统计不同位置的个数
    //那就只要异或了，相同为0，不同为1
    public int convertInteger(int A, int B) {
        return get(A ^ B);
    }

    //计算1的个数，用之前的题 191, n & (n-1),就能把最右边的1弄为0，一个奇数最右的为1，一个偶数最右的为0，与出来肯定为0
    private int get(int n) {
        int cnt = 0;
        while (n != 0) {
            n = n & (n - 1);
            cnt++;
        }
        return cnt;
    }

}