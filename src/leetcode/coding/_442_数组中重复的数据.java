package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _442_数组中重复的数据 {

    // 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）,其中有些元素出现两次而其他元素出现一次。找到所有出现两次的元素。
    // 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？

    // 反正是我想不到的思路：
    // 将出现过的数字作为数组的index（访问元素时需要减一）如果出现一次的，将该 index 下的数取相反数，记录此时的状态
    // 如果值为index的数字再出现一次（此时index索引的值为负数），那么这个数字就出现了两次。
    // 比如 数组 [2,2,1] , 第一次更新后 index = 2 索引的元素取相反数 [2,-2,1],
    // 第二次更新 index = 2 , 此时数组元素已为负，所以 2 就是其中的一个结果....

    //input [4,3,2,7,8,2,3,1] 对于第一个数字4 的出现次数，用index 3 的数字7的正负值表示
    // 4出现第一次，7变-7，出现第二次-7变7并加入result


    // 相似题目448题
    public static List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = Math.abs(nums[i]);
            if (nums[a - 1] < 0) {
                res.add(a);
            } else {
                nums[a - 1] = -nums[a - 1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        findDuplicates(nums);
    }
}