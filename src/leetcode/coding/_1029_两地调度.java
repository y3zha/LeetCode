package leetcode.coding;

import java.util.Arrays;

public class _1029_两地调度 {

    // 2N个人按照去A地和去B地的费用之差从小到大排序
    // 选出前N个去A地，后N个去B地

    public int twoCitySchedCost(int[][] costs) {
        int n = costs.length;
        Arrays.sort(costs, (o1, o2) -> (o1[0] - o1[1]) - (o2[0] - o2[1]));
        int res = 0, half = n / 2;
        for (int i = 0; i < half; i++) {
            res += costs[i][0] + costs[i + half][1];
        }
        return res;
    }


}