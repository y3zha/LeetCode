package leetcode.coding;

public class _043_字符串相乘 {

    public static void main(String[] args) {
        multiply("9", "9");
    }

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int n = num1.length();
        int m = num2.length();
        String res = "0";   //保存计算结果

        //num2的每一位去和num1的每一位相乘
        for (int i = m - 1; i >= 0; i--) {
            //每次开始进位都要初始化为0
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            //首先要补多少个0,最后一位不补0，倒数第二位补1个.,..依此类推，也就是补m-1-i个
            for (int j = 0; j < m - 1 - i; j++) {
                sb.append(0);
            }
            //相乘
            int a = num2.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int b = num1.charAt(j) - '0';
                int sum = a * b + carry;
                sb.append(sum % 10);
                carry = 0;
                carry += sum / 10;
            }
            if (carry > 0) {
                sb.append(carry);
            }
            //得到当前串，并和res中的相加
            res = addOperation(res, sb.reverse().toString());
        }
        return res;
    }

    //两个字符串形式的相加，返回字符串形式的和
    private static String addOperation(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        // 首先令s1为较短的那个串
        if (s1.length() > s2.length()) {
            String temp = s2;
            s2 = s1;
            s1 = temp;
        }
        int n = s1.length();    //较短的那个串
        int m = s2.length();

        //首先是较短的串和较长的串的后半部分相加
        int j = m - 1;  //较长串的指针
        for (int i = n - 1; i >= 0; i--) {
            int a = s1.charAt(i) - '0';
            int b = s2.charAt(j--) - '0';
            int sum = a + b + carry;
            sb.append(sum % 10);
            carry = 0;
            carry += sum / 10;
        }
        //处理s2剩余部分
        for (int i = m - 1 - n; i >= 0; i--) {
            int sum = s2.charAt(i) - '0' + carry;
            sb.append(sum % 10);
            carry = 0;
            carry += sum / 10;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }


    //上面那个addOperater自己写的，太丑了。。参考下别人的
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry != 0; i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            sb.append(sum);
            carry = (x + y + carry) / 10;
        }
        return sb.reverse().toString();
    }

}