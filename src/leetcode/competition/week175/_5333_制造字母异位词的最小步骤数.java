package leetcode.competition.week175;

public class _5333_制造字母异位词的最小步骤数 {

    public int minSteps(String s, String t) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            count += Math.abs(arr[i]);
        }
        return count / 2;
    }
}