package leetcode.coding;

import java.util.*;

/**
 * 字典树
 * 从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返回答案中字典序最小的单词。
 *
 * 思路：构建字典树，层次遍历，把最后一层单词放进去，排序，输出符合要求的
 */
public class _720_词典中最长的单词 {

    class TrieNode{
        TrieNode[] children;
        boolean hasWord;

        public TrieNode() {
            children = new TrieNode[26];
            hasWord = false;
        }
    }
    class Solution{
        TrieNode root;

        public String longestWord(String[] words) {
            Arrays.sort(words);     //这个排序非常关键
            String res = "";
            root = new TrieNode();
            for (String word : words) {
                TrieNode cur = root;        //一定每次都要重置
                char[] chs = word.toCharArray();
                boolean flag = true;   //一开始这是为true是为了但个字符比如a
                for (int i = 0; i < word.length(); i++) {
                    int index = chs[i] - 'a';
                    if (cur.children[index] == null) {
                        cur.children[index] = new TrieNode();
                    }
                    cur = cur.children[index];
                    //找单词，这个单词需要由其他前缀组成,如果还没到单词的长度，并且前缀没有被标记为hasWord的（就是没前缀的情况），那么就不行
                    if (i < word.length() - 1 && !cur.hasWord) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    cur.hasWord = true;
                    if (word.length() > res.length()) {
                        res = word;
                    }
                }
            }
            return res;
        }
    }

}