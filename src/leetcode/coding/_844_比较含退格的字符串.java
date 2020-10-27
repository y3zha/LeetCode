package leetcode.coding;

public class _844_比较含退格的字符串 {


    public boolean backspaceCompare(String S, String T) {
        return getStr(S).equals(getStr(T));
    }

    private String getStr(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != '#') {
                sb.append(c);
            } else {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        return sb.toString();
    }


}