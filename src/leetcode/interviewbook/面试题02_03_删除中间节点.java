package leetcode.interviewbook;

public class 面试题02_03_删除中间节点 {

    //node是给定删除的节点，删除它
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}