package leetcode.coding;

public class _1221_分割平衡字符串 {

    public static int balancedStringSplit(String s) {

        int n = s.length();
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'L') {
                cnt++;
            } else {
                cnt--;
            }
            if (cnt == 0) {
                sum++;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        balancedStringSplit("LLLRRR");
    }
}