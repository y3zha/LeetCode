package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 这个题和297题一模一样，贴一下297题的题解
 *
 * 通过BFS 先序遍历 求序列化，对于 null 这种 Node，也要 offer 到 queue 中去，用 "#" 来占位即可。最后可能会多几个 "#"，这个没关系可以保留
 * 反序列化的时候，如果当前位不是"#"，那么就是有效的 Node，但是我们需要判断它是左孩子还是右孩子
 *      很好判断，因为我们得到的是先序遍历 根 左 右 左 右，当前是左，下一个肯定是右了，我们只要搞个标记即可
 */
public class _449_序列化和反序列化二叉搜索树 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Codec {

        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            StringBuilder sb = new StringBuilder();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    sb.append("#");
                } else {
                    sb.append(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
                //只要不是最后一个就得要加逗号隔开
                if (!queue.isEmpty()) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }

        public TreeNode deserialize(String data) {
            if (data.length() == 0) {
                return null;
            }
            String[] s = data.split(",");
            //得到root
            TreeNode root = new TreeNode(Integer.parseInt(s[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            boolean isLeftChild = true;
            for (int i = 1; i < s.length; i++) {
                //只要不是null 节点
                if (!s[i].equals("#")) {
                    TreeNode child = new TreeNode(Integer.parseInt(s[i]));
                    if (isLeftChild) {
                        queue.peek().left = child;
                    } else {
                        queue.peek().right = child;
                    }
                    queue.offer(child);
                }
                //无论是null还是非null的child，只要是右儿子，都要把它父亲弹出
                if (!isLeftChild) {
                    queue.poll();
                }
                isLeftChild = !isLeftChild;
            }
            return root;
        }
    }
}