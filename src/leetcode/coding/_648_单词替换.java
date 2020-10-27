package leetcode.coding;

import java.util.List;

/**
 * 给定一个由词根组成的词典和一个句子。将句子中的所有继承词用词根替换掉。
 *
 * 思路一：二哥的方法，直接排序加二分，反正如果一个词是另一个词的前缀，这俩词肯定是挨一起的。
 * 思路二：字典树，把字典中的放进前缀树，找到前缀树里第一个前缀然后替换，如果找不到前缀就使用原字符串。
 */
public class _648_单词替换 {

    public String replaceWords(List<String> dict, String sentence) {
        Trie trie = new Trie();
        trie.insert(dict);
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (trie.startsWith(words[i].substring(0, j + 1))) {
                    words[i] = words[i].substring(0, j + 1);
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]).append(" ");
        }
        return sb.toString().trim();
    }

    //使用字典树
    class TrieNode{
        TrieNode[] children = null;
        boolean hasWord;
        public TrieNode() {
            children = new TrieNode[26];
            hasWord = false;
        }
    }

    class Trie {
        TrieNode root = null;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(List<String> dict) {
            for (String s : dict) {
                TrieNode cur = root;
                char[] chs = s.toCharArray();
                for (int i = 0; i < chs.length; i++) {
                    int index = chs[i] - 'a';
                    if (cur.children[index] == null) {
                        cur.children[index] = new TrieNode();
                    }
                    cur = cur.children[index];
                }
                cur.hasWord = true;
            }
        }

        //前缀必须是单词才能替换
        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            char[] chs = prefix.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (cur.children[index] == null) {
                    return false;
                }
                cur = cur.children[index];
            }
            return cur.hasWord;
        }
    }


}