package leetcode.interviewbook;

import java.util.Stack;

public class 面试题02_06_回文链表 {

    /**
     * 遍历一边的方法，先找到中间节点把前半部分链表放进栈中
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        Stack<Integer> stack = new Stack<>();
        // 1 -> 2 -> 3 -> 4  偶数个，出循环时，栈内存放了12 slow指向 3，fast指向null
        // 1 -> 2 -> 3 -> 4 -> 5，奇数个，出循环时，栈内存放了12 slow指向了3，fast指向5，不为null，应该从slow.next开始比较
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            stack.add(slow.val);
            slow = slow.next;
        }
        //处理下奇数个的情况
        if (fast != null) {
            slow = slow.next;
        }
        while (slow != null) {
            if (slow.val != stack.pop()) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }
}