package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * N皇后问题，目的要使得皇后之间不能相互攻击。皇后能攻击同一行、同一列、以及两条斜线。输出格式是打印棋盘
 *
 * 思路，这个题是一个全排列的问题，用dfs解决。
 *
 * 输入: 4
 * 输出: [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 这两种解法，实际上就是全排列得出的，比如解法1，可以认为结果是[2,4,1,3]，就是每个皇后在这一行中的第几列
 * 那么我们可以dfs，得到每个皇后在这一行的第几列，把这个位置存到list中，根据这个list，再把棋盘画出来，放进res集合中
 *
 */
public class _051_N皇后 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        dfs(n, new ArrayList<Integer>(), res);
        return res;
    }

    /**
     * dfs
     * @param n
     * @param list  //list中放每个皇后在这一行的第几列
     * @param res   //总的结果集
     */
    private void dfs(int n, ArrayList<Integer> list, List<List<String>> res) {
        //如果这一行的长度为n，那就添加进res
        if (list.size() == n) {
            res.add(drawChessboard(list));
            return;
        }

        //遍历n行
        for (int i = 0; i < n; i++) {
            //判断皇后位置是否合法,把当前list传进去
            if (!isValid(list, i)) {
                continue;
            }
            list.add(i);
            dfs(n, list, res);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 判断位置是否合法
     *
     * @param list
     * @param col  //在这一行中的第几列
     * @return
     */
    private boolean isValid(ArrayList<Integer> list, int col) {
        // 遍历所有之前放进来的
        int n = list.size();    //放进来几行了
        for (int i = 0; i < n; i++) {
            //新来的肯定在新的一行，但是要不同列
            if (list.get(i) == col) {
                return false;
            }
            //判断左上到右下这根斜对角线上(y=-x)有没有冲突.这一条线上的所有坐标，它的横纵坐标之差是一样的
            //如果其他点的横坐标（i）- 纵坐标(list.get(i)) == 当前点的横坐标n - 当前点的纵坐标(col)，那就返回false
            if (i - list.get(i) == n - col) {       //这边是n，不是n+1，因为放进来n行，但最后一个位置是n-1
                return false;
            }

            //判断右上到到左下这条斜对角线（y=x）有没有冲突，这一条线程所有的坐标，它的横纵坐标之和都是一样的
            //如果其他点的横坐标（i）+ 纵坐标(list.get(i)) == 当前点的横坐标n + 当前点的纵坐标(col)，那就返回false
            if (i + list.get(i) == n + col) {
                return false;
            }
        }
        return true;
    }

    //画出chessboard放进res，list中存放的是每个皇后在这一行的第几列
    private List<String> drawChessboard(ArrayList<Integer> list) {
        List<String> res = new ArrayList<>();
        int n = list.size();
        for (int i = 0; i < n; i++) {
            String temp = "";
            Integer col = list.get(i);      //皇后在这一行中的第 col 列
            //画出这一行
            for (int j = 0; j < n; j++) {
                if (j == col) {
                    temp += "Q";
                } else {
                    temp += ".";
                }
            }
            res.add(temp);
        }
        return res;
    }

}