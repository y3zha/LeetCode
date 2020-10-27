package leetcode.coding;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class _854_相似度为K的字符串 {


    /*
    这个题的意思就是：最少交换多少次可以将字符串A变为B

    BFS思路：
    最少次数最短路径可以采用bfs的方式，A就是起始状态，B就是最终状态，进行字符交换，只要还没变成B就一直交换
    直接去BFS应该会超时，首先要确保 A 一定能转变成 B，也就是每个字符对应的字符个数是相等的，其次什么时候需要交换，什么时候不需要交换
    如果 A[i] = B[i]，这肯定不需要交换
    我们要找到两个不等的字符位置去交换，即 A[i]和A[j] ，他们满足A[i] != B[i] 且 A[j] != B[j]
    最好的情况是A[i] = B[j]，比如 ab 和 ba，所以这就是我们要的最佳情况

    每次加进 queue 的时候都用 set 判断下之前有没有加过
     */

    public int kSimilarity(String A, String B) {
        int[] cnt = new int[26];
        int n = A.length();
        for (int i = 0; i < n; i++) {
            cnt[A.charAt(i) - 'a']++;
            cnt[B.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) {
                // 一定不能转换的情况
                return -1;
            }
        }
        Queue<String> queue = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        queue.offer(A);
        set.add(A);
        int res = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                String cur = queue.poll();
                if (cur.equals(B)) {
                    return res;
                }
                // 找到两个位置 i, j
                int i = 0;
                while (i < n && cur.charAt(i) == B.charAt(i)) {
                    i++;
                }
                // 保存下换之前的cur
                String thisCur = cur;
                for (int j = i + 1; j < n; j++) {
                    if (cur.charAt(j) == B.charAt(j) || cur.charAt(j) != B.charAt(i)) {
                        continue;
                    }
                    // 找到最佳交换情况，交换
                    String newStr = swap(cur, i, j);
                    if (!set.contains(newStr)) {
                        set.add(newStr);
                        queue.offer(newStr);
                    }
                    cur = thisCur;
                }
            }
            res++;
        }
        return res;
    }

    private String swap(String cur, int i, int j) {
        char[] sc = cur.toCharArray();
        char temp = sc[i];
        sc[i] = sc[j];
        sc[j] = temp;
        return new String(sc);
    }
}