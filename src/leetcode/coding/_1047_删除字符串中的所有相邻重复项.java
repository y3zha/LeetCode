package leetcode.coding;

import java.util.Deque;
import java.util.LinkedList;

public class _1047_删除字符串中的所有相邻重复项 {

    // 类似消消乐
    // trick 是用栈来实现
    public String removeDuplicates(String s) {
        Deque<Character> stack = new LinkedList<>();
        int i = 0;
        while (i < s.length()) {
            if (!stack.isEmpty() && stack.peek().equals(s.charAt(i))) {
                stack.pop();
                i++;
                continue;
            }
            stack.push(s.charAt(i++));
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

}