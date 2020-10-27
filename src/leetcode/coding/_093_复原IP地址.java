package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 思考：ip地址一共为四段，每一段内的数字范围在0-255，如果是0开头的，只能为单个的0
 *      一开始我是像上面那样思考的，但其实可以看作是划分字符串，把3个点插入到合适的位置去
 */
public class _093_复原IP地址 {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        if (s.length() > 12) {      //有个巨长的uc...这种就不行的，ip地址最长12位
            return res;
        }
        dfs(s, 0, new ArrayList<String>(),  res);
        return res;
    }

    private void dfs(String s, int startIndex, ArrayList<String> list, List<String> res) {
        if (list.size() == 4 && startIndex == s.length()) {
            res.add(String.join(".", list));
            return;
        }
        //每段最大长度为3
        for (int i = 1; i <= 3; i++) {
            if (startIndex + i > s.length()) {
                break;
            }
            String str = s.substring(startIndex, startIndex + i);
            //str不能以0为开头（长度不为1） 并且 str不能超过255
            if ((str.startsWith("0") && str.length() != 1) || (str.length() == 3 && Integer.valueOf(str) > 255)) {
                continue;
            }
            list.add(str);
            dfs(s, startIndex + i, list, res);
            list.remove(list.size() - 1);
        }
    }

}