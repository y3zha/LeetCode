package leetcode.coding;

import java.util.*;

/**
 * 线段树 树状数组
 */
public class _315_计算右侧小于当前元素的个数 {

    public static void main(String[] args) {
        int[] a = {5, 2, 6, 1};
        countSmaller(a);
    }

    //暴力n^2 超时
    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int cnt = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < num) {
                    cnt++;
                }
            }
            list.add(cnt);
        }
        return list;
    }

    //逆序对，是求i < j  a[i] > a[j] 的个数
    //这个题是求在当前元素右侧 小于当前元素的 个数
    //那么如果用归并思想，划分为两部分，两部分各自排好序，那么就应该在归并的时候
    //前一个数组选出某个元素的时候，和后面的元素去比对下
    //不过题目中要求我们要具体计算到元素级别。“归并排序” 完成以后，原始数组的位置就已经变化了，因此如何定位元素是关键。


    /**
     * 树状数组
     * <p>
     * 首先离散化：相对于数组中的值，我们其实更关心的是数组中的元素的“相对排名”，为了避免开辟多余的“树状数组”空间
     * 我们首先对数组元素做预处理，这一步叫“离散化”；
     * 因为我们关心右侧的元素，最后一个元素的右侧的元素个数为 0， 倒数第二元素的右侧的元素个数为 1，依次增大，从右边向左边看便于我们计算结果。
     * 因此我们从右边向左边看。
     * https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/solution/shu-zhuang-shu-zu-by-liweiwei1419/
     */

    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int n = nums.length;
        if (n == 0) return list;
        //离散化处理
        //使用二分搜索树更方便排序
        Set<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        //排名表
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (Integer num : set) {
            map.put(num, rank);
            rank++;
        }
        FenwickTree fenwickTree = new FenwickTree(set.size() + 1);
        //从后向前填表,这个是关键，而不是从前向后（面试题51是从前往后）
        for (int i = n - 1; i >= 0; i--) {
            // 1、查询排名
            rank = map.get(nums[i]);
            // 2、在排名位置插入
            fenwickTree.add(rank, 1);
            // 3、查询一下小于等于“当前排名 - 1”的元素有多少
            list.add(fenwickTree.query(rank - 1));
        }
        Collections.reverse(list);
        return list;
    }


    class FenwickTree{
        int n;
        int[] c;

        public FenwickTree(int n) {
            this.n = n;
            c = new int[n + 1];
        }

        public void add(int i, int val) {
            while (i <= n) {
                c[i] += val;
                i += lowbit(i);
            }
        }

        public int query(int i) {
            int cnt = 0;
            while (i > 0) {
                cnt += c[i];
                i -= lowbit(i);
            }
            return cnt;
        }

        private int lowbit(int x) {
            return x & (-x);
        }
    }

}