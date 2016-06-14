package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution350IntersectionofTwoArraysII {
	/*
	 * Given two arrays, write a function to compute their intersection.
	 * 
	 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
	 * 
	 * Note: Each element in the result should appear as many times as it shows
	 * in both arrays. The result can be in any order. Follow up: What if the
	 * given array is already sorted? How would you optimize your algorithm?
	 * What if nums1's size is small compared to num2's size? Which algorithm is
	 * better? What if elements of nums2 are stored on disk, and the memory is
	 * limited such that you cannot load all elements into the memory at once?
	 */
	public int[] intersect(int[] nums1, int[] nums2) {
		// not sorted

		Map<Integer, Integer> is = new HashMap<>();
		if (nums1.length > nums2.length) {
			int[] tmp = nums1;
			nums1 = nums2;
			nums2 = tmp;
		}

		for (int i : nums1) {
			if (is.containsKey(i)) {
				is.put(i, (is.get(i)) + 1);
			} else {
				is.put(i, 1);
			}
		}

		List<Integer> list = new ArrayList<>();
		int c = 0;
		for (int i : nums2) {
			if (!is.containsKey(i)) {
				continue;
			}

			c = is.get(i);
			if (c > 0) {
				list.add(i);
				is.put(i, c - 1);
			}
		}

		int[] result = new int[list.size()];
		int i = 0;
		for (int j : list) {
			result[i++] = j;
		}

		return result;
	}

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

	public static void main(String[] args) {
		int[] a = new int[]{1, 2, 2, 1, 3, 5, 6, 7, 8, 2, 2, 34, 6, 7, 8, 99,
				0, 23, 12, 1};
		int[] b = new int[]{2, 2, 3, 34, 6, 1, 48};

		int[] c = new Solution350IntersectionofTwoArraysII().intersect(a, b);
		for (int i : c) {
			System.out.println(i);
		}
	}
}
