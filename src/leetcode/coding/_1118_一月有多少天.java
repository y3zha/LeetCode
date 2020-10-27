package leetcode.coding;

public class _1118_一月有多少天 {

    public int numberOfDays(int Y, int M) {
        int[] m = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (M == 2) {
            return isLeap(Y) ? 29 : 28;
        } else {
            return m[M - 1];
        }
    }

    //能被4整除的大多是闰年，但能被100整除而不能被400整除的年份不是闰年
    // 四年一闰，百年不闰，四百年再闰。
    // 只要是4的倍数且不是100的倍数，或者是400的倍数，那就是闰年

    private boolean isLeap(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

}