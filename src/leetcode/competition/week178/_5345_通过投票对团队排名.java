package leetcode.competition.week178;

import java.util.*;

public class _5345_通过投票对团队排名 {

    //记录每一个字母出现的位置再自定义排序
    //1 <= votes[i].length <= 26
    public static String rankTeams(String[] votes) {
        int[][] rank = new int[26][26]; //26个字母，最大长度为26
        for (String vote : votes) {
            for (int i = 0; i < vote.length(); i++) {
                rank[vote.charAt(i) - 'A'][i]++;
            }
        }
        Integer[] temp = new Integer[26];   //一定得是Integer 才能用 new Comparator，包装类
        for (int i = 0; i < 26; i++) {
            temp[i] = i;
        }
        Arrays.sort(temp, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 对于每一个位置，如果票数不一样，那肯定是降序排,【每个位置都要看】！！！
                for (int i = 0; i < 26; i++) {
                    if (rank[o1][i] != rank[o2][i]) {
                        return rank[o2][i] - rank[o1][i];
                    }
                }
                //如果每个位置读看过了都是一样的，那就按照字母升序
                return o1.compareTo(o2);
            }
        });

        //统计
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < votes[0].length(); i++) {
            sb.append((char) (temp[i] + 'A'));
        }
        return sb.toString();
    }
}