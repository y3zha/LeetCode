package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class _1125_最小的必要团队 {

    // 做前先做691
    // 压缩所有技能(1<<n)和每个人的技能，然后用dp枚举所有状态
    // 背包问题 01
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length;

        // 给技能编号, 建立位图对应关系, 每一位代表一个技能
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(req_skills[i], i);
        }

        // 获取人的技能清单（过滤没用技能）
        int m = people.size();
        int[] mask = new int[m];
        for (int i = 0; i < m; i++) {
            mask[i] = convert(people.get(i), map);
        }

        // dp[i]表示在当前状态下，所需的最少人数是多少个
        int[] dp = new int[1 << n];
        int INF = 0x3f3f3f3f;
        Arrays.fill(dp, INF);
        dp[0] = 0;

        HashMap<Integer, List<Integer>> teams = new HashMap<>();
        teams.put(0, new ArrayList<>());
        // 对于前i个人
        for (int i = 0; i < people.size(); i++) {
            for (int j = 0; j < 1 << n; j++) {
                int t = j | mask[i];
                if (dp[j] + 1 < dp[t]) {
                    dp[t] = dp[j] + 1;
                    List<Integer> list = new ArrayList<>(teams.get(j));
                    list.add(i);
                    teams.put(t, list);
                }
            }
        }
        List<Integer> list = teams.get((1 << n) - 1);
        System.out.println(list);
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    private int convert(List<String> skills, HashMap<String, Integer> map) {
        int res = 0;
        for (String skill : skills) {
            if (map.containsKey(skill)) {
                res |= (1 << map.get(skill));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _1125_最小的必要团队 test = new _1125_最小的必要团队();

        String[] rs = {"java", "nodejs", "reactjs"};
        List<List<String>> p = new ArrayList<>();
        List<String> l1 = new ArrayList<>();
        l1.add("java");
        l1.add("nodejs");
        List<String> l2 = new ArrayList<>();
        l2.add("reactjs");
        l2.add("nodejs");
        p.add(l1);
        p.add(l2);
        test.smallestSufficientTeam(rs, p);
    }
}