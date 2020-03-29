package leetcode.coding;

import java.util.ArrayList;

/**
 * 这里只要返回次数即可,少了画棋盘的过程
 */
public class _052_N皇后II {

    static int count;

    public static int totalNQueens(int n) {
        count = 0;
        if (n == 0) {
            return 0;
        }
        dfs(n, new ArrayList<Integer>());
        return count;
    }

    private static void dfs(int n, ArrayList<Integer> list) {
        if (list.size() == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!isValid(list, i)) {
                continue;
            }
            list.add(i);
            dfs(n, list);
            list.remove(list.size() - 1);
        }
    }

    //list存放的是皇后在这一行中的第几列
    private static boolean isValid(ArrayList<Integer> list, int col) {
        int n = list.size();
        for (int i = 0; i < n; i++) {
            //如果列相同，那就冲突了
            if (list.get(i) == col) {
                return false;
            }
            //如果在y=-x这条线上也是冲突的，这一条线上的横坐标与纵坐标之差都是一样的
            if (i - list.get(i) == n - col) {
                return false;
            }
            //如果在y=x这条线上也是冲突的，这一条线上的横坐标与纵坐标之和都是一样的
            if (i + list.get(i) == n + col) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        totalNQueens(1);
    }
}