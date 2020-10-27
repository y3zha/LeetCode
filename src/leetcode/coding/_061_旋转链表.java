package leetcode.coding;

/**
 * https://leetcode-cn.com/problems/rotate-list/solution/chuan-zhen-yin-xian-by-liweiwei1419/
 *
 * 1、首先计算链表长度，如果k是链表长度的倍数，直接对链表长度取余
 * 2、得到链表长度后找到倒数第k个节点，它就是新的节点
 * 3、然后找到这个节点的前一个节点，它就是旋转之后的尾节点，把它后面置为null
 * 4、从新节点开始遍历找到尾巴，尾巴的下一个就是头，这样连起来即可
 *
 */
public class _061_旋转链表 {

    
      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        //首先计算链表长度
        int len = 1;
        ListNode tail = head;       //最后一步有用
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }
        k = k % len;
        if (k == 0) {
            return head;
        }

        //取余过后，k = 1，就是倒数第一个
        //找到新节点的位置,由于是倒数第k个，那么正数就是第len-k+1个
        //在这里要找到新节点的前一个节点，把它next置为null，它的前一个就是整数第len-k+1-1个，然后由于我们从head开始遍历，已经算第一个，还要再-1，就是len-k-1
        ListNode preNode = head;
        for (int i = 0; i < len - k - 1; i++) {
            preNode = preNode.next;
        }
        //新节点
        ListNode newNode = preNode.next;
        //把preNode的next置为null
        preNode.next = null;
        tail.next = head;
        return newNode;
    }
}