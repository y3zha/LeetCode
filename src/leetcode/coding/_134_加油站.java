package leetcode.coding;

/**
 * 到达每个汽车加油站都会获得gas[i]升汽油
 * 从当前位置前往下一个车展都会消耗cost[i]升汽油
 *
 * 问：能不能环绕一周？能的话返回起点编号，不能则返回-1。（假设题目能的话有唯一解）
 *
 * https://leetcode-cn.com/problems/gas-station/solution/shi-yong-tu-de-si-xiang-fen-xi-gai-wen-ti-by-cyayc/
 * 分析：要环绕一圈，到达终点及其以前的任意点，油量的剩余值都需要 >= 0
 *
 * 以下面这个例子为例
 *
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * 在i为0时，剩余油量为 1 - 3 = -2
 * 在i为1时，剩余油量为 -2 + 2 - 4 = -4
 * 在i为2时，剩余油量为 -4 + 3 - 5 = -6
 * 在i为3时，剩余油量为 -6 + 4 - 1 = -3
 * 在i为4时，剩余油量为 -3 + 5 - 2 = 0
 *
 * 这个是总剩余油量的值，需要满足它的任意部分都 >= 0，所以要找到这个最低点，这个最低点作为出发点
 *
 * 如果最后spare到达0了，那么说明是可以走一圈的，如果小于0，说明不能走一圈。
 *
 */
public class _134_加油站 {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int spare = 0;  //剩余总油量是多少
        int minSpare = Integer.MAX_VALUE;   //记录最小油量
        int index = 0;  //最小油量下标
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            spare += gas[i] - cost[i];
            if (spare < minSpare) {
                minSpare = spare;
                index = i;
            }
        }

        return spare < 0 ? -1 : (index + 1) % n;
    }
}