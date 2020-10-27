package leetcode.coding;



import java.util.*;

/**
 * 单词接龙，目标是找到从 beginWord 到 endWord 的最短转换序列的长度
 * 思路：看见最短路径就要想到bfs，把每个单词可以看作一个节点，这个单词能转换到另一个单词，说明两者之间存在一条边，所有单词能够构建成一个图，这就是求图中的最短路径
 *
 * 难点在于，如何找到这个单词能变换成什么单词
 *      方法一,遍历N个单词，每个单词长为L，一个一个字符去比较，只有一个地方不一样的，那就是能变换的
 *              单个单词，时间复杂度 N*L，N个单词，时间复杂度为O(N * N * L)
 *      方法二,对于一个单词，长度为L，每个字符有25种变换方式，变换后查看在集合中有没有就可以，时间复杂度O(L * 25 * L)，O(L)的查询时间
 *
 * 在leetcode中，如果endword不在wordlist中，则无法进行转换 return 0
 * 然后开始单词是不在wordlist中的
 * 返回的是转换序列的长度，就是从beginword稻草endword的一个序列的长度。
 */
public class _127_单词接龙 {


    /**
     * 这个题用双向bfs才能过，就是beginWord找endWord，endWord找beginWord
     * 从两端搜索，需要两个队列来保存各自的搜索信息，记为队列A、队列B
     * 每次选取两个队列中含有较少元素的队列进行出队操作，如果两者含有元素一样多，选取A队列
     * 每次遍历到符合要求的元素，则进行入队操作，如果入队时发现对方的集合中有这个元素，那么就退出。返回此时的层数
     *
     * 因为BFS是层次遍历，也就是金字塔型遍历，越往后，搜索到的节点越多，信息越庞大，导致搜索时间越长。但是结束点又只有一个，所以数据量大就会超时
     * 可以利用这个特点，在起始和结束的word之间都进行搜索，这样可以充分利用信息，不再只有一个结束标志位了
     * 只要任意一个搜索到的节点发现自己的flag变为了3，那么代表begin和word之间是连通的，此时搜索的层次就是最短路径
     *
     */

    //双向bfs写法一，较为繁琐，这是最基本的写法
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Queue<String> queA = new LinkedList<>();
        Set<String> setA = new HashSet<>();
        queA.offer(beginWord);
        setA.add(beginWord);

        Queue<String> queB = new LinkedList<>();
        Set<String> setB = new HashSet<>();
        queB.offer(endWord);
        setB.add(endWord);

        Queue<String> que = null;
        Set<String> curSet = null;
        Set<String> tempSet = null;

        int res = 1;
        while (!queA.isEmpty() && !queB.isEmpty()) {
            if (queA.size() <= queB.size()) {
                que = queA;
                curSet = setA;
                tempSet = setB;
            } else {
                que = queB;
                curSet = setB;
                tempSet = setA;
            }
            res++;
            int size = que.size();
            for (int i = 0; i < size; i++) {
                String word = que.poll();
                ArrayList<String> adjs = getAdj(word, wordList);
                //遍历adj words
                for (String w : adjs) {
                    if (tempSet.contains(w)) {
                        return res;
                    }
                    if (!curSet.contains(w)) {
                        curSet.add(w);
                        que.offer(w);
                    }
                }
            }
        }
        return 0;
    }


    //双向bfs，powcai大佬的写法，实际上不用开两个队列，只需要两个set
    public static int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Set<String> setA = new HashSet<>();
        setA.add(beginWord);
        Set<String> setB = new HashSet<>();
        setB.add(endWord);
        int n = beginWord.length();
        int step = 1;
        while (!setA.isEmpty() && !setB.isEmpty()) {
            step++;
            if (setA.size() > setB.size()) {
                //交换两个set,始终保证setA是size较小的，如果相等还是选setA
                Set<String> temp = setA;
                setA = setB;
                setB = temp;
            }
            Set<String> set = new HashSet<>();
            for (String word : setA) {
                for (int i = 0; i < n; i++) {
                    //必须放在里面，每次重新根据word生成，因为之前的已经被改过了
                    char[] sc = word.toCharArray();
                    for (char j = 'a'; j <= 'z'; j++) {
                        sc[i] = j;
                        String str = new String(sc);
                        if (setB.contains(str)) {
                            return step;
                        }
                        if (!wordList.contains(str)) {
                            continue;
                        }
                        wordList.remove(str);
                        set.add(str);
                    }
                }
            }
            setA = set;
        }
        return 0;
    }





    //单向bfs太慢了，只过了30个uc
    public static int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        //如果结果集中没有endWord，直接返回0
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        HashSet<String> set = new HashSet<>();          //用来记录结果集中有没有endWord
        queue.offer(beginWord);
        set.add(beginWord);

        int res = 1;        //从1开始

        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                //得到这个单词相邻边上的节点
                ArrayList<String> list = getAdj(word, wordList);
                //遍历
                for (int j = 0; j < list.size(); j++) {
                    String adjWord = list.get(j);
                    if (set.contains(adjWord)) {
                        continue;
                    } else if (adjWord.equals(endWord)) {
                        return res;     // 如果和endWord直接返回res
                    } else {
                        set.add(adjWord);
                        queue.offer(adjWord);
                    }
                }
            }
        }
        return 0;
    }

    private static  ArrayList<String> getAdj(String word, List<String> wordList) {
        ArrayList<String> list = new ArrayList<>();
        //遍历word的每个字符并作出变换
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < 26; j++) {
                //不能在这之间换，必须分出去换
                if (word.charAt(i) != ('a' + j)) {
                    String str = getReplaced(word, i, j);
                    if (wordList.contains(str)) {
                        list.add(str);
                    }
                }
            }
        }
        return list;
    }

    private static String getReplaced(String word, int i, int j) {
        char[] chs = word.toCharArray();
        chs[i] = (char) ('a' + j);
        return new String(chs);
    }

    public static void main(String[] args) {
        String[] strs = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(strs));
        int i = ladderLength2("hit", "cog", list);
        System.out.println(i);
    }
}