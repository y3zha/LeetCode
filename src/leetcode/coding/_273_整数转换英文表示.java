package leetcode.coding;

public class _273_整数转换英文表示 {

    private final String[] belowTwenty = {
            "",
            "One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private final String[] belowHundred = {
            "", "Ten", "Twenty", "Thirty",
            "Forty", "Fifty", "Sixty",
            "Seventy", "Eighty", "Ninety"
    };

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        return helper(num);
    }

    private String helper(int num) {
        String res;
        if (num < 20) {
            res = belowTwenty[num] + " ";
        } else if (num < 100) {
            // 56 -> fifty six
            res = belowHundred[num / 10] + " " + helper(num % 10);
        } else if (num < 1000) {
            // 123 -> one hundred twenty three
            res = helper(num / 100) + " " + "Hundred" + " " + helper(num % 100);
        } else if (num < 1000000) {
            // 百万（million）以内 123675 -> one hundred twenty three thousand  six hundred seventy five
            res = helper(num / 1000) + " " + "Thousand" + " " + helper(num % 1000);
        } else if (num < 1000000000) {
            // 十亿（billion）以内
            res = helper(num / 1000000) + " " + "Million" + " " + helper(num % 1000000);
        } else {
            // 超过十亿
            res = helper(num / 1000000000) + " " + "Billion" + " " + helper(num % 1000000000);
        }
        return res.trim();
    }


}