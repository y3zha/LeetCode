package leetcode.coding;

public class _328_奇偶链表 {

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode curEven = even;

        while (odd.next != null && curEven.next != null) {
            odd.next = curEven.next;
            odd = odd.next;
            curEven.next = odd.next;
            curEven = curEven.next;
        }
        odd.next = even;
        return head;
    }

}