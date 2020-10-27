package leetcode.coding;

import java.util.HashSet;
import java.util.Set;

public class _593_有效的正方形 {

    // 给定二维空间中四点的坐标，返回四点是否可以构造一个正方形。
    // 一个点的坐标（x，y）由一个有两个整数的整数数组表示。

    // 思路： 每两个节点算一下距离，放进set，如果最后set的大小为2，也就是只有边和对角线，那就是能构成正方形的
    // 但是可能有一些恶心的额uc，比如
    //         [0,0]
    //         [1,1]
    //         [0,0]
    //         [0,0]
    // 我们就要看有没有边长为 0 的
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Set<Integer> set = new HashSet<>();
        set.add(helper(p1, p2));
        set.add(helper(p1, p3));
        set.add(helper(p1, p4));
        set.add(helper(p2, p3));
        set.add(helper(p2, p4));
        set.add(helper(p3, p4));

        return !set.contains(0) && set.size() == 2;
    }

    private Integer helper(int[] p1, int[] p2) {
        int x1 = p1[0];
        int y1 = p1[1];
        int x2 = p2[0];
        int y2 = p2[1];
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

}