package leetcode.coding;

public class _1021_删除最外层的括号 {

    public static String removeOuterParentheses(String S) {
        StringBuilder sb = new StringBuilder();
        int level = 0;
        for (char c : S.toCharArray()) {
            if (c == ')') --level;
            if (level >= 1) sb.append(c);
            if (c == '(') ++level;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        removeOuterParentheses("(()())(())");
    }

}