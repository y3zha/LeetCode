package leetcode.coding;

public class _186_翻转字符串里的单词II {

    // 两次翻转即可。第一次全局翻转、第二次对每个单词进行翻转。这两次翻转顺序允许颠倒。
    public void reverseWords(char[] s) {
        int n = s.length;
        // 全局翻转
        reverse(s, 0, n-1);
        // 局部翻转
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }

        // 翻转最后一个单词
        reverse(s,start,n-1);
    }

    private void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}