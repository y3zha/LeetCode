package leetcode.interviewbook;

import java.util.LinkedHashSet;

public class 面试题02_01_移除重复节点 {

    // 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
    //如果排序的话，就是模拟
    //由于未排序，需要用到Set，这边可以直接使用LinkedHashSet,得到这个顺序
    public ListNode removeDuplicateNodes(ListNode head) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        ListNode cur = head;
        while (cur != null) {
            set.add(cur.val);
            cur = cur.next;
        }
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        for (Integer val : set) {
            p.next = new ListNode(val);
            p = p.next;
        }
        return dummy.next;
    }
}