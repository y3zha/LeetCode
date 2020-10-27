package leetcode.interviewbook;

import java.util.*;

public class 面试题17_22_单词转换 {

    public static void main(String[] args) {
        String beginword = "hit";
        String endword = "cog";
        ArrayList<String> list = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        findLadders(beginword, endword, list);
    }

    //找到任意一个可能的转换序列即可，原来是要找到所有的，找任意一个就最短路径吧
    public static List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if (!set.contains((endWord))) {
            return new ArrayList<String>();
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> vis = new HashSet<>();
        vis.add(beginWord);
        List<String> res = new ArrayList<>();
        //单词->前驱单词
        HashMap<String, String> map = new HashMap<>();  //用于回溯，方便找当前单词的前驱单词

        while (!queue.isEmpty()) {
            String poll = queue.poll();
            for (String word : getNext(poll, set)) {
                if (word.equals(endWord)) {
                    res.add(word);
                    map.put(word, poll);
                    //把它前驱都添加进去
                    String pre = map.get(word);
                    while (pre != null) {
                        res.add(pre);
                        pre = map.get(pre);
                    }
                    return res;
                }
                if (!vis.contains(word)) {
                    vis.add(word);
                    queue.offer(word);
                    map.put(word, poll);
                }
            }
        }
        Collections.reverse(res);
        return res;
    }

    private static ArrayList<String> getNext(String word, HashSet<String> set) {
        ArrayList<String> res = new ArrayList<>();
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char[] sc = word.toCharArray();
            for (int j = 0; j < 26; j++) {
                sc[i] = (char) (j + 'a');
                String s = new String(sc);
                if (set.contains(s)) {
                    res.add(s);
                }
            }
        }
        return res;
    }


}