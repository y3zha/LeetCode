package leetcode.coding;

import java.util.ArrayList;

public class _988_从叶结点开始的最小字符串 {

    //暴力的做法：先序遍历拿到所有的字符串，输出字典序最小的那个
    //[0,null,1] 输出 ba 而不是 a
    public String smallestFromLeaf(TreeNode root) {
        ArrayList<String> list = new ArrayList<>();
        dfs(root, "",list);
        list.sort(String::compareTo);
        return list.get(0);
    }

    private void dfs(TreeNode root, String s, ArrayList<String> list) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            s = s + (char)(root.val + 'a');
            list.add(new StringBuilder(s).reverse().toString());
            return;
        }
        s = s + (char)(root.val + 'a');
        dfs(root.left, s, list);
        dfs(root.right, s, list);
    }


}