package leetcode.coding;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 常规思路和dp思路，两种
 *
 * 常规思路：
 *
 * dp思路：
 *      https://www.bilibili.com/video/av74192978?from=search&seid=17421792936827387592
 *      还是利用栈，碰到左括号就入栈，碰到右括号就出栈，dp[i]代表当前连续的有效括号对是有多少对，我们知道多少对了，答案只要乘上2即可。
 *      这里要注意的是，我们碰到了右括号后，如果出栈匹配成功了，当前就算是一对，但还要看前面已经有多少对连续的了，看下视频就知道了
 */
public class _032_最长有效括号 {

    //看b站视频
    public int longestValidParentheses(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        int[] dp = new int[n + 1];
        Deque<Character> stack = new LinkedList<>();

        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (sc[i - 1] == '(') {
                stack.push('(');
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                    dp[i] = dp[i - 1] + 1;  //首先加上前面的，表示这一段括号是连续的，比如(())
                    //其次还要找到在这一组括号前，有没有连续的括号，比如前面是这样的()() 后面是这样的(())，我们要找到前面的那个连续的是多少
                    int preIndex = i - dp[i] * 2;
                    if (preIndex > 0) {
                        dp[i] += dp[preIndex];
                    }
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max * 2; //max记录的是有几对括号
    }

}