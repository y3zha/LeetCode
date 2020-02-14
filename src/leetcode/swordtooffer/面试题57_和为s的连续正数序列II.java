package leetcode.swordtooffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class 面试题57_和为s的连续正数序列II {

    /**
     * 因为结果是一串连续的数字，所以可以利用滑动窗口，也可以说是快慢指针，窗口就是由两个指针之间的数据组成。
     * 当结果大于指定值时，我们就用结果减去左边的数字，然后左指针加1。
     * 当结果小于指定值时，我们就用结果加上右边的数字。
     * 当结果等于指定值时，我们就将左右两个指针组成的数字组成数组。
     */

    public static int[][] findContinuousSequence(int target) {
        List<int[]> res = new LinkedList<>();
        int i = 1;
        int j = 2;
        int sum = i + j;
        while (i < j) {
            if (sum < target) {
                if (j >= target) {
                    break;
                }
                j++;
                sum += j;
            } else if (sum > target) {
                sum -= i;
                i++;
            } else {    //相等的情况
                res.add(IntStream.rangeClosed(i, j).toArray()); //用IntStream生成数组看起来清爽多了
                sum -= i;
                i++;
            }
        }
        return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        findContinuousSequence(9);
    }

}