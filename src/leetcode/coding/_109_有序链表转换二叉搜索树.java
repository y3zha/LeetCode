package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;
import lintcode.算法基础班._06_LinkedList_Array.ListNode;

/**
 * 和108题一样还是找中点，不过前面是有序数组，现在变为在链表中找中点。链表找中点就是快慢指针啊，快指针走两步，慢指针走一步
 */
public class _109_有序链表转换二叉搜索树 {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        return buildTree(head, null);
    }

    private TreeNode buildTree(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //找到中点了
        TreeNode root = new TreeNode(slow.val);
        root.left = buildTree(head, slow);
        root.right = buildTree(slow.next, tail);
        return root;
    }

}