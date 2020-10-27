package leetcode.coding;

/**
 * 做这个题之前先做一下438题，利用滑动窗口求anagrams
 *
 * 这个题也是滑动窗口。。。都可以直接拿438题的代码过
 */
public class _567_字符串的排列 {

    //如果第二次做看不懂，直接看438题注释
    public boolean checkInclusion(String s1, String s2) {
        int[] arr = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            arr[s1.charAt(i) - 'a']++;
        }

        int i = 0, j = 0, count = 0, matched = 0;
        int k = s1.length();    //窗口大小
        for (i = 0; i < s2.length() - k + 1; i++) {
            while (j < s2.length() && count < k) {
                arr[s2.charAt(j) - 'a']--;
                if (arr[s2.charAt(j) - 'a'] >= 0) {
                    matched++;
                }
                j++;
                count++;
            }
            if (count == k) {
                if (matched == k) {
                    return true;
                }
            }
            arr[s2.charAt(i) - 'a']++;
            if (arr[s2.charAt(i) - 'a'] > 0) {
                matched--;
            }
            count--;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}