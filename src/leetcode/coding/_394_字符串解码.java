package leetcode.coding;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class _394_字符串解码 {

    public static String decodeString(String s) {
        char[] chs = s.toCharArray();

        //用Object，而不是开Integer和String两个stack
        Stack<Object> stack = new Stack<>();

        // 计算中括号前的数字是多少，不一定是一位数。
        int num = 0;

        for (char c : chs) {
            if (Character.isDigit(c)) {
                // 1. 数字则直接计算
                num = num * 10 + c - '0';
            } else if (c == '[') {
                // 2. 左括号, 先要把前面的数字放进去, 左中括号不用入栈
                stack.push(num);
                num = 0;
            } else if (c == ']') {
                // 3. 右括号, 出栈, 获取局部字符串再根据前面的数字得到乘次数再放入stack
                String str = popAndGetString(stack);
                int times = (int) stack.pop();
                String temp = String.join("", Collections.nCopies(times, str));
                stack.push(temp);
            } else {
                // 4. 正常字符, 放String类型
                stack.push(String.valueOf(c));
            }
        }
        return new StringBuilder(popAndGetString(stack)).reverse().toString();
    }

    // 这边一种情况是，前面可能已经变成正序的了，但是后面还有，后面来了，然后在这个方法中reverse一下就又变反了
    // 那我干脆只在最终结果处reverse，其他过程不reverse
    private static String popAndGetString(Stack<Object> stack) {
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty() && stack.peek() instanceof String) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public String decodeString2(String s) {
        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        LinkedList<Integer> iStack = new LinkedList<>();
        LinkedList<String> sStack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            // 碰到左括号需要记录这之间的字符串，
            if (c == '[') {
                sStack.addLast(ans.toString()); // 比如有 12[abc24[def 这样子的，就先要把abc24给放进去
                iStack.addLast(cnt);    // 先保存重复次数
                cnt = 0;    // 重置重复次数
                ans = new StringBuilder();
            } else if (c == ']') {
                // 碰到右括号了，获得重复次数，生成字符串
                StringBuilder sb = new StringBuilder();
                Integer times = iStack.removeLast();
                for (int i = 0; i < times; i++) {
                    sb.append(ans);
                }
                // 此时的 ans
                ans = new StringBuilder(sStack.removeLast() + sb);
            } else if (c >= '0' && c <= '9') {
                cnt = cnt * 10 + Integer.parseInt(c + "");
            } else {
                ans.append(c);
            }
        }
        return ans.toString();
    }

    public String decodeString3(String s) {
        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        LinkedList<String> sStack = new LinkedList<>();
        LinkedList<Integer> iStack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == '[') {
                // 首先把前面那串给放进 stack
                sStack.addLast(ans.toString());
                ans = new StringBuilder();
                // 把前面的cnt放进 iStack
                iStack.addLast(cnt);
                cnt = 0;
            } else if (c == ']') {
                // 首先生成[]之内的字符串
                StringBuilder tmp = new StringBuilder();
                Integer times = iStack.removeLast();
                for (int i = 0; i < times; i++) {
                    tmp.append(ans);
                }
                // 和之前的合并
                ans = new StringBuilder(sStack.removeLast() + tmp.toString());
            } else if (c >= '0' && c <= '9') {
                cnt = cnt * 10 + Integer.parseInt(c + "");
            } else {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}