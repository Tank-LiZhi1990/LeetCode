package leetcode;

public class Solution334IncreasingTripletSubsequence {

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
		// 表示是否已经找到了两个递增的数
		boolean flag = false;
		int max = 0;
		int min = 0;
		for (int i = 1; i < nums.length; i++) {
			if (flag) {
				// i从递增的两个数的下一个数开始循环
				if (nums[i] > nums[i - 1]) {
					if (nums[i] > max) {
						// i max min
						return true;
					} else if (nums[i - 1] > min) {
						// i i-1 min
						return true;
					} else if (nums[i] > min) {
						max = nums[i];
					} else {
						// 新的两个数比原先的数小，则他们组成连续递增的可能性更大
						min = nums[i - 1];
						max = nums[i];
					}
				}
			} else if (nums[i] > nums[i - 1]) {
				flag = true;
				max = nums[i];
				min = nums[i - 1];
			}
		}
		return false;
	}
}
