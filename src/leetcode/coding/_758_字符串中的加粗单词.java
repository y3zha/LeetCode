package leetcode.coding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class _758_字符串中的加粗单词 {

    // 参考https://www.cnblogs.com/grandyang/p/8531642.html
    public String boldWords(String[] words, String s) {
        int n = s.length();
        boolean[] b = new boolean[n];
        for (String word : words) {
            int m = word.length();
            for (int i = 0; i <= n - m; i++) {
                if (s.charAt(i) == word.charAt(0) && s.substring(i, m).equals(word)) {
                    for (int j = i; j < i + m; j++) {
                        b[j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(b[i]);
        }

        List<String> list = new ArrayList<>();


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (b[i]) {
                //如果是第一个字符，或者其前面的字符不用加粗，我们加上一个左标签<b>
                if (i == 0 || !b[i - 1]) {
                    sb.append("<b>");
                }
                sb.append(s.charAt(i));
                //如果当前是末尾字符，或者后面一个字符不用加粗，则需要加上一个右标签</b>
                if (i == n - 1 || !b[i + 1]) {
                    sb.append("</b>");
                }
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}