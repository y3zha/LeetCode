package leetcode.coding;

public class _972_相等的有理数 {


    /*
    看官方题解，讲的很好,转化成分数做比较是一个好的方法
    这个直接暴力了
     */
    public boolean isRationalEqual(String S, String T) {
        int sPos = -1, tPos = -1;
        StringBuilder s = new StringBuilder();
        StringBuilder t = new StringBuilder(new String());
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                sPos = i;
                break;
            }
            s.append(S.charAt(i));
        }
        for (int i = 0; i < T.length(); i++) {
            if (T.charAt(i) == '(') {
                tPos = i;
                break;
            }
            t.append(T.charAt(i));
        }
        if (sPos > 0) {
            // 拿到括号中的小数部分
            StringBuilder tmp = new StringBuilder();
            for (int i = sPos + 1; i < S.length(); i++) {
                if (S.charAt(i) == ')') {
                    break;
                }
                tmp.append(S.charAt(i));
            }
            // 循环添加
            for (int i = 0; i < 16; i++) {
                s.append(tmp.toString());
            }
        }
        if (tPos > 0) {
            // 拿到括号中的小数部分
            StringBuilder tmp = new StringBuilder();
            for (int i = tPos + 1; i < T.length(); i++) {
                if (T.charAt(i) == ')') {
                    break;
                }
                tmp.append(T.charAt(i));
            }
            // 循环添加
            for (int i = 0; i < 16; i++) {
                t.append(tmp.toString());
            }
        }
        double ss = Double.parseDouble(s.toString());
        double tt = Double.parseDouble(t.toString());
        return Math.abs(ss - tt) <= Math.pow(10, -10);
    }


}