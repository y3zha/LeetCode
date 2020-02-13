package leetcode.swordtooffer;

import java.util.LinkedList;
import java.util.Queue;

public class 面试题37_序列化二叉树 {

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
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    sb.append("#");
                } else {
                    sb.append(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }

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

            TreeNode root = new TreeNode(Integer.parseInt(s[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            boolean isLeftChild = true;
            for (int i = 1; i < s.length; i++) {
                if (!s[i].equals("#")) {
                    TreeNode node = new TreeNode(Integer.parseInt(s[i]));
                    if (isLeftChild) {
                        queue.peek().left = node;
                    } else {
                        queue.peek().right = node;
                    }
                    queue.offer(node);
                }
                if (!isLeftChild) {
                    queue.poll();
                }
                isLeftChild = !isLeftChild;
            }
            return root;
        }
    }
}