package leetcode.swordtooffer;

import java.util.LinkedList;

public class 面试题62_圆圈中最后剩下的数字 {

    /**
     * 经典的约瑟夫环问题
     * 我们首先能想到的就是模拟这个过程，因为这种题目 很明显是用链表做更适合
     * 我们先把所有的数加进一个链表中，只要链表的长度不为1，那我们就要不断循环，如果不是要剔除的，把前一个添加进链表尾部即可
     * 时间复杂度 O(nm)
     * 看了下数据流 n= 1e5 m=1e6，必定会TLE
     */
    public int lastRemaining_TLE(int n, int m) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        while (list.size() != 1) {
            for (int i = 0; i < m; i++) {
                Integer pre = list.pollFirst();
                if (i != m - 1) {
                    list.add(pre);
                }
            }
        }
        return list.pollFirst();
    }

    /**
     * 方法二，递推公式
     * 见我的题解 https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/yue-se-fu-huan-wen-ti-tu-jie-xiang-xi-si-lu-fen-xi/
     */

    public static int lastRemaining(int n, int m) {
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }

    public static void main(String[] args) {
        lastRemaining(2, 3);
    }
}