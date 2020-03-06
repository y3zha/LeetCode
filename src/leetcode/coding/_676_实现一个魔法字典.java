package leetcode.coding;

/**
 * trie = dfs
 *
 * 这个题它是要求必须替换一个字符的，和原单词一样的话，也就是没替换的话是过不去的
 */
public class _676_实现一个魔法字典 {

    class MagicDictionary {

        class  TrieNode{
            boolean hasWord;
            TrieNode[] children;

            public TrieNode() {
                hasWord = false;
                children = new TrieNode[26];
            }
        }

        TrieNode root;

        public MagicDictionary() {
            root = new TrieNode();
        }

        private void insert(String[] strings) {
            for (String word : strings) {
                TrieNode cur = root;
                for (int i = 0; i < word.length(); i++) {
                    int idx = word.charAt(i) - 'a';
                    if (cur.children[idx] == null) {
                        cur.children[idx] = new TrieNode();
                    }
                    cur = cur.children[idx];
                }
                cur.hasWord = true;
            }
        }

        private boolean query(TrieNode cur, String word, int index, int count) {
            //递归出口
            if (index == word.length()) {
                return cur.hasWord && count == 0;   //这个题它是要求必须替换一个字符的，和原单词一样的话，也就是没替换的话是过不去的
            }
            char c = word.charAt(index);    //待确认字母
            boolean flag = false;

            if (cur.children[c - 'a'] != null) {
                flag = query(cur.children[c - 'a'], word, index + 1, count);    //走儿子
                if (flag) {
                    return true;    //如果没找到 走下面的，而不是直接返回false
                }
            }

            //搜索不成功的情况，idx的位置就是失败的位置
            if (count > 0) {
                count--;
                for (int j = 0; j < cur.children.length; j++) {
                    //如果ch这个位置没儿子 跳过
                    if (cur.children[j] == null) continue;
                    //如果还是原来那个字符，跳过
                    if (j == c - 'a') continue;
                    return query(cur.children[j], word, index + 1, count);
                }
            }
            return false;
        }

        public void buildDict(String[] dict) {
            insert(dict);
        }

        public boolean search(String word) {
            // return magicSearch(root, word.toCharArray(), 0, 1);
            return query(root, word, 0, 1);
        }
    }
}