package leetcode.coding;

import java.util.*;

// 这个题变为N叉数，每个节点的儿子个数不一样就很拿控制，我们可以使用'[' ']'来解决,遍历儿子节点前加入左括号，完成后加入右括号
// 序列化过程用递归
// 反序列化
// 使用栈每一个 “[” 前的节点，作为父节点（因为可能有很多层级）
// 将 “[” 与 “]” 之间的节点放到父节点的 “children” 列表中；
// 遇到 “]” 时，将该父节点出栈，尝试处理与该父节点同一层级、或上一层级的节点。

// [1[3[5][6]][2][4]]
// 1[3[5[],6[]],2[],4[]]  -- 生成这种
// 1[3[5][6]][2][4]
// 碰到[ 继续，碰到1，生成节点放进栈中，碰到左括号，val.length = 0, 继续
public class _428_序列化和反序列化N叉树 {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sb.toString();
    }

    private void helper(Node root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val);
        sb.append('[');
        for (Node child : root.children) {
            helper(child,sb);
        }
        sb.append(']');
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String s) {
        // System.out.println(s);
        if (s.length() == 0) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        Node root = null;
        StringBuilder val = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 有可能是正数/负数需要处理下
            if (c >= '0' && c <= '9' || c == '+' || c == '-') {
                val.append(c);
            } else if (c == '[') {
                if (val.length() == 0) {
                    continue;
                }
                // 如果碰到了左括号，把左括号之前的生成节点放进去
                // System.out.println(val);
                Node node = new Node(Integer.parseInt(val.toString()), new ArrayList<>());
                // 用完 val要重置
                val = new StringBuilder();
                stack.push(node);
                if (root == null) {
                    root = node;
                }
            } else if (c == ']'){
                // 如果碰到右括号了,出栈最顶上的，如果栈内不空，栈顶就是出战的这个的父节点
                Node pop = stack.pop();
                if (!stack.isEmpty()) {
                    Node node = stack.peek();
                    node.children.add(pop);
                }
            }
        }
        return root;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static void main(String[] args) {
        _428_序列化和反序列化N叉树 test = new _428_序列化和反序列化N叉树();
        test.deserialize("[1[3[5][6]][2][4]]");
    }

}