package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _1469_寻找所有的独生节点 {

    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        // 处理当前节点
        // 递归出口
        if (root == null || (root.left == null && root.right == null)) {
            return ans;
        }
        if (root.left != null && root.right == null) {
            ans.add(root.left.val);
        }
        if (root.left == null && root.right != null) {
            ans.add(root.right.val);
        }

        List<Integer> left = getLonelyNodes(root.left);
        List<Integer> right = getLonelyNodes(root.right);

        ans.addAll(left);
        ans.addAll(right);
        return ans;
    }


}