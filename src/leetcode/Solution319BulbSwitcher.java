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
	 * <p>
	 * opt(k) = opt(k-1)+k的约数是否为奇数
	 * <p>
	 * 约数一定成对出现,除非是平方数36=1x36,2x18,3x12,4x9,6x6
	 * <p>
	 * 对于n,n以内的平方数的个数 小于等于 sqrt(n),也就是亮灯的个数小于等于sqrt(n)
	 * <p>
	 * 因此最后亮灯的个数为sqrt(n)向下取整
	 * 
	 * @param n
	 * @return
	 */
	public int bulbSwitch(int n) {

		return (int) Math.sqrt(n);
	}

	public static void main(String[] args) {
		System.out.println(new Solution319BulbSwitcher().bulbSwitch(100));
	}
}
