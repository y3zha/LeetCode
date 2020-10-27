package leetcode.coding;

import java.util.*;
// 355题 设计推特
public class Twitter {

    // 用户和他关注的人的关系表映射 userId -> 他关注的那些人
    HashMap<Integer, HashSet<Integer>> followings;

    // 用户与他发布的推文的映射 userId -> 推文链表
    HashMap<Integer, Tweet> tweets;

    // k路归并用的优先队列,根据时间戳从大到小排序，大的说明是最近发的，所以要建大堆
    PriorityQueue<Tweet> pq;

    // 全局时间戳
    private static int timestamp = 0;

    public Twitter() {
        followings = new HashMap<>();
        tweets = new HashMap<>();
        pq = new PriorityQueue<>(10, (o1, o2) -> o2.timestamp - o1.timestamp);
    }

    // 发推特,传参包括 用户名id，推文id
    public void postTweet(int userId, int tweetId) {
        // 发推特之前时间戳先往后移，表示这条推特的时间戳
        timestamp++;
        // 如果不是第一次发推特
        if (tweets.containsKey(userId)) {
            // 获取之前的
            Tweet preTweet = tweets.get(userId);
            Tweet curTweet = new Tweet(tweetId, timestamp);
            curTweet.next = preTweet;
            tweets.put(userId, curTweet);
        } else {
            // 如果是第一次发，new一个就完事了
            tweets.put(userId, new Tweet(tweetId, timestamp));
        }
    }

    // 获取最近的推特（检索十条），每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
    // List里面放的是推文的id，其实就是k路归并的过程
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed = new ArrayList<>();
        pq.clear();
        // 首先把这个人自己的推文放进去
        Tweet ownTweet = tweets.get(userId);
        if (ownTweet != null) {
            pq.offer(ownTweet);
        }
        // 再把他关注的人的推特放进去
        HashSet<Integer> set = followings.get(userId);
        if (set != null) {
            for (Integer followeeId : set) {
                Tweet followeeTweet = tweets.get(followeeId);
                if (followeeTweet!= null) {
                    pq.offer(followeeTweet);
                }
            }
        }
        while (!pq.isEmpty()) {
            Tweet tweet = pq.poll();
            newsFeed.add(tweet.tweetId);
            if (newsFeed.size() == 10) {
                break;
            } else if (tweet.next != null) {
                pq.offer(tweet.next);
            }
        }
        return newsFeed;
    }

    // 关注用户，twitter.follow(1, 2); 用户1关注用户2
    public void follow(int followerId, int followeeId) {
        // 不能自己关注自己
        if (followerId == followeeId) {
            return;
        }
        followings.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    // 取消关注，twitter.unollow(1, 2); 用户1取消关注用户2
    public void unfollow(int followerId, int followeeId) {
        // 不能自己取关自己
        if (followerId == followeeId) {
            return;
        }
        // 如果 followings 中不存在这个人，就什么也不做吧
        if (followings.get(followerId) != null) {
            followings.get(followerId).remove(followeeId);
        }
    }

    private class Tweet {
        // 推文id
        Integer tweetId;
        // 推文的下一个，是链表结构的
        Tweet next;
        // 时间戳
        int timestamp;

        public Tweet(Integer tweetId, int timestamp) {
            this.tweetId = tweetId;
            this.timestamp = timestamp;
        }
    }

    public static void main(String[] args) {

        Twitter twitter = new Twitter();

        // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
        twitter.postTweet(1, 5);

        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        twitter.getNewsFeed(1);

        // 用户1关注了用户2.
        twitter.follow(1, 2);

        // 用户2发送了一个新推文 (推文id = 6).
        twitter.postTweet(2, 6);

        // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
        // 推文id6应当在推文id5之前，因为它是在5之后发送的.
        twitter.getNewsFeed(1);

        // 用户1取消关注了用户2.
        twitter.unfollow(1, 2);

        // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
        // 因为用户1已经不再关注用户2.
        twitter.getNewsFeed(1);


    }

}