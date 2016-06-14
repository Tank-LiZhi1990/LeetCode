package leetcode;

public class Solution319BulbSwitcher {
	/*
	 * There are n bulbs that are initially off. You first turn on all the
	 * bulbs. Then, you turn off every second bulb. On the third round, you
	 * toggle every third bulb (turning on if it's off or turning off if it's
	 * on). For the ith round, you toggle every i bulb. For the nth round, you
	 * only toggle the last bulb. Find how many bulbs are on after n rounds.
	 * 
	 * Example:
	 * 
	 * Given n = 3.
	 * 
	 * At first, the three bulbs are [off, off, off].
	 * After first round, the three bulbs are [on, on, on].
	 * After second round, the three bulbs are [on, off, on].
	 * After third round, the three bulbs are [on, off, off].
	 * 
	 * So you should return 1, because there is only one bulb is on.
	 */

	/**
	 * <br>
	 * 000000
	 * <br>
	 * 111111
	 * <br>
	 * 101010
	 * <br>
	 * 100011
	 * <br>
	 * 100111
	 * <br>
	 * 100101
	 * <br>
	 * 100100
	 * 
	 * <p>
	 * opt(k) = opt(k-1)+k的公约数是否为奇数
	 * 
	 * @param n
	 * @return
	 */
	public int bulbSwitch(int n) {

		// 求n的公约数的个数
		int count = commonDivisor(n);

		return n;
	}

	private int commonDivisor(int n) {
		return 0;
	}
}
