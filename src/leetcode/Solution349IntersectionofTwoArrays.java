package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution349IntersectionofTwoArrays {
	/*
	 * Given two arrays, write a function to compute their intersection.
	 * 
	 * Example:
	 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
	 * 
	 * Note:
	 * Each element in the result must be unique.
	 * The result can be in any order.
	 */
	public int[] intersection(int[] nums1, int[] nums2) {
		if (nums1.length > nums2.length) {
			int[] tmp = nums1;
			nums1 = nums2;
			nums2 = tmp;
		}

		Set<Integer> integers = new HashSet<>();
		for (int i : nums1) {
			integers.add(i);
		}

		List<Integer> list = new ArrayList<>();
		for (int i : nums2) {
			if (integers.contains(i)) {
				list.add(i);
				integers.remove(i);
			}
		}

		int[] result = new int[list.size()];
		int i = 0;
		for (int j : list) {
			result[i++] = j;
		}

		return result;
	}
}
