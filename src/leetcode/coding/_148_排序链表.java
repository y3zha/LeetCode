package leetcode.coding;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 看到这个时间复杂度就是归并排序了 分治思想
 */
public class _148_排序链表 {

    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //找到链表的中心的前一个，它的后一个就是后半段的头，比如2 1 3 4，我们找到1，它的后面3就是后一段的头,所以这里从head.next开始
        ListNode fast = head.next, solw = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            solw = solw.next;
        }
        //此时slow指向链表中间节点，后半段链表的头就是slow.next
        ListNode temp = solw.next;
        solw.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(temp);

        //排好前后半段，开始合并,不写迭代的合并了，写递归方式的合并，就是合并两个链表。。
        ListNode node = merge(l1, l2);
        return node;
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        sortList(n1);

    }
}