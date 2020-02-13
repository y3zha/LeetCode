package leetcode.swordtooffer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 面试题35_复杂链表的复制 {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    //用HashMap把，和克隆图那个题差不多
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(head, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }
        //如果克隆过了，就返回
        if (map.containsKey(node)) {
            return map.get(node);
        }
        //没有克隆过则克隆当前节点
        Node clone = new Node(node.val);
        map.put(node, clone);
        clone.next = dfs(node.next, map);
        clone.random = dfs(node.random, map);
        return clone;
    }

}