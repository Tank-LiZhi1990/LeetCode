package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution355Twitter {

	private class Tweet {
		int userId;
		int tweetId;
		// String content;
		public Tweet(int userId, int tweetId) {
			super();
			this.userId = userId;
			this.tweetId = tweetId;
		}
	}

	private class User {
		public User(int uid) {
			this.id = uid;
			tweets = new LinkedList<>();
			followers = new HashSet<>();
		}
		int id;
		LinkedList<Tweet> tweets;
		Set<Integer> followers;
	}

	private LinkedList<Tweet> tweets;
	private Map<Integer, User> users;

	/** Initialize your data structure here. */
	public Solution355Twitter() {
		tweets = new LinkedList<>();
		users = new HashMap<Integer, User>();
	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
		User user = null;
		if (!users.containsKey(userId)) {
			user = new User(userId);
			users.put(userId, user);
		} else {
			user = users.get(userId);
		}

		Tweet t = new Tweet(userId, tweetId);
		tweets.addFirst(t);
		user.tweets.addFirst(t);
	}

	/**
	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item
	 * in the news feed must be posted by users who the user followed or by the
	 * user herself. Tweets must be ordered from most recent to least recent.
	 */
	public List<Integer> getNewsFeed(int userId) {

		List<Integer> result = new ArrayList<>();

		if (!users.containsKey(userId)) {
			return result;
		}

		Set<Integer> followers = users.get(userId).followers;
		Iterator<Tweet> it = tweets.iterator();
		int i = 0;
		Tweet next;
		while (it.hasNext() && i < 10) {
			next = it.next();
			if (followers.contains(next.userId) || next.userId == userId) {
				result.add(next.tweetId);
				i++;
			}
		}

		return result;
	}

	/**
	 * Follower follows a followee. If the operation is invalid, it should be a
	 * no-op.
	 */
	public void follow(int followerId, int followeeId) {
		if (!users.containsKey(followerId)) {
			users.put(followerId, new User(followerId));
		}

		if (!users.containsKey(followeeId)) {
			users.put(followeeId, new User(followeeId));
		}

		users.get(followerId).followers.add(followeeId);
	}

	/**
	 * Follower unfollows a followee. If the operation is invalid, it should be
	 * a no-op.
	 */
	public void unfollow(int followerId, int followeeId) {
		if (!users.containsKey(followerId) || !users.containsKey(followeeId)) {
			return;
		}

		users.get(followerId).followers.remove(followeeId);
	}

	/**
	 * Your Twitter object will be instantiated and called as such:
	 * <p>
	 * Twitter obj = new Twitter(); <br/>
	 * obj.postTweet(userId,tweetId); <br/>
	 * List <Integer> param_2 = obj.getNewsFeed(userId); <br/>
	 * obj.follow(followerId,followeeId);<br/>
	 * obj.unfollow(followerId,followeeId);<br/>
	 */

}
