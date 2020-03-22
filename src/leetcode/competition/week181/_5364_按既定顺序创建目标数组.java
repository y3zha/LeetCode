package leetcode.competition.week181;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _5364_按既定顺序创建目标数组 {

    //签到题
    public int[] createTargetArray(int[] nums, int[] index) {
        int n = nums.length;
        //一个个插入还是LinkedList好哦
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(index[i], nums[i]);
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}