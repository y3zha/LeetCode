package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _022_括号生成 {

    /**
     * 这个题实际上是在考树的生成，括号和树就是一体两面，可以看下1111题
     * 产生左分支的时候，只看当前是否还有左括号可以使用；
     * 产生右分支的时候，还受到左分支的限制，右边剩余可以使用的括号数量一定得在严格大于左边剩余的数量的时候，才可以产生分支；
     * 在左边和右边剩余的括号数都等于 0 的时候结算。
     * 初始时左右括号都是n对
     */

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        dfs(n, n, "", res);
        return res;
    }

    private void dfs(int left, int right, String temp, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(temp);
            return;
        }
        // 如果左边剩余更多，那肯定不行了
        if (left > right) {
            return;
        }
        if (left > 0) {
            dfs(left - 1, right, temp + "(", res);
        }
        if (right > 0) {
            dfs(left, right - 1, temp + ")", res);  //不能两处都写temp+=
        }
    }
}