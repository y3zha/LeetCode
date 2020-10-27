package leetcode.coding;

/**
 * 滑动窗口
 * 这个题的关键点就是我们如何判断一个字符串改变K个字符，能够变成一个连续串
 * 如果当前字符串中的出现次数最多的字母个数+K大于串长度，那么这个串就是满足条件的
 * 所以需要维护一个数组int[26]来存储当前窗口中各个字母的出现次数
 *
 * 这道题没有限制窗口的大小，他说的是任意位置替换k个字符，其实只要这么做
 *  保存滑动窗口内相同字母出现次数的历史最大值 max，窗口宽度(j - i + 1)大于 max+ k 就做滑动，否则窗口就扩张
 *
 */
public class _424_替换后的最长重复字符 {

    public static int characterReplacement(String s, int k) {
        int[] a = new int[26];
        char[] sc = s.toCharArray();
        int n = sc.length;
        int max = 0;    //当前窗口某个字符出现次数最多，为max
        int res = 1;
        int i = 0, j = 0;
        //窗口右端
        for (j = 0; j < n; j++) {
            int index = sc[j] - 'A';
            a[index]++;
            max = Math.max(max, a[index]);
            //如果窗口大小大于了 窗口中数量最多的字符的数量+k，那左端点要前移了
            //就是说，如果这个窗口大小要比这个最长重复字符串大，也就是这个窗口大小里除了最长重复字符串，还有其他的一些字符
            //那么这个窗口里的所有字符就不满足最长重复字符串，要把左端点往前移动，直到满足窗口内是一个最长重复字符串
            if (max + k < j - i + 1) {
                //需要做滑动
                a[sc[i] - 'A']--;
                i++;
            }
        }
        return n - i;
    }

    public static void main(String[] args) {
        characterReplacement("ABAB", 2);
    }

}