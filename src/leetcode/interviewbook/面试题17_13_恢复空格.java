package leetcode.interviewbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class 面试题17_13_恢复空格 {

    public int respace(String[] dictionary, String sentence) {
        Set<String> set = Arrays.stream(dictionary).collect(Collectors.toSet());
        int n = sentence.length();

        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < i; j++) {
                if (set.contains(sentence.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }
        return dp[n];
    }

    // 优化 字典树 Trie

    public int respace2(String[] dictionary, String sentence) {
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }
        int n = sentence.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (Integer idx : trie.search(sentence, i - 1)) {
                dp[i] = Math.min(dp[i], dp[idx]);
            }
        }
        return dp[n];
    }

    class Trie {
        TrieNode root;

        public Trie() {
            root =new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;

            for (int i = word.length() - 1; i >= 0; i--) {
                int idx = word.charAt(i) - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode();
                }
                cur = cur.children[idx];
            }
            cur.isWord = true;
        }

        // 查询以第 i + 1 个字符为结尾的单词有哪些（构建字典树时将单词逆序插入即可）
        public List<Integer> search(String sentence, int endPos) {
            List<Integer> list = new ArrayList<>();
            TrieNode cur = root;
            for (int i = endPos; i >= 0; i--) {
                int idx = sentence.charAt(i) - 'a';
                if (cur.children[idx] == null) {
                    break;
                }
                cur = cur.children[idx];
                if (cur.isWord) {
                    list.add(i);
                }
            }
            return list;
        }
    }

    class TrieNode {
        boolean isWord;
        TrieNode[] children;

        public TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }
    }
}