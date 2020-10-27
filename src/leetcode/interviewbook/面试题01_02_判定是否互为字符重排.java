package leetcode.interviewbook;


import java.util.Arrays;
import java.util.stream.Stream;

public class 面试题01_02_判定是否互为字符重排 {

    public boolean CheckPermutation(String s1, String s2) {
        char[] sc1 = s1.toCharArray();
        Arrays.sort(sc1);
        char[] sc2 = s2.toCharArray();
        Arrays.sort(sc2);
        String ns1 = new String(sc1);
        String ns2 = new String(sc2);
        return ns1.equals(ns2);
    }
}