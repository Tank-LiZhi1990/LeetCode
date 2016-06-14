package leetcode;

import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class Solution327CountofRangeSum {
	/*
	 * Given an integer array nums, return the number of range sums that lie in
	 * [lower, upper] inclusive.
	 * Range sum S(i, j) is defined as the sum of the elements in nums between
	 * indices i and j (i ≤ j), inclusive.
	 * 
	 * Note:
	 * A naive algorithm of O(n2) is trivial. You MUST do better than that.
	 * 
	 * Example:
	 * Given nums = [-2, 5, -1], lower = -2, upper = 2,
	 * Return 3.
	 * The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums
	 * are: -2, -1, 2.
	 */

	public int countRangeSum(int[] nums, int lower, int upper) {

		// 将s(i,j)转化为p[j+1]-p[i]
		int[] prefixArray = buildPrefixArray(nums);
		for (int i : prefixArray) {
			System.out.print(i + " ");
		}
		System.out.println();
		TreeSet<Integer> treeSet = new TreeSet<>();
		int count = 0;
		SortedSet<Integer> small;
		SortedSet<Integer> bigger;
		for (int i = prefixArray.length - 2; i >= 0; --i) {
			treeSet.add(prefixArray[i + 1]);
			small = treeSet.headSet(prefixArray[i] + lower);
			bigger = treeSet.headSet(prefixArray[i] + upper, true);

			count += bigger.size() - small.size();
			bigger.removeAll(new HashSet<>(small));
			System.out.println("[" + i + "," + bigger + "]");
		}

		return count;
	}

	private int[] buildPrefixArray(int[] nums) {
		int[] prefixArray = new int[nums.length + 1];
		prefixArray[0] = 0;
		int sum = 0;
		for (int i = 0; i < nums.length; ++i) {
			sum += nums[i];
			prefixArray[i + 1] = sum;
		}

		return prefixArray;
	}

	public static void main(String[] args) {

		int[] nums = new int[] { -4, 0, -3, -1, 1, 2, 1, -4 };
		 System.out.println(new Solution327CountofRangeSum().countRangeSum(nums, 0, 6));

		for (int i = 0; i < 100; i++) {
			int j = i;
			System.out.println(j += j & -j);
		}
	}

}
