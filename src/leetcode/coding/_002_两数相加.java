package leetcode.coding;

public class _002_两数相加 {

    class ListNode{
        int val;
        ListNode next;

        public ListNode(int x) {
            this.val = x;
            next = null;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);  //存放结果的dummy
        ListNode cur = dummy;       //移动指针
        int sum = 0;
        int carry = 0;

        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            cur.next = new ListNode(sum % 10);  //写一个66+552看一下就知道了
            cur = cur.next;
            carry = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            sum = l1.val + carry;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            carry = sum / 10;
            l1 = l1.next;
        }
        while (l2 != null) {
            sum = l2.val + carry;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            carry = sum / 10;
            l2 = l2.next;
        }
        //最后判断下carry
        if (carry == 1) {
            cur.next = new ListNode(1);
        }
        return dummy.next;
    }


}