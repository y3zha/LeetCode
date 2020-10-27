package leetcode.coding;

import comic.Chapter_03.src.Heap;
import lintcode.算法基础班._06_LinkedList_Array.ListNode;

/**
 * 这个题是单向链表，不是双向链表，双向链表能从右向左插入，单向链表只能从左向右插入
 * 先找个排头dummy,然后依次从head节点放入dummy,只需要依次dummy现有节点比较,插入其中!
 */
public class _147_对链表进行插入排序 {

    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            //先保存cur的下一个，因为等会就丢失了
            ListNode temp = cur.next;
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            //此时pre.next为null或pre.next.val > cur.val
            cur.next = pre.next;
            pre.next = cur;
            //重置cur和pre
            pre = dummy;
            cur = temp;
        }
        return dummy.next;
    }

    //上面的代码，每次都要从头开始遍历，导致效率不高，这边可以增加个tail记录dummy链表中当前最后一个节点的值
    //如果最后一个的值比cur的值小，那就直接放在最后，否则就从头遍历即可
    public ListNode insertionSortList2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        ListNode cur = head;
        ListNode tail = dummy;

        while (cur != null) {
            if (tail.val < cur.val) {
                tail.next = cur;
                //后移
                tail = cur;
                cur = cur.next;
            } else {
                ListNode temp = cur.next;
                tail.next = temp;   //没有这句话，tail.next后面接的就是cur，然后cur.next = pre.next; pre.next = cur;就成环了，因为成环接下来会一直困在主while循环
                while (pre.next != null && pre.next.val < cur.val) {
                    pre = pre.next;
                }
                cur.next = pre.next;
                pre.next = cur;
                //后移
                cur = temp;
                pre = dummy;
            }
        }
        return dummy.next;
    }

}