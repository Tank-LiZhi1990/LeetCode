package leetcode;

public class Solution342PowerofFour {
	/*
	 * Given an integer (signed 32 bits), write a function to check whether it
	 * is a power of 4.
	 * 
	 * Example: Given num = 16, return true. Given num = 5, return false.
	 * 
	 * Follow up: Could you solve it without loops/recursion?
	 */

	/**
	 * Idea is simple. Powers of four are 1, 4, 16.. or in binary, 0x0001,
	 * 0x0100, etc. Only one bit is ever set, and it's always an odd bit. So
	 * simply check for that...
	 * 
	 * This does not use any loops or recursion, is O(1) time and O(1) space.
	 * 
	 * @param num
	 * @return
	 */
	public boolean isPowerOfFour(int num) {
		// 是否只存在一个位为1
		if ((num & (num - 1)) == 0) {
			return false;
		}

		// 是否在奇数位上
		if ((num & 0x55555555) != 0) {
			return true;
		}

		return false;
	}

	public boolean isPowerOfFour2(int num) {

		if (num <= 0) {
			return false;
		}

		if (num == 1) {
			return true;
		}

		if ((num & (num - 1)) != 0) {
			return false;
		}

		if (num % 10 == 2 || num % 10 == 8) {
			return false;
		}

		return true;
	}
}
