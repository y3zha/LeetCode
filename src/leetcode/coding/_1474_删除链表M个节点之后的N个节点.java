package leetcode.coding;

public class _1474_删除链表M个节点之后的N个节点 {

    // 要求原地修改链表
    public static ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode cur = head;
        int cnt;
        while (cur != null) {
            cnt = 0;

            while (cur != null && cnt < m - 1) {
                cnt++;
                cur = cur.next;
            }
            ListNode pre = cur;
            if (pre == null) break;
            cnt = 0;
            while (cur != null && cnt < n) {
                cnt++;
                cur = cur.next;
            }
            if (cur == null) {
                pre.next = null;
                break;
            }
            pre.next = cur.next;
            cur = pre.next;
        }
        return dummy.next;
    }

    // 快慢指针更好
    public static ListNode deleteNodes2(ListNode head, int m, int n) {
        ListNode slow = head, fast = head;
        while (slow != null) {
            for (int i = 0; i < m - 1 && slow != null; i++) {
                slow = slow.next;
            }
            for (int i = 0; i < m + n && fast != null; i++) {
                fast = fast.next;
            }
            if (slow == null) {
                break;
            }
            slow.next = fast;
            slow = slow.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(8);
        ListNode n9 = new ListNode(9);
        ListNode n10 = new ListNode(10);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        n9.next = n10;
        deleteNodes(n1, 2, 3);
    }
}