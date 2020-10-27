package leetcode.coding;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 127是找出最短路径，这里要求输出转换序列，如果不存在输出空，如果存在多个转换序列，都输出。
 *
 * 找出所有首先想到的是dfs
 */
public class _126_单词接龙II {

    /**
     * 看下面的写法，直接dfs、直接双向bfs求最短路径+dfs都是过了，过不去
     * 盲搜会展示非常多的无用路径，你得让搜索时候知道当前点离终点有多远，必须是越走越近
     * 这里的思路是从终点先倒过来使用bfs，计算每个结点到终点的距离是多少，存在hash表中
     * 接着从起点出发做dfs，在dfs的过程中，保证每一步走都离终点又近了一步，这样就一定不会出现中间的无用状态（原地踏步或越走越远）
     */

    //我写的版本没过。。过了30个用例，超时，后来看了powcai哥的代码，发现bfs的时候，其实只要一碰到endWord，就能break了
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return res;
        }

        //出现一个bug，发现dfs一开始报了空指针异常，debug发现是没有beginWord，所以要先把beginWord放进wordList
        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }

        //存放这个word和它相关的word
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        //存放当前word到终点的距离
        HashMap<String, Integer> dist = new HashMap<>();
        // start -> end : 获取每个结点到终点的最短距离,以及每个节点的邻居
        bfs(map, dist, beginWord, endWord, wordList);

        ArrayList<String> list = new ArrayList<>();
        list.add(beginWord);
        // start -> end
        dfs(map, dist, beginWord, endWord, list, res);
        System.out.println(res);
        return res;
    }

    private void dfs(HashMap<String, ArrayList<String>> map, HashMap<String, Integer> dist, String curWord, String endWord, ArrayList<String> list, List<List<String>> res) {
        if (curWord.equals(endWord)) {
            res.add(new ArrayList<>(list));
            return;
        }
        ArrayList<String> adj = map.get(curWord);
        for (String next : adj) {
            //要判断当前单词到终点是不是越走越近，如果不是我们就不选它
            if (dist.containsKey(next) && dist.get(next) == dist.get(curWord) + 1) {
                list.add(next);
                dfs(map, dist, next, endWord, list, res);
                list.remove(list.size() - 1);
            }
        }
    }

    private void bfs(HashMap<String, ArrayList<String>> map, HashMap<String, Integer> dist, String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        dist.put(beginWord, 0);

        // 初始化map
        for (String s : wordList) {
            map.put(s, new ArrayList<>());
        }
        //如果wordList中没有beginWord，要添加进去
        map.put(beginWord, new ArrayList<>());
        boolean flag = false;
        while (!queue.isEmpty()) {
            //遍历每一层
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                String word = queue.poll();
                //获取与这个word所有有关的单词
                ArrayList<String> list = getAjd(word, wordList);
                map.put(word, list);
                //遍历这个list中的所有单词，他们到终点的距离都要+1（因为我这里是从后往前写的）
                for (String s : list) {
                    if (s.equals(endWord)) {
                        flag = true;
                    }
                    if (!dist.containsKey(s)) {
                        dist.put(s, dist.get(word) + 1);
                        queue.offer(s);
                    }
                }
            }
            if (flag) {
                break;
            }
        }
    }

    private ArrayList<String> getAjd(String word, List<String> wordList) {
        ArrayList<String> adj = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char[] sc = word.toCharArray();
            for (int j = 0; j < 26; j++) {
                sc[i] = (char) (j + 'a');
                String temp = new String(sc);
                if (wordList.contains(temp) && !temp.equals(word)) {
                // if (wordList.contains(temp)) { //不允许添加自己
                    adj.add(temp);
                }
            }
        }
        return adj;
    }

    /**
     * 下面是powcai的版本，和上面用hashmap的思路一样的，过了
     */
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        Map<String, ArrayList<String>> map = new HashMap<>();
        Map<String, Integer> dist = new HashMap<>();

        bfs(beginWord, endWord, map, dist, wordSet);
        dfs(beginWord, endWord, map, 0, res, new ArrayList<>(Arrays.asList(beginWord)), dist);
        return res;
    }

    private void dfs(String beginWord, String endWord, Map<String, ArrayList<String>> map, int step, List<List<String>> res, ArrayList<String> list, Map<String, Integer> dist) {

        if (list.get(list.size() - 1).equals(endWord)) {
            res.add(new ArrayList<>(list));
        }
        for (String word : map.get(list.get(list.size() - 1))) {
            if (dist.get(word) == step + 1) {
                list.add(word);
                dfs(word, endWord, map, step + 1, res, list, dist);
                list.remove(list.size() - 1);
            }
        }
    }

    private void bfs(String beginWord, String endWord, Map<String, ArrayList<String>> map, Map<String, Integer> dist, Set<String> wordSet) {
        for (String s : wordSet) {
            map.put(s, new ArrayList<String>());
        }
        map.put(beginWord, new ArrayList<>());  //wordSet中可能没beginWord
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        dist.put(beginWord, 0);
        boolean flag = false;
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                String word = queue.poll();
                for (String nw : getAdj(word, wordSet)) {
                    map.getOrDefault(word, new ArrayList<>()).add(nw);
                    if (nw.equals(endWord)) {
                        flag = true;
                    }
                    if (!dist.containsKey(nw)) {
                        dist.put(nw, step);
                        queue.offer(nw);
                    }

                }
            }
            if (flag) {
                break;
            }
        }
    }

    private ArrayList<String> getAdj(String word, Set<String> wordList_set) {
        ArrayList<String> ans = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            char[] chars = word.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                chars[i] = ch;
                String tmp = new String(chars);
                if (!tmp.equals(word) && wordList_set.contains(tmp)) ans.add(tmp);
            }
        }
        return ans;
    }









    //下面是我一开始的版本，分别为 dfs + 双向bfs+dfs，也都没过


    //DFS ,,我这个超时，然后做了剪枝，就是dfs的时候判断当前list的长度是否大于min，大于就不用再dfs了,but剪枝了还没过
    /*int min = Integer.MAX_VALUE;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return res;
        }
        ArrayList<String> list = new ArrayList<>();
        list.add(beginWord);
        //此时得到的是所有的转换序列我们要最短的，dfs的时候顺便记录下最小转换序列的长度
        dfs(wordList, endWord, list, res);
        System.out.println(min);
        // System.out.println(res);
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i).size() != min) {
                res.remove(i);
            }
        }
        return res;
    }

    private void dfs(List<String> wordList, String endWord, ArrayList<String> list, List<List<String>> res) {

        if (list.get(list.size() - 1).equals(endWord)) {
            if (list.size() < min) {
                min = list.size();
                res.clear();
                res.add(new ArrayList<>(list));
            } else if (list.size() == min) {
                res.add(new ArrayList<>(list));
            }
        }
        //剪枝：如果list长度大于已有最短路径长度，不用dfs了，return把
        if (list.size() > min) {
            return;
        }
        for (String word : wordList) {
            //判断这个word和上一个添加到list中的单词的关系，如果符合要求，可以添加。首要条件是这个单词不能是自己本身,并且添加过的不能再重复，不然会死循环
            String s = list.get(list.size() - 1);
            if (!word.equals(s) && vaild(word, s) && !list.contains(word)) {
                list.add(word);
                dfs(wordList, endWord, list, res);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean vaild(String word, String s) {
        // 判断word能否由s替换掉一个字符变换过去
        for (int i = 0; i < s.length(); i++) {
            char[] sc = s.toCharArray();
            for (int j = 0; j < 26; j++) {
                sc[i] = (char) (j + 'a');
                String temp = new String(sc);
                if (word.equals(temp)) {
                    return true;
                }
            }
        }
        return false;
    }*/

    /**
     * 剪枝过后还是没过，因为可能存在这么一种最坏情况，前面的list的长度都是大于最短路径的，只有最后一个才是最短路径，这样还是相当于没剪枝
     * 所以这里先通过bfs找到最短路径，再dfs得到结果
     * 在 BFS 中，就把每个节点的所有相邻节点保存到 HashMap 中，就省去了 DFS 再去找相邻节点的时间
     * 然而这个优化，对于 leetcode 的 tests 并没有什么影响。
     * <p>
     * 再优化，我们把每个单词出现在第几层给保存下来，如果后面出现就不用看了
     * https://leetcode-cn.com/problems/word-ladder-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-3/
     * <p>
     * 我直接用双向bfs搜最短路径+dfs写写看
     * 还是过不去
     */
    /*int minDist = Integer.MIN_VALUE;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return res;
        }
        minDist = bfs(beginWord, endWord, wordList);
        if (minDist == 0) {
            //不可达直接return
            return res;
        }
        //System.out.println(minDist);
        ArrayList<String> list = new ArrayList<>();
        list.add(beginWord);
        dfs(wordList, endWord, list, res);
        //System.out.println(res);
        return res;
    }

    //双向bfs
    private int bfs(String beginWord, String endWord, List<String> wordList) {
        //这边传wordlist进来会导致dfs的时候变空了。。所以我们这里用个set
        Set<String> set = new HashSet<>();
        set.addAll(wordList);
        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        s1.add(beginWord);
        s2.add(endWord);

        int step = 1;
        int n = beginWord.length();
        while (!s1.isEmpty() && !s2.isEmpty()) {
            step++;
            if (s1.size() > s2.size()) {
                Set<String> temp = s1;
                s1 = s2;
                s2 = temp;
            }
            Set<String> s = new HashSet<>();
            for (String word : s1) {
                for (int i = 0; i < n; i++) {
                    char[] sc = word.toCharArray();
                    for (int j = 0; j < 26; j++) {
                        sc[i] = (char) (j + 'a');
                        String temp = new String(sc);
                        if (s2.contains(temp)) {
                            return step;
                        }
                        if (set.contains(temp)) {
                            set.remove(temp);
                            s.add(temp);
                        }
                    }
                }
            }
            //更新s1
            s1 = s;
        }
        return 0;
    }

    private void dfs(List<String> wordList, String endWord, ArrayList<String> list, List<List<String>> res) {
        if (list.size() > minDist) {
            return;
        }
        if (list.get(list.size() - 1).equals(endWord)) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (String word : wordList) {
            //判断这个word和上一个添加到list中的单词的关系，如果符合要求，可以添加。首要条件是这个单词不能是自己本身,并且添加过的不能再重复，不然会死循环
            String s = list.get(list.size() - 1);
            if (!word.equals(s) && vaild(word, s) && !list.contains(word)) {
                list.add(word);
                dfs(wordList, endWord, list, res);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean vaild(String word, String s) {
        // 判断word能否由s替换掉一个字符变换过去
        for (int i = 0; i < s.length(); i++) {
            char[] sc = s.toCharArray();
            for (int j = 0; j < 26; j++) {
                sc[i] = (char) (j + 'a');
                String temp = new String(sc);
                if (word.equals(temp)) {
                    return true;
                }
            }
        }
        return false;
    }*/








    public static void main(String[] args) {
        _126_单词接龙II test = new _126_单词接龙II();
        String beginword = "hit";
        String endword = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> list = Arrays.stream(words).collect(Collectors.toList());

        // String beginword = "hot";
        // String endword = "dog";
        // String[] words = {"hot", "dog"};
        // List<String> list = Arrays.stream(words).collect(Collectors.toList());
        test.findLadders(beginword, endword, list);
    }
}