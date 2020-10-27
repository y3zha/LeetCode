package leetcode.coding;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 思路一：用stack，把节点全部放进去，然后弹一般出来，逐个插入
 * 思路二：找到链表终点，翻转后半部分，然后一个个插入前半部分
 */
public class _143_重排链表 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    //方法一：用stack
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        //得到终点
        int mid = (stack.size() - 1) / 2;
        // 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
        ListNode p = head;
        while (mid != 0) {
            ListNode node = stack.pop();
            node.next = p.next;
            p.next = node;
            p = p.next.next;    //p.next已经变成插进来的了，所以要p.next.next
            mid--;
        }
        //最后要把中点的next置为空
        stack.pop().next = null;
    }

    // 同上
    public static void reorderList3(ListNode head) {
        LinkedList<ListNode> stack = new LinkedList<>();
        if (head == null || head.next == null) return;
        ListNode p = head;
        while (p != null) {
            stack.addLast(p);
            p = p.next;
        }
        ListNode cur = head;
        int mid = (stack.size() - 1) / 2;
        while (mid-- > 0) {
            ListNode node = stack.pollLast();
            node.next = cur.next;
            cur.next = node;
            cur = cur.next.next;
        }
        if (!stack.isEmpty()) {
            stack.pollLast().next = null;
        }
    }

    //方法二：翻转链表方式
    public static void reorderList2(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        //快慢指针找到中间点
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //slow现在所在的点就是中点,翻转后半部分链表
        ListNode postHead = reverseList(slow);

        //插入
        ListNode cur = head;
        while (postHead != null) {
            ListNode temp = postHead.next;
            postHead.next = cur.next;
            cur.next = postHead;
            postHead = temp;
            cur = cur.next.next;
        }
        cur.next = null;
    }

    //翻转链表--非递归方式
    private static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            //首先把cur的后面一个保存下来
            ListNode tempNode = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tempNode;
        }
        return pre;
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        reorderList(node1);

    }
}