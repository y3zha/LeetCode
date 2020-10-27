package leetcode.coding;

import java.util.LinkedList;
import java.util.Stack;

public class _772_基本计算器III {

    public static int cal(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        LinkedList<Long> stack = new LinkedList<>();
        int i = 0;
        while (i < n) {
            if (sc[i] == ' ') continue;
            char op = '+';
            if (sc[i] == '+' || sc[i] == '-' || sc[i] == '*' || sc[i] == '/') {
                op = sc[i++];
            }
            while (i< n && sc[i] == ' ') i++;
            // 可能出现数字或者左括号了,下一个要么是数字，要么是左括号，我们要计算数字的值或者是左括号内的值
            long num = 0;
            while (i < n && Character.isDigit(sc[i])) {
                num = num * 10 + sc[i] - '0';
                i++;
            }
            if (i < n && sc[i] == '(') {
                int st = i + 1;
                int cnt = 0;
                while (i < n) {
                    if (sc[i] == '(') cnt++;
                    if (sc[i] == ')') cnt--;
                    if (cnt == 0) {
                        num = cal(s.substring(st, i));
                        i++;
                        break;
                    }
                    i++;
                }
            }
            switch (op) {
                case '-':
                    num *= -1;
                    break;
                case '*':
                    num = stack.pop() * num;
                    break;
                case '/':
                    num = stack.pop() / num;
                    break;
                default:
                    break;
            }
            stack.push(num);
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.remove();
        }
        return ans;
    }




    /**
     * 递归写法,和上面一模一样的，上面是我练习写的
     */
    public static int calculate(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        int i = 0;
        Stack<Integer> stack = new Stack<>();
        while (i < n) {
            if (sc[i] == ' ') {
                i++;
                continue;
            }
            char c = sc[i];
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                i++;
                while (i < n &&sc[i] == ' ') i++;
            }
            int num = 0;
            while (i < n && Character.isDigit(sc[i])) {
                num = num * 10 + sc[i] - '0';
                i++;
            }
            if (i < n && sc[i] == '(') {
                int st = i + 1;
                int cnt = 0;    // 计算括号对数
                while (i < n) {
                    if (sc[i] == '(') {
                        cnt++;
                    }
                    if (sc[i] == ')') {
                        cnt--;
                    }
                    if (cnt == 0) {
                        num = calculate(s.substring(st, i));
                        i++;
                        break;
                    }
                    i++;
                }
            }

            switch (c) {
                case '-':
                    num = -num;
                    break;
                case '*':
                    num = stack.pop() * num;
                    break;
                case '/':
                    num = stack.pop() / num;
                    break;
                default:
                    break;
            }
            stack.push(num);
        }
        int ans = 0;
        while (!stack.isEmpty()) ans += stack.pop();
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(cal("(2+6* 3+5- (3*14/7+2)*5)+3"));

    }
}