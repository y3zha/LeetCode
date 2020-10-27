package leetcode.coding;

public class _1079_活字印刷 {

    static int res = 0;
    public static int numTilePossibilities(String tiles) {
        int n = tiles.length();
        int[] a = new int[26];
        for (int i = 0; i < n; i++) {
            int idx = tiles.charAt(i) - 'A';
            a[idx]++;
        }
        dfs(a);
        return res;
    }

    private static void dfs(int[] a) {
        for (int i = 0; i < 26; i++) {
            if (a[i] == 0) {
                continue;
            }
            res++;  // 统计
            a[i]--;
            dfs(a);
            a[i]++;
        }
    }

    public static void main(String[] args) {
        numTilePossibilities("AAB");
    }
}