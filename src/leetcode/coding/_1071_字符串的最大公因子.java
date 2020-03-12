package leetcode.coding;

/**
 * 那我的思路是，能不能先找到s1的最大公因子和s2的最大公因子，然后这两个公因子取一样的部分
 * 但是这样就太复杂了
 *
 * 那么这几题来说，假设我们找到了它们的公因子，s，那么s1是由n个s构成的，s2是由m个s构成的，连起来就是n+m个s
 * 而 n+m个s和m+n个s是一样的，也就是 s1+s2 = s2+s1
 * 如果 s1+s2 = s2+s1，那就说明有解！如果不想等，就说明无解！
 * 那么在确定有解的情况下，再去找这个最长公因子，就可以利用gcd了
 *
 * https://leetcode-cn.com/problems/greatest-common-divisor-of-strings/solution/java-hen-jian-ji-yi-yan-jiu-neng-kan-ming-bai-by-s/
 */
public class _1071_字符串的最大公因子 {

    public String gcdOfStrings(String s1, String s2) {
        if (!(s1 + s2).equals(s2 + s1)) {
            return "";
        }
        return s1.substring(0, gcd(s1.length(), s2.length()));
    }

    /**
     * 默写下求最大公约数的算法
     * 欧里几徳算法 -> 辗转相除法，依赖于 gcd(a,b) = gcd(b,a % b)
     * 证明，令c为a和b的最大公约数，数学符号表示为c=gcd(a,b)
     *      因为任何两个实数的最大公约数c一定是存在的，也就是说必然存在两个数k1,k2使得a=k1*c, b=k2*c
     *      a % b 等价于存在整数r和k3使得余数 r = a – k3*b.
     *      即r = k1*c - k3* k2 * c = (k1-k3*k2)*c
     *      也就是说 a 和 b 的余数 r 是最大公因数c的倍数。所以gcd(a,b) = gcd(b,a % b)
     *      通过模运算的余数是最大公约数之间存在的整数倍的关系
     */
    int gcd (int a,int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    //迭代方式
    int gcd2(int a, int b) {
        while (b != 0) {
            int r = b;
            b = a % b;
            a = r;
        }
        return a;
    }

    //以前写的暴力算法
    public static String gcdOfStrings2(String str1, String str2) {
        String maxSubStr = "";
        for (int i = 1; i <= str2.length(); i++) {
            String sub = str2.substring(0, i);
            if (isConsistOfSubString(str1, sub) && isConsistOfSubString(str2, sub)) {
                maxSubStr = sub;
            }
        }
        return maxSubStr;
    }

    //判断字符串str是否由子字符串构成
    public static boolean isConsistOfSubString(String str, String subStr) {
        int len1 = str.length();
        int len2 = subStr.length();
        int times = len1 / len2;
        StringBuilder temp = new StringBuilder();
        if (times == 0) {
            return false;
        } else {
            for (int i = 0; i < times; i++) {
                temp.append(subStr);
            }
            if (temp.toString().equals(str)) {
                return true;
            } else {
                return false;
            }
        }
    }

}