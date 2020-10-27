package leetcode.coding;

public class _1111_有效括号的嵌套深度 {

    // 就是把这个字符串拆成两部分A和B，使max(depth(A), depth(B)) 的可能取值最小。
    // 那么对于很深的括号，平分一点就好了，比如(((()))) ,A分两对，B分两对，完美
    // 因为是有效括号，开头必然是 '('，如果偶数位置（从1开始算idx）出现了'('，那就肯定有嵌套

    // 就是把这个字符串拆成两部分A和B，使max(depth(A), depth(B)) 的可能取值最小。
    // A这里左括号多了，那就往B那加，A这里左括号少了就往A这加，右括号也是同理的
    public static int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] res = new int[n];
        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                if (a < b) {
                    res[i] = 0;
                    a++;
                } else {
                    res[i] = 1;
                    b++;
                }
            } else {
                if (a > b) {
                    res[i] = 0;
                    a--;
                } else {
                    res[i] = 1;
                    b--;
                }
            }
        }
        return res;
    }

    // 括号序列和树就是一体两面，这属于 well-known fact --SAM Qingchuan Zhang
    // 这题就是要把到最深的叶子拆成两半
    // 这就是奇偶，肯定是 a++,b++,a++,b++
    // 大白话： 这层给a，下层给b，层数奇偶，保证嵌套深度最大值最小，最优也就切一半
    public int[] maxDepthAfterSplit2(String seq) {
        int n = seq.length();
        int[] res = new int[n];
        int level = 0;
        for(int i = 0; i < n; i++){
            if(seq.charAt(i) == '('){
                res[i] = level % 2;
                level++;
            }else{
                level--;
                res[i] = level % 2;
            }
        }
        return res;
    }

    public int[] maxDepthAfterSplit3(String seq) {
        int[] res = new int[seq.length()];
        int cur = 0;
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == '(') {
                res[i] = cur % 2;
                cur++;
            } else {
                cur--;
                res[i] = cur % 2;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _1111_有效括号的嵌套深度 test = new _1111_有效括号的嵌套深度();
        test.maxDepthAfterSplit("(()())");
    }
}