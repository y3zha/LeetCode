package leetcode.coding;

/**
 * 节点需要包含两个值：自己的值，和这条路径（prefix）的值
 */
public class _677_键值映射 {

    class MapSum {

        private class TrieNode{
            private TrieNode[] children;
            private int val;
            private boolean hasKey;

            public TrieNode() {
                children = new TrieNode[26];
                this.val = 0;
                hasKey = false;
            }
        }

        private TrieNode root;

        public MapSum() {
            root = new TrieNode();
        }

        public void insert(String key, int val) {
            TrieNode cur = root;
            char[] chs = key.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new TrieNode();
                    cur.children[index].val = val;
                }
                cur = cur.children[index];
            }
            cur.val = val;  //赋值/覆盖原有值
            cur.hasKey = true;
        }

        // 表示前缀的字符串，返回所有以该前缀开头的键的值的和。
        public int sum(String prefix) {
            TrieNode cur = root;
            char[] chs = prefix.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (cur.children[index] == null) {
                    return 0;
                }
                cur = cur.children[index];
            }
            return helper(cur);
        }

        //找到以这段prefix开头的所有键，求和
        private int helper(TrieNode cur) {
            if (cur == null) {
                return 0;
            }
            int res = cur.hasKey ? cur.val : 0;
            for (TrieNode child : cur.children) {
                res += helper(child);
            }
            return res;
        }
    }

}