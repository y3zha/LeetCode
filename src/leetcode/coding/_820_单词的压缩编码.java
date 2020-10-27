package leetcode.coding;

import java.util.Arrays;

public class _820_单词的压缩编码 {

    /**
     * 一种简单的写法，但应该是面试官不喜欢的
     */
    public int minimumLengthEncoding(String[] words) {
        if (words == null || words.length == 0) return 0;
        // 首先按照单个word的长度来降序排序：["time", "me", "bell"]，长度一样的不用管
        Arrays.sort(words, ((o1, o2) -> o2.length() - o1.length()));
        StringBuilder sb = new StringBuilder();
        // 先把第一个单词放进去
        sb.append(words[0]).append("#");
        for (int i = 1; i < words.length; i++) {
            if (sb.indexOf(words[i] + "#") == -1) {
                sb.append(words[i]).append("#");
            }
        }
        return sb.length();
    }

    /**
     * 好久没写trie了，写一遍吧
     * 只要把每个单词倒序插入字典树就完事了，还需要判断某哥单个的逆序在不在字典树里，如果在，只要返回0，如果不在 ，就要返回 word.length + 1
     */

    public int minimumLengthEncoding2(String[] words) {
        Trie trie = new Trie();
        int res = 0;
        // 插入之前先要排序，必须先插入长的，不然，对于 ["me", "time"] 这种 use case，先插入 em，是新的，再插入 emit，也是新的，这就重复算了
        // 应当先插入 emit 再插入 em
        Arrays.sort(words, ((o1, o2) -> o2.length() - o1.length()));
        for (String word : words) {
            res += trie.insert(word);
        }
        return res;
    }

    class TrieNode{
        TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    class Trie{
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public int insert(String word) {
            TrieNode cur = root;
            boolean flag = false;
            for (int i = word.length() - 1; i >= 0; i--) {
                int idx = word.charAt(i) - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode();
                    flag = true;
                }
                cur = cur.children[idx];
            }
            if (flag) {
                return word.length() + 1;
            } else {
                return 0;
            }
        }
    }

}