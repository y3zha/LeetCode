package leetcode.coding;

import java.util.*;

public class _1244_力扣排行榜 {

    static class Leaderboard {

        Map<Integer, Player> map;

        public Leaderboard() {
            map = new HashMap<>();
        }

        public void addScore(int playerId, int score) {
            if (!map.containsKey(playerId)) {
                Player player = new Player();
                player.id = playerId;
                player.score = score;
                map.put(playerId, player);
            } else {
                Player player = map.get(playerId);
                player.score += score;
            }
        }

        public int top(int K) {
            ArrayList<Map.Entry<Integer, Player>> list = new ArrayList<>(map.entrySet());
            list.sort((o1, o2) -> -Integer.compare(o1.getValue().score, o2.getValue().score));
            int sum = 0;
            int i = 0;
            Iterator<Map.Entry<Integer, Player>> it = list.iterator();
            while (it.hasNext() && i < K) {
                Map.Entry<Integer, Player> entry = it.next();
                sum += map.get(entry.getKey()).score;
                i++;
            }
            return sum;
        }

        public void reset(int playerId) {
            Player player = map.get(playerId);
            if (player != null) {
                player.score = 0;
            }
        }
    }

    static class Player {
        int id;
        int score;

    }

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addScore(1, 73);
        leaderboard.addScore(2, 56);
        leaderboard.addScore(3, 39);
        leaderboard.addScore(4, 51);
        leaderboard.addScore(5, 4);
        leaderboard.top(1);
        leaderboard.reset(1);
        leaderboard.reset(2);
        leaderboard.addScore(2, 51);
        leaderboard.top(3);
    }
}