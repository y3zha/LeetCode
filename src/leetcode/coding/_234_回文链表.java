package leetcode.coding;

public class _234_回文链表 {


    /*
    方法1
    空间O(n)很好做，就是存下来check，这里做空间 O(1) 的
    我们就是要把链表分成两个部分来遍历
    那就是要用快慢指针
    1->2->2->1  // 找到第一个 2，拿到第二个2
    1->2->3->2->1   // 找到3，拿到3的下一个
    找到中间节点，切分链表，翻转后半段链表，然后逐一比对，这里不写


    方法2 hash
    hash = hash * seed + val,其中 seed 是一个质数 ，val 是节点的值
    如果正序hash和逆序hash得到的结果一致，可以认为是回文的

    hash 1 = a[1]*seed^(n-1) + a[2]*seed^(n-2) + ... + a[n]*seed^0

    hash 2 = a[1]*seed^(0) + a[2]*seed^(1) + ... + a[n]*seed^(n-1)

     */
    public boolean isPalindrome(ListNode head) {
        long hash1 = 0, hash2 = 0, power = 1;
        long seed = 805306457;
        ListNode p = head;
        while (p != null) {
            hash1 = hash1 * seed + p.val;
            hash2 = hash2 + power * p.val;
            power *= seed;
            p = p.next;
        }
        return hash1 == hash2;
    }

    // public ListNode getMidNode(ListNode head) {
    //     ListNode slow = head;
    //     ListNode fast = head;
    //
    //     while (fast.next != null && fast.next.next != null) {
    //         slow = slow.next;
    //         fast = fast.next.next;
    //     }
    //     return slow;
    // }

}