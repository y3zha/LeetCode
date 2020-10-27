package leetcode.coding;

public class _1165_单行键盘 {

    public static int calculateTime(String keyboard, String word) {
        int res = 0;
        int preIdx = 0;
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < keyboard.length(); j++) {
                if (word.charAt(i) == keyboard.charAt(j)) {
                    res += Math.abs(j - preIdx);
                    preIdx = j;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        calculateTime("abcdefghijklmnopqrstuvwxyz", "cba");
    }

}