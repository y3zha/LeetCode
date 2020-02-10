package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 设计一个算法，并编写代码来序列化和反序列化二叉树。将树写入一个文件被称为“序列化”，读取文件后重建同样的二叉树被称为“反序列化”。
 * 如何反序列化或序列化二叉树是没有限制的，你只需要确保可以将二叉树序列化为一个字符串，并且可以将字符串反序列化为原来的树结构。
 *
 * 通过BFS 先序遍历 求序列化，对于 null 这种 Node，也要 offer 到 queue 中去，用 "#" 来占位即可。最后可能会多几个 "#"，这个没关系可以保留
 * 反序列化的时候，如果当前位不是"#"，那么就是有效的 Node，但是我们需要判断它是左孩子还是右孩子
 *      很好判断，因为我们得到的是先序遍历 根 左 右 左 右，当前是左，下一个肯定是右了，我们只要搞个标记即可
 *
 */
public class _297_二叉树的序列化与反序列化 {

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

            //bfs 先序遍历树中所有node，也不用去判断儿子是不是null了
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                //如果是 null，序列化成#，否则加入node的值，把它左右儿子加进队列，也不用判空
                if (node == null) {
                    sb.append("#");
                } else {
                    sb.append(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
                //每个数据之间都要加,如果是最后一个就不要加，也就是队列为空的时候就不用加
                if (!queue.isEmpty()) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }

        public TreeNode deserialize(String data) {
            //没数据直接返回把
            if (data.length() == 0) {
                return null;
            }
            // 拆分出节点： 根 左 右 左 右 这样子的
            String[] s = data.split(",");
            //拿出root
            TreeNode root = new TreeNode(Integer.parseInt(s[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            //根节点放进去了，下一个就是左儿子
            boolean isLeftChild = true;

            for (int i = 1; i < s.length; i++) {
                //如果不是null节点，new一个节点，判断是队列中对后一个节点的左儿子还是右儿子
                if (!s[i].equals("#")) {
                    TreeNode child = new TreeNode(Integer.parseInt(s[i]));
                    if (isLeftChild) {
                        queue.peek().left = child;
                    } else {
                        queue.peek().right = child;
                    }
                    //把这个节放进队列
                    queue.offer(child);
                }
                //对于null的处理也是一样的，所以null就相当于直接跳过了，如果此时放的是队头的右儿子，那么这个节点ok了，可以pop了
                if (!isLeftChild) {
                    queue.poll();
                }
                isLeftChild = !isLeftChild;
            }
            return root;
        }
    }
}