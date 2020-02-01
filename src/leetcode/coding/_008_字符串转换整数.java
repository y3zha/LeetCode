package leetcode.coding;

/**
 * 思路：
 *
 * 去空格
 * 判断第一个字符是否+、-号
 * 遍历（条件是当前遍历的字符是0-9，利用ascii，然后判断溢出）
 */
public class _008_字符串转换整数 {

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("-42"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));
    }

    public static int myAtoi(String str) {
        //1、去空格,再处理下空串
        String s = str.trim();
        if (s.length() == 0) {
            return 0;
        }
        //2、判断第一个字符是不是正负号
        int index = 0;
        int res = 0;
        int max = Integer.MAX_VALUE / 10;
        int sign = 1;
        if (s.charAt(0) == '+') {
            index++;
        } else if (s.charAt(0) == '-') {
            sign = -1;
            index++;
        }

        while (index < s.length()) {
            // 只有是数字的时候才加
            int temp = s.charAt(index) - '0';
            if (temp >= 0 && temp <= 9) {
                //首先处理溢出
                if (res > max || (res == max && (sign == 1 ? temp > 7 : temp > 8))) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                res = res * 10 + temp;
                index++;
            } else {
                break;
            }
        }
        return res * sign;
    }


}