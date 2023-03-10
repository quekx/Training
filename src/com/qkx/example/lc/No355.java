package com.qkx.example.lc;

//Design a simplified version of Twitter where users can post tweets, follow/
//unfollow another user, and is able to see the 10 most recent tweets in the user's
//news feed.
//
// Implement the Twitter class:
//
//
// Twitter() Initializes your twitter object.
// void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId
//by the user userId. Each call to this function will be made with a unique
//tweetId.
// List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs
//in the user's news feed. Each item in the news feed must be posted by users who
//the user followed or by the user themself. Tweets must be ordered from most
//recent to least recent.
// void follow(int followerId, int followeeId) The user with ID followerId
//started following the user with ID followeeId.
// void unfollow(int followerId, int followeeId) The user with ID followerId
//started unfollowing the user with ID followeeId.
//
//
//
// Example 1:
//
//
//Input
//["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed",
//"unfollow", "getNewsFeed"]
//[[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
//Output
//[null, null, [5], null, null, [6, 5], null, [5]]
//
//Explanation
//Twitter twitter = new Twitter();
//twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
//twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1
//tweet id -> [5]. return [5]
//twitter.follow(1, 2);    // User 1 follows user 2.
//twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
//twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2
//tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted
//after tweet id 5.
//twitter.unfollow(1, 2);  // User 1 unfollows user 2.
//twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1
//tweet id -> [5], since user 1 is no longer following user 2.
//
//
//
// Constraints:
//
//
// 1 <= userId, followerId, followeeId <= 500
// 0 <= tweetId <= 10⁴
// All the tweets have unique IDs.
// At most 3 * 10⁴ calls will be made to postTweet, getNewsFeed, follow, and
//unfollow.
//
//
// Related Topics Hash Table Linked List Design Heap (Priority Queue) 👍 2882 👎
// 372


import java.util.*;

/**
 * 解答成功:
 * 	执行耗时:8 ms,击败了100.00% 的Java用户
 * 	内存消耗:41 MB,击败了17.04% 的Java用户
 */
//leetcode submit region begin(Prohibit modification and deletion)
class Twitter {

    private TweetNode head = new TweetNode(-1, -1);
    Map<Integer, Set<Integer>> followerMap = new HashMap<>();

    public Twitter() {

    }

    public void postTweet(int userId, int tweetId) {
        addTweetNode(new TweetNode(userId, tweetId));
    }

    private void addTweetNode(TweetNode node) {
        node.next = head.next;
        head.next = node;
    }

    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> followers = followerMap.get(userId);
        List<Integer> ans = new ArrayList<>(10);
        TweetNode node = head.next;
        while (node != null && ans.size() < 10) {
            if (check(node.userId, userId, followers)) {
                ans.add(node.tweetId);
            }
            node = node.next;
        }
        return ans;
    }

    private boolean check(int tweetUserId, int curUserId, Set<Integer> followers) {
        if (tweetUserId == curUserId) {
            return true;
        }
        if (followers == null) {
            return false;
        }
        return followers.contains(tweetUserId);
    }

    public void follow(int followerId, int followeeId) {
        followerMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        followerMap.computeIfAbsent(followerId, k -> new HashSet<>()).remove(followeeId);
    }

    class TweetNode {
        private TweetNode next;
        private int userId;
        private int tweetId;
        public TweetNode(int userId, int tweetId) {
            this.userId = userId;
            this.tweetId = tweetId;
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
//leetcode submit region end(Prohibit modification and deletion)


public class No355 {

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.follow(1, 2);
        System.out.println(113);
    }
}
