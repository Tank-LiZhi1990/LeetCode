package leetcode;

public class Solution338CountingBits {

	/*
	 * Given a non negative integer number num. For every numbers i in the range
	 * 0 ≤ i ≤ num calculate the number of 1's in their binary representation
	 * and return them as an array.
	 * 
	 * Example:
	 * For num = 5 you should return [0,1,1,2,1,2].
	 * 
	 * Follow up:
	 * 
	 * It is very easy to come up with a solution with run time
	 * O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in
	 * a single pass?
	 * Space complexity should be O(n).
	 * Can you do it like a boss? Do it without using any builtin function like
	 * __builtin_popcount in c++ or in any other language.
	 */

	public int[] countBits(int num) {

		if (num < 0) {
			return new int[0];
		}

		int[] results = new int[num + 1];
		for (int i = 0; i < results.length; i++) {
			results[i] = countBitOfEach(i);
		}

		return results;
	}

	private int countBitOfEach(int n) {
		int count = 0;
		while (n != 0) {
			++count;
			n = (n - 1) & n;
		}

		return count;
	}
}
