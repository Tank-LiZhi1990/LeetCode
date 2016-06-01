package leetcode;

public class Solution334 {

	/*
	 * Given an unsorted array return whether an increasing subsequence of
	 * length 3 exists or not in the array.
	 * 
	 * Formally the function should:
	 * Return true if there exists i, j, k
	 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return
	 * false.
	 * Your algorithm should run in O(n) time complexity and O(1) space
	 * complexity.
	 * 
	 * Examples:
	 * Given [1, 2, 3, 4, 5],
	 * return true.
	 * 
	 * Given [5, 4, 3, 2, 1],
	 * return false.
	 */

	public boolean increasingTriplet(int[] nums) {
		if (nums.length < 3) {
			return false;
		}
		boolean pos1 = false;
		boolean pos2 = false;
		int max = 0;
		int min = 0;
		for (int i = 1; i < nums.length; i++) {
			if (pos1) {
				if (nums[i] > nums[i - 1]) {
					if (nums[i] > max) {
						return true;
					} else if (nums[i - 1] > min) {
						return true;
					} else if (nums[i] > min) {
						max = nums[i];
					} else {
						min = nums[i - 1];
						max = nums[i];
					}
				}
			} else if (nums[i] > nums[i - 1]) {
				pos1 = true;
				max = nums[i];
				min = nums[i - 1];
			}
		}
		return false;
	}
}
