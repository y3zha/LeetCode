package leetcode.coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _247_中心对称数II {

    // 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
    //
    // 找到所有长度为 n 的中心对称数。

    // 一个长度为n的字符串，可以先把中间n-2位字符串的所有构造方式列出。然后两边拼接上 11 69 96 88 即可。
    // 需要注意的是，要处理一下最外层数据不能取0的限制。

    // https://juejin.im/post/5bddcee3e51d4520b6639663
    public List<String> findStrobogrammatic(int n) {
        if (n == 1) {
            return new ArrayList<String>() {{
                add("0");
                add("1");
                add("8");
            }};
        }
        if (n == 2) {
            return new ArrayList<String>() {{
                add("11");
                add("69");
                add("96");
                add("88");
            }};
        }

        List<String> list = helper(n - 2);
        List<String> res = new ArrayList<>();
        for (String s : list) {
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("9" + s + "6");
            res.add("8" + s + "8");
        }
        Collections.sort(res);
        return res;
    }

    private List<String> helper(int n) {
        if (n == 1) {
            return new ArrayList<String>() {{
                add("0");
                add("1");
                add("8");
            }};
        }
        if (n == 2) {
            return new ArrayList<String>() {{
                add("11");
                add("69");
                add("96");
                add("88");
                // 可以加0
                add("00");
            }};
        }
        List<String> res = new ArrayList<>();
        List<String> list = helper(n - 2);
        for (String s : list) {
            // 内部可以加0
            res.add("0" + s + "0");
            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("9" + s + "6");
            res.add("8" + s + "8");
        }
        return res;
    }
}