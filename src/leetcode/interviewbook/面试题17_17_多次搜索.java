package leetcode.interviewbook;

import java.util.*;

public class 面试题17_17_多次搜索 {


    /**
     * Trie
     * 每个树节点保存它的起始位置有多少种可能
     */

    /**
     * 方法一，暴力，index of
     */
    public static int[][] multiSearch(String big, String[] smalls) {
        int n = big.length();
        List<int[]> res = new ArrayList<>();
        for (String s : smalls) {
            int idx = 0;
            LinkedList<Integer> list = new LinkedList<>();
            //碰到空字符串的情况
            if (s.length() == 0) {
                res.add(new int[]{});
                continue;
            }
            while (idx < n) {
                int pos = big.indexOf(s, idx);
                if (pos != -1) {
                    list.add(pos);
                    idx = pos + 1;  //直接定位到pos的下一个，不能定位到pos，会死循环
                } else {
                    break;
                }
            }
            res.add(list.stream().mapToInt(Integer::valueOf).toArray());
        }
        return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        String s = "mississippi";
        String[] smalls = {"is", "ppi", "hi", "sis", "i", "ssippi"};
        multiSearch(s, smalls);
    }






    /**
     * ac自动机 https://my.oschina.net/u/3132973/blog/812141
     * http://www.cppblog.com/mythit/archive/2009/04/21/80633.html
     * 1、构造前缀树
     * 2、设置每个节点的失配跳转并收集每个节点的所有匹配模式串
     * 3、对目标字符串进行搜索匹配
     */

    class Node {
        int tail;
        Node fail;  //失败指针
        Node[] children;   //Tire每个节点的个子节点

        public Node() {
            fail = null;
            tail = 0;
            children = new Node[26];
        }
    }


    public class ACAutomation {
        private Node root;
        private Queue<Node> queue;
        private int count;
        private String[] words;
        private Map<String, List<Integer>> map;

        public ACAutomation(String[] words) {
            root = new Node();
            queue = new LinkedList<Node>();
            count = 0;
            this.words = words;
            map = new TreeMap<>();
            for (String word : words) {
                this.insert(word);
                map.put(word, new ArrayList<>());
            }
            this.acAutomation();
        }

        //首先构建trie
        public void insert(String word) {
            if (word.isEmpty()) {
                return;
            }
            Node cur = root;
            this.count++;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new Node();
                }
                cur = cur.children[index];
            }
            cur.tail = this.count;
        }

        /**
         * 构造失败指针
         * 过程概括起来就一句话：
         * 设这个节点上的字母为C，沿着他父亲的失败指针走，直到走到一个节点，他的儿子中也有字母为C的节点。
         * 然后把当前节点的失败指针指向那个字母也为C的儿子。如果一直走到了root都没找到，那就把失败指针指向root。
         * （https://blog.csdn.net/jw72jw/article/details/6843700）
         * 具体操作起来只需要：
         * 先把root加入队列(root的失败指针指向自己或者NULL)，这以后我们每处理一个点，就把它的所有儿子加入队列，队列为空。
         */
        public void acAutomation() {
            root.fail = null;
            queue.add(root);
            while (!queue.isEmpty()) {
                Node poll = queue.poll();   //取出这个节点
                Node p = null;  //这个节点的失败指针
                //枚举这个节点的儿子
                for (int i = 0; i < 26; i++) {
                    //如果有儿子
                    if (poll.children[i] != null) {
                        //如果poll出来的是根，它儿子的fail指针就是指向根
                        if (poll == root) {
                            poll.children[i].fail = root;
                        } else {    //否则
                            p = poll.fail;  //取到这个节点的失败指针
                            while (p != null) {     //只要这个失败指针不为null就一直找
                                if (p.children[i] != null) {    //如果失败指针指向的节点有这个儿子 （i就代表了这个儿子）
                                    poll.children[i].fail = p.children[i];
                                    break;  //保证总是找到后缀最长的
                                }
                                p = p.fail; //否则没这个儿子只能默默往上跳
                            }
                            //没有找到，只能指向根了
                            if (p == null) {
                                poll.children[i].fail = root;
                            }
                        }
                        queue.add(poll.children[i]);
                    }
                }
            }
        }

        /**
         * 自动机上查询
         * 只需对目标串从头到尾线性扫描，且没有回溯。搜索之前先记录树的当前节点node，初始时，树的当前节点node为根节点Root。
         * 从目标串的第一个字符开始，和Root的孩子节点进行匹配，如果不匹配，则目标字符串往后挪一个字符
         * 继续在Root的孩子节点中查找匹配。如果找到匹配的孩子，则目标字符串往后挪一个字符，node变为匹配上的孩子节点。
         * 在接下来的匹配过程中，如果失配将跳转到node节点的fail值处继续进行匹配。
         * 在树上每次往孩子节点方向走一步都要检查该孩子节点的匹配模式串信息，如果有匹配的模式串信息，则应记录找到了哪些能够匹配的模式串。
         */
        // public int[] query(String s,String small) {
        //     List<Integer> list = new ArrayList<>();
        //     int n = small.length();
        //     Node p = root;
        //     char[] sc = s.toCharArray();
        //     for (int i = 0; i < sc.length;) {
        //         int index = sc[i] - 'a';
        //         //如果当前状态不能找到后继节点 ，只能去他爸爸那找了
        //         while (p.children[index] == null && p != root) {
        //             p = p.fail;
        //         }
        //         //如果当前状态能找到后继节点，继续走它
        //         p = p.children[index];
        //         p = (p == null) ? root : p;
        //         Node temp = p;
        //         while (temp != root && temp.count != -1) {
        //             list.add(i);
        //             temp.count = -1;
        //             temp = temp.fail;
        //
        //             i++;
        //         }
        //     }
        //     return list.stream().mapToInt(Integer::valueOf).toArray();
        // }
        public Map<String,List<Integer>> query(String s) {
            char[] sc = s.toCharArray();
            Node cur = root;
            Map<String, ArrayList<Integer>> map = new HashMap<>();
            for (int i = 0; i < sc.length; i++) {
                int index = sc[i] - 'a';
                while (cur.children[index] == null && cur != root) {
                    cur = cur.fail;
                }
                int startIndex = 0;
                if (cur.children[index] != null && cur == root) {
                    startIndex = i;
                }
                if (cur.children[index] != null) {
                    cur = cur.children[index];
                } else {
                    startIndex = i;
                    cur = root;
                }
                Node temp = cur;
                while (temp != root) {
                    if (temp.tail != 0) {
                        String word = this.words[temp.tail - 1];
                        map.get(word).add(i - word.length() + 1);
                    }
                    temp = temp.fail;
                }
            }
            return this.map;
        }
    }
    public int[][] multiSearch2(String big, String[] smalls) {
        ACAutomation acAutomation = new ACAutomation(smalls);
        Map<String, List<Integer>> map = acAutomation.query(big);
        List<List<Integer>> res = new ArrayList<>();
        for (String s : map.keySet()) {
            res.add(map.get(s));
        }
        System.out.println(res);
        return null;
    }


}