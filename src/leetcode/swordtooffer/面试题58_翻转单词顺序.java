package leetcode.swordtooffer;

public class 面试题58_翻转单词顺序 {

    public static String reverseWords(String s) {
        String trim = s.trim();
        String[] ss = trim.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = ss.length - 1; i >= 0; i--) {
            //如果两个单词中有多余的空格，split出来是空串""，所以要判断一下，如果是空串就跳过
            if (ss[i].length() == 0) {
                continue;
            }
            sb.append(ss[i].trim()).append(" ");
        }
        return sb.toString().trim();
    }
}