package leetcode.coding;

import java.util.Arrays;

public class _468_验证IP地址 {

    public String validIPAddress(String IP) {
        // limit 为非正，模式将被应用尽可能多的次数，如果最后是 . 或者 :，则后面一段空的也会补上来
        String[] IP4Addr = IP.split("\\.", -1);
        if (IP4Addr.length == 4) {
            return isIp4Addr(IP4Addr);
        }
        String[] IP6Addr = IP.split(":", -1);
        if (IP6Addr.length == 8) {
            return isIp6Addr(IP6Addr);
        }
        return "Neither";
    }

    public String isIp4Addr(String[] IP4Addr) {
        for (String ip : IP4Addr) {
            int n = ip.length();
            if (n == 0 || n > 3) {
                return "Neither";
            }
            for (int i = 0; i < n; i++) {
                if (!Character.isDigit(ip.charAt(i))) {
                    return "Neither";
                }
            }
            int num = Integer.parseInt(ip);
            if (num > 255 || String.valueOf(num).length() != ip.length()) {
                return "Neither";
            }
        }
        return "IPv4";
    }

    public String isIp6Addr(String[] IP6Addr) {
        for (String ip : IP6Addr) {
            int n = ip.length();
            if (n == 0 || n > 4) {
                return "Neither";
            }
            for (int i = 0; i < n; i++) {
                char c = ip.charAt(i);
                if (!Character.isDigit(c) && !('a' <= c && c <= 'f') && !('A' <= c && c <= 'F')) {
                    return "Neither";
                }
            }
        }
        return "IPv6";
    }

    public static void main(String[] args) {
        String s1 = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
        String s2 = "2001:0db8:85a3:0:0:8A2E:0370:7334";

        String[] ss1 = s1.split(":", -2);
        String[] ss2 = s2.split(":", -2);

        System.out.println(Arrays.deepToString(ss1));
        System.out.println(Arrays.deepToString(ss2));
    }

}