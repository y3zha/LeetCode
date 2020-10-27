package leetcode.interviewbook;

import org.apache.commons.collections15.functors.IfClosure;

import java.util.Stack;

public class 面试题16_26_计算器 {

    // 同227题
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] sc = s.toCharArray();
        int i = 0, ans = 0, n = s.length();
        while (i < n) {
            if (sc[i] == ' ') {
                i++;
                continue;
            }
            char op = sc[i];
            if (op == '+' || op == '-' || op == '*' || op == '/') {
                i++;
                while (i < n && sc[i] == ' ') i++;
            }
            int num = 0;
            while (i < n && Character.isDigit(sc[i])) {
                num = num * 10 + sc[i] - '0';
                i++;
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
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}