package leetcode.interviewbook;

public class 面试题05_02_二进制数转字符串 {

    /**
     * 给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。
     * 如果该数字不在0和1之间，或者无法精确地用32位以内的二进制表示，则打印“ERROR”。
     * <p>
     * https://bit.ly/3iyimWv
     * 首先得清楚二进制是怎么表示小数的.
     * 十进制中 125.456  可以分解成 1*10^2 + 2*10^1 + 5*10^0 + 4*10^-1 + 5*10^-2 + 6*10^-3
     *
     * 那么二进制中的小数表示应该也很清楚了
     * double 二进制 -> 十进制
     * 0.1 ->1*2^-1 (0.5)，所以 十进制的 0.5 对应二进制的 0.1，只要 0.5*2
     * 0.01 -> 1*2^-2（0.25） 十进制的 0.25 对应二进制的 0.01,
     *
     * double 十进制 -> 二进制
     * 0.5 -> 0.5*2,取个位（0或1）,截取num的小数部分作为num的新值
     * 0.625 -> 0.625*2 = 1.25(取1), 0.25*2 = 0.5（取0）,0.5*2 = 1（取1）
     *
     */

    // 十进制 double 转 二进制
    public static String printBin(double num) {
        StringBuffer sb = new StringBuffer();
        sb.append("0.");
        while(num != 0) {
            num *= 2;
            if(num >= 1) {
                sb.append(1);
                num -= 1;
            }else {
                sb.append(0);
            }
            if(sb.length() > 32)
                return "ERROR";
        }
        return sb.toString();
    }

    // 二进制转十进制
    public static void BinaryToDecimal(String s) {
        // 方法一, s为二进制串，radix 为进制
        String s1 = Integer.valueOf(s, 2).toString();

        // 方法二, 手撸
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int a = s.charAt(i) - '0';
            res = res * 2 + a;
        }
    }

    // 十进制转二进制
    public static void DecimalToBinary(int i) {
        // 方法一, s为二进制串，radix 为进制
        String s = Integer.toBinaryString(i);

        StringBuilder sb = new StringBuilder();
        while (i > 0) {
            sb.insert(0, i % 2);
            i /= 2;
        }
        System.out.println(sb);
    }


    public static void main(String[] args) {
        // BinaryToDecimal("101");
        // DecimalToBinary(6);
        System.out.println(printBin(0.5));
    }

}