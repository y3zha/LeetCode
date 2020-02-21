package leetcode.coding;

import java.util.PriorityQueue;
import java.util.Queue;

public class _023_合并K个排序链表 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        Queue<ListNode> pq = new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);
        //先把每条链表的头加进去
        for (ListNode h : lists) {
            if (h != null) pq.add(h);
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (!pq.isEmpty()) {
            ListNode temp = pq.poll();
            cur.next = temp;
            cur = cur.next;
            if (temp.next != null) {
                pq.offer(temp.next);
            }
        }
        return dummy.next;
    }
}