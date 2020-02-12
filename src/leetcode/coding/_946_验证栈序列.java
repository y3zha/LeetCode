package leetcode.coding;

import java.util.Stack;

public class _946_验证栈序列 {

    //思路：模拟push操作，当pop的节点等于stack的top的节点，就出栈，最后栈空为true，否则为false
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;  //popped的指针
        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }
}