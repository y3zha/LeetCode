package leetcode.coding;

public class _744_寻找比目标字母大的最小字母 {

    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        if (target < letters[0] || target >= letters[n - 1]) {
            return letters[0];
        }
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return letters[left];
    }
}