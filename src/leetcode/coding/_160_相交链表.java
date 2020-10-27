package leetcode.coding;

/**
 * 找到两个相交链表的起始节点，如果两个链表没有交点，返回 null。假定链表中没有环
 *
 * 思路：如果两个链表相交，那么相交之后的长度肯定是相同的，假设链表A的长度为a+c（前半段+后半段），链表B的长度为b+c
 *      如果相交 则(a+c+b)+c = (b+c+a)+c，如果链表相交，a+c+b与b+c+a就会抵消，如果不相交。
 *
 *      换句话说，就相当于两个人，一个人从A起点出发遍历A链表，A链表遍历完了遍历B链表，另一个人从B起点出发遍历B链表，B遍历完了遍历A链表
 *      它们到达相交点时走过的路程是一样的，都是a+b+c
 */
public class _160_相交链表 {


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}