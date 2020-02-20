package leetcode.interviewbook;

public class 面试题16_07_最大数值 {

    //你考虑过如何处理a – b中的整数溢出吗？ 这个提示很关键

    /**
     * 如何取出int类型的符号位，超过int类型怎么办
     * 后者可以用long解决，怎么取出符号位，符号位都是放第一个的 long一共是64位，那第一位就是符号位 也就是右移动63位即可
     * >>> 无符号右移，不管正负数，高位都是补0
     * >> 带符号右移，正数高位补0，负数高位补1，所以我们要选带符号右移
     *
     * 符号位为0 表示是正数，符号位为1，表示是负数
     *
     * a = 5, b = 10
     * temp = a - b = -5 sign = 1
     * return a - sign * temp
     *
     *
     * 或者 temp = b - a = 5 sign = 0
     * return b - sign * temp
     */
    public int maximum(int a, int b) {
        long temp = (long)a - (long)b;  //一定要先强制转换成long再做运算
        //System.out.println(temp);

        long sign = (temp >> 63) & 0x1;
        //System.out.println(sign);

        return (int) (a - sign * temp);
    }


}