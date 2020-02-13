package leetcode.swordtooffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class 面试题6_从尾到头打印链表 {

    /**
     * 方法一：顺序遍历，reverse输出
     * 方法二：借助stack
     *
     * 懒得写了
     */

    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Collections.reverse(list);
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

}