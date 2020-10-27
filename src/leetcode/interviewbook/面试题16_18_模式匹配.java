package leetcode.interviewbook;

import java.util.Collections;

public class 面试题16_18_模式匹配 {

    /**
     * 模拟
     * 情况一：patten中只有1种字母，看v能否等分成len(patten)份一样的字符串
     * <p>
     * 情况二：
     * patten中含有ab,交换ab,使p以a开头，分三种情况讨论
     * 1 a=="" b!="" 计算p中b个数n,看v能否等分成n份一样的字符串
     * 2 a!="" b=="" 同上
     * 3 a!="" b!="" a!=b 循环1到len(value)/a 个数的长度的value头部子串作为a，
     * 结合b在p中的起始位置和b的所需个数以及value的长度,唯一确定所需的b,根据ab和规则p,生成p代表的字符串，和value对比
     */

    public static boolean patternMatching(String pattern, String value) {
        if (pattern.length() == 0 && value.length() == 0) return true;
        if (pattern.length() == 0) return false;
        int[] cnt = new int[2];
        // 如果第一个字母是b，那么就把a和b置换
        if (pattern.charAt(0) == 'b') {
            char[] sc = pattern.toCharArray();
            for (int i = 0; i < sc.length; i++) {
                sc[i] = sc[i] == 'a' ? 'b' : 'a';
            }
            pattern = new String(sc);
        }
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == 'a') {
                cnt[0]++;
            } else {
                cnt[1]++;
            }
        }
        if (cnt[0] == 0 || cnt[1] == 0) {
            return check(cnt[0] + cnt[1], value);
        } else if (value.trim().length() != 0) {
            // 如果a为""或者b为""
            boolean check = check(cnt[1], value) || check(cnt[0], value);
            if (check) {
                return true;
            } else {
                int idx = pattern.indexOf('b');
                // b千面有 index个a
                if (idx != 0) {
                    int len = value.length() / idx;
                    for (int i = 1; i <= len; i++) {
                        String a = value.substring(0, i);   // a, i为a的长度
                        int lenB = (value.length() - cnt[0] * i) / cnt[1];
                        if (lenB <= 0) {
                            continue;
                        }
                        String b = value.substring(i * idx, i * idx + lenB);
                        StringBuilder sb = new StringBuilder();
                        for (int j = 0; j < pattern.length(); j++) {
                            if (pattern.charAt(j) == 'a') {
                                sb.append(a);
                            } else {
                                sb.append(b);
                            }
                        }
                        if (sb.toString().equals(value)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    private static boolean check(int times, String value) {
        if (value.trim().length() == 0) {
            return true;
        }
        int n = value.length();
        int len = n / times;
        String sub = value.substring(0, len);
        String s = String.join("", Collections.nCopies(times, sub));
        return s.equals(value);
    }

    public static void main(String[] args) {
        patternMatching("baabab", "eimmieimmieeimmiee");
    }

}