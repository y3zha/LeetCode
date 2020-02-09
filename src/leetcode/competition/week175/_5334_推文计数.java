package leetcode.competition.week175;

import java.util.*;

public class _5334_推文计数 {

    class TweetCounts {

        Map<String, ArrayList<Integer>> map;

        public TweetCounts() {
            map = new HashMap<>();
        }

        //用户 tweetName 在 time（以 秒 为单位）时刻发布了一条推文。
        public void recordTweet(String tweetName, int time) {
            map.computeIfAbsent(tweetName, k -> new ArrayList<>()).add(time);
        }

        //返回从开始时间 startTime（以 秒 为单位）到结束时间 endTime（以 秒 为单位）内，每 分 minute，时 hour 或者 日 day （取决于 freq）内指定用户 tweetName 发布的推文总数。
        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            List<Integer> res = new ArrayList<>();
            int t = 0;
            if (freq.equals("day")) {
                t = 24 * 60 * 60;
            } else if (freq.equals("hour")) {
                t = 60 * 60;
            } else if (freq.equals("minute")) {
                t = 60;
            }
            ArrayList<Integer> list = map.get(tweetName);
            int delta = (endTime - startTime) / t + 1;
            for (int i = 0; i < delta; i++) {
                int count = 0;
                for (Integer a : list) {
                    if (a >= startTime + i * t && a < Math.min((startTime + (i + 1) * t), endTime + 1)) {
                        count++;
                    }

                }
                res.add(count);
            }
            return res;
        }

        /**
         * 第一个时间间隔始终从 startTime 开始，因此时间间隔为 
         * [startTime, startTime + delta*1>,  [startTime + delta*1, startTime + delta*2>, [startTime + delta*2, startTime + delta*3>
         * , ... , [startTime + delta*i, min(startTime + delta*(i+1), endTime + 1)>，其中 i 和 delta（取决于 freq）都是非负整数。
         *
         */
    }
}