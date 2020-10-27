package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 埃筛法(nju练习做过，sea2)
 * 首先将2到n范围内的整数写下来，其中2是最小的素数。将表中所有的2的倍数划去，表中剩下的最小的数字就是3，他不能被更小的数整除，所以3是素数。
 * 再将表中所有的3的倍数划去……以此类推，如果表中剩余的最小的数是m，那么m就是素数。然后将表中所有m的倍数划去，像这样反复操作，就能依次枚举n以内的素数。
 * 表格如下（以n为20为例）：20以内的素数有2,3,5,7,11,13,
 */
public class _204_计数质数 {

    //统计所有小于非负整数 n 的质数的数量。是小于，n本身不算在里面
    public static int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        int[] a = new int[n];
        for (int i = 2; i < n; i++) {
            if (a[i] == 0) {
                list.add(i);
                for (int j = 2 * i; j < n; j += i) {
                    a[j] = 1;
                }
            }
        }
        return list.size();
    }

    public static void main(String[] args) {
        countPrimes(13);
    }
}