package leetcode.swordtooffer;

public class 面试题22_链表中倒数第k个节点 {

    /**
     * 快慢指针，快指针比慢指针先走k步
     * 1->2->3->4->5, 和 k = 2.
     * 返回链表 4->5.
     * fast为null时，slow正好是指向那个节点
     */

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            if (k > 0) {
                fast = fast.next;
                k--;
                continue;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}