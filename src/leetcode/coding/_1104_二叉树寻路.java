package leetcode.coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _1104_二叉树寻路 {

    /*
    因为以1为根节点层次编号的满二叉树可以对应到位的表示，所以用位运算的思路即可。
    因为每层的顺序在变，所以每次需要对首位外的其它位取反。 !!!!
    举例14=1110b，
    先将14右移，变为111b，然后对除第一位外所有位取反变为100b，即它的根节点4，
    同理100b，右移变为10b，对除第一位外所有位取反变为11b，即它的根节点3
    一直到1结束。
     */
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> list = new ArrayList<>();
        String s = Integer.toBinaryString(label);
        int n = s.length();
        while (label != 1) {
            list.add(label);
            label >>= 1;
            n--;
            // 对除第一位的其他位取反,异或就行，相同为0，不同为1，和1异或，如果本来是1，1^1变为0，如果本来为0，就变味1
            label = label ^ ((1 << n - 1) - 1);
        }
        list.add(1);
        Collections.reverse(list);
        return list;
    }

}