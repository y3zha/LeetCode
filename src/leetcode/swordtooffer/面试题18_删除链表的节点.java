package leetcode.swordtooffer;

public class 面试题18_删除链表的节点 {

    //O（n）
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
                break;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * 此题要求O(1)
     * 只是平均复杂度为O(1)罢了
     * 1、如果待删除节点非尾节点，将后一个节点的值复制到当前节点即可，然后删除后一个节点 O(1)
     * 2、待删除节点为尾节点，要找到珊瑚虫节点的前一个节点进行删除 O(n)
     */

}