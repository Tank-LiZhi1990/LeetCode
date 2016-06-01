package leetcode;

public class Solution342 {
	/*
	 * Given an integer (signed 32 bits), write a function to check whether it
	 * is a power of 4.
	 * 
	 * Example: Given num = 16, return true. Given num = 5, return false.
	 * 
	 * Follow up: Could you solve it without loops/recursion?
	 */
	public boolean isPowerOfFour(int num) {

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
