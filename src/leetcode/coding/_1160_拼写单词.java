package leetcode.coding;

public class _1160_拼写单词 {

    public int countCharacters(String[] words, String chars) {
        int[] temp = new int[26];
        for (int i = 0; i < chars.length(); i++) {
            temp[chars.charAt(i) - 'a']++;
        }
        int res = 0;
        for (String word : words) {
            int[] t = temp.clone();
            int cnt = 0;
            for (int i = 0; i < word.length(); i++) {
                if (t[word.charAt(i) - 'a']-- >0) cnt++;
                else break;
            }
            if (cnt == word.length()) res += word.length();
        }
        return res;
    }
}