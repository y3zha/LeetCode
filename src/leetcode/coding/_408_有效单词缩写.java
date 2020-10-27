package leetcode.coding;

public class _408_有效单词缩写 {

    // apple -> a2le
    public static boolean validWordAbbreviation(String word, String abbr) {
        int n = word.length();
        int m = abbr.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
            } else if (abbr.charAt(j) >= '1' && abbr.charAt(j) <= '9') {
                // 数字可能不是单个的哦，比如有12这样一串的，我们要拿到这一串数字
                // int num = abbr.charAt(j) - '0';
                int num = 0;
                while (j < m && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                    num = num * 10 + abbr.charAt(j) - '0';
                    j++;
                }
                i += num;
            } else {
                return false;
            }
        }
        return i == n && j == m;
    }

    public static void main(String[] args) {
        // validWordAbbreviation("apple", "a2le");
        // validWordAbbreviation("internationalization", "i12iz4n");
        validWordAbbreviation("abbreviation", "a10n");
    }
}