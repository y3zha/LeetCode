package leetcode.swordtooffer;

import leetcode.test.Test;

public class 面试题67_把字符串转换成整数 {

    public int strToInt(String str) {
        if (str == null) {
            return 0;
        }
        String s = str.trim();
        if (s.length() == 0) {
            return 0;
        }
        char first = s.charAt(0);
        int sign = 1;
        int index = 0;
        if (first == '+') {
            index++;
        } else if (first == '-') {
            sign = -1;
            index++;
        }
        int res = 0;
        int max = Integer.MAX_VALUE / 10;
        while (index < s.length()) {
            int a = s.charAt(index) - '0';
            if (a >= 0 && a <= 9) {
                if (res > max || (res == max && (sign == 1 ? a > 7 : a > 8))) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                res = res * 10 + a;
                index++;
            } else {
                break;
            }
        }
        return res * sign;
    }


}