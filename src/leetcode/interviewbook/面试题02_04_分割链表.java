package leetcode.interviewbook;

public class 面试题02_04_分割链表 {

    public ListNode partition(ListNode head, int x) {
        ListNode lDummy = new ListNode(-1);
        ListNode rDummy = new ListNode(-1);
        ListNode left = lDummy;
        ListNode right = rDummy;
        ListNode cur = head;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = null;
            if (cur.val < x) {
                left.next = cur;
                left = left.next;
            } else {
                right.next = cur;
                right = right.next;
            }
            cur = temp;
        }
        left.next = rDummy.next;
        return lDummy.next;
    }
}