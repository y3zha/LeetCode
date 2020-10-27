package leetcode.interviewbook;

public class 面试题02_02_返回倒数第k个节点 {

    public int kthToLast(ListNode head, int k) {
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
        return slow.next.val;
    }
}