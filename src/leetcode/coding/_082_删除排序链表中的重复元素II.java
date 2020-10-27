package leetcode.coding;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 */
public class _082_删除排序链表中的重复元素II {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //自己写的递归版本
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        } else {
            //它和它后面的元素相等，我们要找到它后面第一个和他不想等的node
            ListNode cur = head;
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            //此时cur.next可能为null 或者是 cur.val != cur.next.val
            //如果为null，就直接return null，如果是第二种请求，cur.next就是我们要的节点
            if (cur.next == null) {
                return null;
            } else {
                return deleteDuplicates(cur.next);
            }
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        deleteDuplicates(n1);

    }
}