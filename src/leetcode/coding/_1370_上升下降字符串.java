package leetcode.coding;

public class _1370_上升下降字符串 {

    /*
    给你一个字符串 s ，请你根据下面的算法重新构造字符串：

    1. 从 s 中选出最小的字符，将它接在 结果字符串的后面。
    2. 从 s 剩余字符中选出最小的字符，且该字符比上一个添加的字符大，将它接在结果字符串后面。
    3. 重复步骤 2 ，直到你没法从 s 中选择字符。

    4. 从 s 中选出最大的字符，将它接在结果字符串的后面。
    5. 从 s 剩余字符中选出最大的字符，且该字符比上一个添加的字符小，将它接在结果字符串后面。
    6. 重复步骤 5 ，直到你没法从 s 中选择字符。

    7. 重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。
    在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。

    请你返回将 s 中字符重新排序后的 结果字符串 。


    乍一看，挺烦的，理性分析下：
    记录每个字符的出现次数，放在桶里，对于这个桶
    先从小到大扫，再从大到小扫，每次发现一个桶当中计数值不为 0 的时候，就把这个桶对应的字母添加到结果字符串的最后方，然后对计数值减一
    */

    public String sortString(String s) {
        int n = s.length();
        int[] map = new int[n];
        for (int i = 0; i < n; i++) {
            map[s.charAt(i) - 'a']++;
        }
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        while (idx < n) {
            for (int i = 0; i < 26; i++) {
                if (map[i] > 0) {
                    sb.append((char) (i + 'a'));
                    map[i]--;
                    idx++;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (map[i] > 0) {
                    sb.append((char) (i + 'a'));
                    map[i]--;
                    idx++;
                }
            }
        }
        return sb.toString();
    }




}