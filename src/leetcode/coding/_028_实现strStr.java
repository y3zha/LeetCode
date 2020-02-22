package leetcode.coding;

import leetcode.test.Test;

/**
 * 1、KMP算法，见柳神的文章https://leetcode-cn.com/problems/implement-strstr/solution/kmp-suan-fa-xiang-jie-by-labuladong/
 * 2、非常推荐看天勤的视频！很好动KMP
 * 3、rabin karp算法
 */
public class _028_实现strStr {

    //labuladong的kmp算法
    public int strStr(String txt, String pat) {
        //如果模式串是空串，直接return 0
        if (pat.length() == 0) {
            return 0;
        }
        //如果模式串不是空串，但是主串是空串，return -1
        if (txt.length() == 0) {
            return -1;
        }
        //通过pat确定有限状态（就是搞一个dp[i][j],i为状态，pat长度有多少，i就有多少。j为某个字符，dp[i][j]=a，代表能转移到状态a）
        int n = txt.length();
        int m = pat.length();
        int[][] dp = new int[m][256];   //一共256个ASCII码
        int X = 0;  //影子状态
        dp[0][pat.charAt(0)] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < 256; j++) {
                //假设下个字符不匹配，此时要回去看影子状态，从而得知跳转到哪个状态
                dp[i][j] = dp[X][j];
            }
            // 只有pat上i的字符匹配，跳转到下个状态
            dp[i][pat.charAt(i)] = i + 1;
            //更新影子状态
            X = dp[X][pat.charAt(i)];
        }

        //构造完dp后，开始search,初始状态为0
        int state = 0;
        for (int i = 0; i < n; i++) {
            state = dp[state][txt.charAt(i)];
            if (state == m) {
                return i - m + 1;
            }
        }
        return -1;
    }







    /**
     * 天勤的KMP，非常好懂
     * 其实就是在模式串中找最长的公共前后缀，我们只需要关注模式串pat即可
     *
     * 最长公共前后缀长度要小于前面那条串本身的长度，前缀从最左端开始 后缀从最右端开始
     *
     * 比如模式串为 ABABAABA 长度为8  我们开next数组时候开n+1 也就是pat长度+1
     * 0-A 前面的最长前后缀没有，为0，所以如果和主串不匹配，把模式串前移一位（相对于主串），也就是让pat的第一个字符与主串下一个字符比较
     *      总结：第一个字符发生不匹配，就与主串的下一个位置比较
     * 1-B 如果第二个字符发生不匹配，但是前面子串长度为1，也是没有公共前后缀的，因为公共前后缀不为其本身，所以公共前后缀长度为0
     *      这里依旧是模式串相对于主串往前移动一位，然后让pat的第一个字符与主串下一个字符比较
     * 2-A 如果此时发生不匹配，此时公共前后缀长度为0，依旧是让pat的第一个字符与主串下一个字符比较
     *
     * 3-B 如果此时发生不匹配，此时公共前后缀长度为1，那么就是让pat的第二个字符（也就是B）与主串的下一个字符比较
     * 4-A 如果此时发生不匹配，此时公共前后缀最长长度为2，那么就是让pat的第三个字符与主串的下一个位置比较
     * ...以此类推
     *
     * 可以发现
     * 发生不匹配的时候，就是让pat当前位置之前的 公共前后缀的最长长度位置 +1 个字符 与主串的下一个位置比较
     * 其实我们得到的next数组就是pat当前位置之前的最长公共前后缀的长度+1！ （这里开了n+1，比较好处理）
     *
     */
    public static int strStr3(String txt, String pat) {
        if (pat == null || pat.length() == 0) {
            return 0;
        }
        int i = 0;
        int j = 0;
        int[] next = getNext(pat);

        while (i < txt.length() && j < pat.length()) {
            //txt和pat的当前字符匹配，往下走
            if (j == -1 || txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            } else {    //不匹配，j从next[j]开始比较
                j = next[j];
            }
        }
        //如果匹配结束了，返回结果
        if (j == pat.length()) {
            return i - pat.length();
        }
        return -1;
    }

    /**
     * 之前说到，next数组就是pat当前位置之前的最长公共前后缀的长度！怎么求next数组？
     * KMP的思想就是重复利用，假设此时next[j]为t，
     *      1、如果此时下一个字符和主串下一个位置相等 ==> next[j+1] = t+1 = next[j]+1
     *      2、如果此时下一个字符和主串下一个位置不等 ==> 循环将 t赋值为next[t]，直到t = 0或者满足1为止，t = 0时，next[j+1] = 1
     *              t=0就是你模式串相对于主串前移把，然后 t+1 从pat的第一个位置开始比较，
     *
     */
    private static int[] getNext(String pat) {
        int j = 0, t = -1, n = pat.length(); //j=1 从第一个字符位置开始
        int[] next = new int[n];
        next[0] = -1;    //从第一个位置开始
        while (j < n - 1) {
            //我们这里是要利用next[j]求next[j+1]
            //pat.charAt(t)这句意思是，在第j个字符发生不匹配的，t=next[j]，也就是下一个要从哪个位置开始比较
            //pat.charAt(j)这是第j+1个字符在原来pat串中的位置，
            //那么就是看这两个字符相不相等，如果第j+1个字符和next[j]位置的这个字符相等，那就是ok的,都往前移动，并设置j+1个自负的失败指针为新的cot
            if (t == -1 || pat.charAt(j) == pat.charAt(t)) {
                j++;
                t++;
                next[j] = t;
            } else {
                t = next[t];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String txt = "ABBBABBAABBA";
        String pat = "BABBAAB";
        strStr3(txt, pat);

    }








    //rabin karp算法
    public int strStr2(String txt, String pat) {
        if (pat.length() == 0) {
            return 0;
        }
        if (txt.length() == 0) {
            return -1;
        }
        int m = pat.length();
        int MOD = 1000000;

        //计算power,用于后面删除头用，要多计算一位
        int power = 1;
        for (int i = 0; i < m; i++) {
            power = power * 31 % MOD;
        }

        //计算pat的hash值
        int patHash = 0;
        for (int i = 0; i < m; i++) {
            patHash = (patHash * 31 + pat.charAt(i)) % MOD;
        }

        //subHash
        int subStrHash = 0;
        int n = txt.length();
        for (int i = 0; i < n; i++) {
            subStrHash = (subStrHash * 31 + txt.charAt(i)) % MOD;
            //如果不够pat的长度
            if (i < m - 1) {
                continue;
            }
            //如果超过pat的长度,要去掉头
            if (i >= m) {
                subStrHash = subStrHash - (txt.charAt(i - m) * power) % MOD;
                if (subStrHash < 0) {
                    subStrHash += MOD;
                }
            }

            //double check
            if (subStrHash == patHash) {
                if (txt.subSequence(i - m + 1, i + 1).equals(pat)) {
                    return i - n + 1;
                }
            }
        }
        return -1;
    }
}