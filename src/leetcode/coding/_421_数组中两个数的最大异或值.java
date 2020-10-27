package leetcode.coding;

/**
 * 思考：两个for循环，太暴力了，
 *
 * 我们希望两个数异或出来最大，那就是希望在高位上都是1，而不是0，这是贪心的思想
 * 利用字典树，开两条分叉，存0和存1，把每个数的二进制都存在这棵树中，按照异或的性质（异或相同则为0），那就是1找0，0找1
 */
public class _421_数组中两个数的最大异或值 {

    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        int max = 0;
        for (int num : nums) {
            trie.insert(trie.root, num);
        }
        for (int num : nums) {
            int temp = trie.search(trie.root, num);
            max = Math.max(max, temp);
        }
        return max;
    }
    class TrieNode{
        TrieNode zero;
        TrieNode one;

        public TrieNode() {
            zero = null;
            one = null;
        }
    }

    class Trie{
        TrieNode root = null;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(TrieNode root, int num) {
            TrieNode cur = root;
            //一共31位，从最高位开始
            for (int i = 30; i >= 0; i--) {
                boolean isOne = ((num >> i) & 1) == 1; //判断当前位是否为1
                if (isOne) {
                    if (cur.one == null) {
                        cur.one = new TrieNode();
                    }
                    cur = cur.one;
                } else {
                    if (cur.zero == null) {
                        cur.zero = new TrieNode();
                    }
                    cur = cur.zero;
                }
            }
        }

        public int search(TrieNode root, int num) {
            TrieNode cur = root;
            int res = 0;
            for (int i = 30; i >= 0; i--) {
                boolean isOne = ((num >> i) & 1) == 1;
                //如果是1，开始找0
                if (isOne) {
                    //如果有0，切换到0的分支
                    if (cur.zero != null) {
                        cur = cur.zero;
                        res += (1 << i);
                    } else {        //如果只有1,那就只能用1了,1^1 = 0，不用加
                        cur = cur.one;
                    }
                } else {        //如果是0，开始找1
                    if (cur.one != null) {
                        cur = cur.one;
                        res += (1 << i);
                    } else {
                        cur = cur.zero;
                    }
                }
            }
            return res;
        }
    }



}