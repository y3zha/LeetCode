package leetcode.coding;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 把路径用"/"分隔，把当前目录压入栈中,遇到当前目录"."直接跳过不看，遇到".."弹出栈顶,最后返回栈中元素.
 */
public class _071_简化路径 {

    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();

        for (String s : path.split("/")) {
            //碰到".."就弹出栈顶
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (s.equals(".")) {
                continue;
            } else if (!s.isEmpty()) {
                stack.push(s);
            }
        }

        String res = "";
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        return res.isEmpty() ? "/" : res;
    }
}