package leetcode;

public class Solution326PowerofThree {
	/*
	 * Given an integer, write a function to determine if it is a power of
	 * three.
	 * 
	 * Follow up:
	 * Could you do it without using any loop / recursion?
	 */
	/**
	 * <code>
	 * bool isPowerOfThree(int n) {<br/>
	 * 	if (n<=0) return false;<br/>
	 * int t = pow(3,(int)(log(INT_MAX)/log(3)));<br/>
	 * return (t%n == 0);<br/>
	 * }<br/>
	 * </code>
	 * 
	 * @param n
	 * @return
	 */
	public boolean isPowerOfThree(int n) {
		int mod = 0;
		while (n > 1) {
			mod = n % 10;
			if (mod != 1 && mod != 3 && mod != 7 && mod != 9) {
				return false;
			}
			mod = n % 3;
			if (mod != 0) {
				return false;
			}
			n = n / 3;
		}
		if (n == 1) {
			return true;
		}
		return false;
	}
}
