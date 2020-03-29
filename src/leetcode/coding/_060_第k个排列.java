package leetcode.coding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 方法一：先求出所有排列，get到第k个
 * 方法二：将 n! 种排列分为：n 组，每组有 (n - 1)!个排列，根据k值可以确定是第几组的第几个排列
 *        选取该排列的第1个数字，然后递归从剩余的数字里面选取下一个数字，直到n=1为止。
 * 方法三：康托展开式
 * 全排列和组合数不一样的地方，全排列需要visited数组，不需要stertIndex，因为它要回过头去找所有可能的排列
 */
public class _060_第k个排列 {

    //方法一：会超时
    public static String getPermutation(int n, int k) {
        List<String> list = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        dfs(n,  "", list, visited);
        System.out.println(list);
        return list.get(k - 1);
    }

    private static void dfs(int n, String temp, List<String> list, boolean[] visited) {
        if (temp.length() == n) {
            list.add(temp);
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp += i;
                dfs(n, temp, list, visited);
                temp = temp.substring(0, temp.length() - 1);
                visited[i] = false;
            }
        }
    }

    //方法二
    //https://leetcode-cn.com/problems/permutation-sequence/solution/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
    public static String getPermutation2(int n, int k) {
        //首先计算出所有的阶乘 0~ n-1的
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        //使用链表
        List<Integer> list = new LinkedList<>();
        //先把一开始的数组都添加进去
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        StringBuilder sb = new StringBuilder();
        k = k - 1;
        for (int i = n - 1; i >= 0; i--) {
            //剪枝位置
            int index = k / factorial[i];
            //在list中删去
            sb.append(list.remove(index));
            //减去上一轮剪枝的数量
            k -= index * factorial[i];
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        getPermutation2(4, 9);
    }
}