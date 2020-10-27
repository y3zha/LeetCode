package leetcode.coding;

import java.util.Arrays;

public class _640_求解方程 {

    public String solveEquation(String equation) {
        String[] ss = equation.split("=");
        int[] left = getCnt(ss[0]);
        int[] right = getCnt(ss[1]);

        int x = left[0] - right[0];
        int val = right[1] - left[1];
        if (x == 0 && val == 0) {
            return "Infinite solutions";
        } else if (x == 0) {
            return "No solution";
        } else {
            return "x=" + val / x;
        }
    }

    private int[] getCnt(String s) {
        int[] ans = new int[2];
        // [xyz] 匹配包含任意一个字符
        // ？= 正向预查，表示捕获我们要获取东西的前面那段串
        // 例如 "x+5-3+x" -> [x, +5, -3, +x]
        String[] split = s.split("(?=[+-])");
        // System.out.println(Arrays.deepToString(split));
        for (String str : split) {
            if (str.equals("+x") || str.equals("x")) {
                ans[0] += 1;
            } else if (str.equals("-x")) {
                ans[0] -= 1;
            } else if (str.contains("x")) {
                ans[0] += Integer.parseInt(str.substring(0, str.length() - 1));
            } else {
                ans[1] += Integer.parseInt(str);
            }
        }
        return ans;
    }
}