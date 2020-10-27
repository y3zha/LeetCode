package leetcode.coding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class _425_单词方块 {

    // 默认是可以重复使用的，所以不需要hashset去重了
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        // 遍历每一个单词作为起点
        Trie trie = new Trie(words);
        List<String> list = new ArrayList<>();
        for (String word : words) {
            list.add(word);
            dfs(words, trie, list, res);
            list.remove(list.size() - 1);
        }
        return res;

    }

    private void dfs(String[] words, Trie trie, List<String> list, List<List<String>> res) {
        if (list.size() == words[0].length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 获取list.size()这一列的前缀
        int idx = list.size();
        StringBuilder sb = new StringBuilder();
        for (String word : list) {
            sb.append(word.charAt(idx));
        }
        String prefix = sb.toString();
        List<String> temp = trie.search(prefix);
        for (String word : temp) {
            list.add(word);
            dfs(words, trie, list, res);
            list.remove(list.size() - 1);
        }
    }

    class TrieNode{
        public TrieNode[] children;
        public List<String> list;

        public TrieNode() {
            children = new TrieNode[26];
            list = new ArrayList<>();
        }
    }

    class Trie{
        private TrieNode root;

        public Trie(String[] words) {
            root = new TrieNode();
            insert(words);
        }

        public void insert(String[] words) {
            for (String word : words) {
                TrieNode cur = root;
                char[] sc = word.toCharArray();
                for (int i = 0; i < sc.length; i++) {
                    int idx = sc[i] - 'a';
                    if (cur.children[idx] == null) {
                        cur.children[idx] = new TrieNode();
                    }
                    cur = cur.children[idx];
                    cur.list.add(word);
                }
            }
        }

        // 查找以该单词开头的所有word
        public List<String> search(String prefix) {
            TrieNode cur = root;
            char[] sc = prefix.toCharArray();
            for (int i = 0; i < sc.length; i++) {
                int idx = sc[i] - 'a';
                if (cur.children[idx] == null) {
                    return new ArrayList<>();
                }
                cur = cur.children[idx];
            }
            return cur.list;
        }
    }



}