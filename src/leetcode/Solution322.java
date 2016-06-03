package leetcode;

import java.util.Arrays;

public class Solution322 {

	/*
	 * You are given coins of different denominations and a total amount of
	 * money amount. Write a function to compute the fewest number of coins that
	 * you need to make up that amount. If that amount of money cannot be made
	 * up by any combination of the coins, return -1.
	 * 
	 * Example 1:
	 * coins = [1, 2, 5], amount = 11
	 * return 3 (11 = 5 + 5 + 1)
	 * 
	 * Example 2:
	 * coins = [2], amount = 3
	 * return -1.
	 * 
	 * Note:
	 * You may assume that you have an infinite number of each kind of coin.
	 */

	public int coinChange(int[] coins, int amount) {

		// 对于包含第k个硬币时的最少数目
		// =不包含第k个硬币时（1到amount）达到的最少数目(只用其他硬币就已经达到最小数目)
		// 与该数目去掉硬币价值后加一个硬币(多使用一个这个硬币才能达到最少数目)
		// 中较小的一个值。
		// opt(coins[1 to k])
		// = min(opt(coins[1 to k-1]),opt(coins[i to k-1] - k) + 1);

		if (coins == null || coins.length == 0) {
			return -1;
		}

		int[] dp = new int[amount + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int value : coins) {
			for (int i = value; i <= amount; ++i) {
				if (dp[i - value] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], dp[i - value] + 1);
				}
			}
		}

		return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];

	}

	public static void main(String[] args) {
		int[] coins = new int[] { 1, 2, 5, 10 };

		System.out.println(new Solution322().coinChange(coins, 111));
	}

}
