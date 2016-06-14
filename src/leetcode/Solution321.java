package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution321 {

	/*
	 * Given two arrays of length m and n with digits 0-9 representing two
	 * numbers. Create the maximum number of length k <= m + n from digits of
	 * the two. The relative order of the digits from the same array must be
	 * preserved. Return an array of the k digits. You should try to optimize
	 * your time and space complexity.
	 * 
	 * Example 1:
	 * nums1 = [3, 4, 6, 5]
	 * nums2 = [9, 1, 2, 5, 8, 3]
	 * k = 5
	 * return [9, 8, 6, 5, 3]
	 * 
	 * Example 2:
	 * nums1 = [6, 7]
	 * nums2 = [6, 0, 4]
	 * k = 5
	 * return [6, 7, 6, 0, 4]
	 * 
	 * Example 3:
	 * nums1 = [3, 9]
	 * nums2 = [8, 9]
	 * k = 3
	 * return [9, 8, 9]
	 */

	/**
	 * The basic idea:
	 * 
	 * To create max number of length k from two arrays, you need to create max
	 * number of length i from array one and max number of length k-i from array
	 * two, then combine them together. After trying all possible i, you will
	 * get the max number created from two arrays.
	 * 
	 * Optimization:
	 * 
	 * <p>
	 * Suppose nums1 = [3, 4, 6, 5], nums2 = [9, 1, 2, 5, 8, 3], the maximum
	 * number you can create from nums1 is [6, 5] with length 2. For nums2, it's
	 * [9, 8, 3] with length 3. Merging the two sequence, we have [9, 8, 6, 5,
	 * 3], which is the max number we can create from two arrays without length
	 * constraint. If the required length k<=5, we can simply trim the result to
	 * required length from front. For instance, if k=3, then [9, 8, 6] is the
	 * result.
	 * 
	 * <p>
	 * Suppose we need to create max number with length 2 from num = [4, 5, 3,
	 * 2, 1, 6, 0, 8]. The simple way is to use a stack, first we push 4 and
	 * have stack [4], then comes 5 > 4, we pop 4 and push 5, stack becomes [5],
	 * 3 < 5, we push 3, stack becomes [5, 3]. Now we have the required length
	 * 2, but we need to keep going through the array in case a larger number
	 * comes, 2 < 3, we discard it instead of pushing it because the stack
	 * already grows to required size 2. 1 < 3, we discard it. 6 > 3, we pop 3,
	 * since 6 > 5 and there are still elements left, we can continue to pop 5
	 * and push 6, the stack becomes [6], since 0 < 6, we push 0, the stack
	 * becomes [6, 0], the stack grows to required length again. Since 8 > 0, we
	 * pop 0, although 8 > 6, we can't continue to pop 6 since there is only one
	 * number, which is 8, left, if we pop 6 and push 8, we can't get to length
	 * 2, so we push 8 directly, the stack becomes [6, 8].
	 * 
	 * <p>
	 * In the basic idea, we mentioned trying all possible length i. If we
	 * create max number for different i from scratch each time, that would be a
	 * waste of time. Suppose num = [4, 9, 3, 2, 1, 8, 7, 6], we need to create
	 * max number with length from 1 to 8. For i==8, result is the original
	 * array. For i==7, we need to drop 1 number from array, since 9 > 4, we
	 * drop 4, the result is [9, 3, 2, 1, 8, 7, 6]. For i==6, we need to drop 1
	 * more number, 3 < 9, skip, 2 < 3, skip, 1 < 2, skip, 8 > 1, we drop 1, the
	 * result is [9, 3, 2, 8, 7, 6]. For i==5, we need to drop 1 more, but this
	 * time, we needn't check from beginning, during last scan, we already know
	 * [9, 3, 2] is monotonically non-increasing, so we check 8 directly, since
	 * 8 > 2, we drop 2, the result is [9, 3, 8, 7, 6]. For i==4, we start with
	 * 8, 8 > 3, we drop 3, the result is [9, 8, 7, 6]. For i==3, we start with
	 * 8, 8 < 9, skip, 7 < 8, skip, 6 < 7, skip, by now, we've got maximum
	 * number we can create from num without length constraint. So from now on,
	 * we can drop a number from the end each time. The result is [9, 8, 7], For
	 * i==2, we drop last number 7 and have [9, 8]. For i==1, we drop last
	 * number 8 and have [9].
	 */
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int[] result = null;

		List<List<Integer>> max_nums1 = new ArrayList<>(k + 1);
		List<List<Integer>> max_nums2 = new ArrayList<>(k + 1);

		genILengthMax(nums1, max_nums1, k);
		genILengthMax(nums2, max_nums2, k);

		for (int i = 0; i <= k; ++i) {
			if (max_nums1.size() < i + 1 || max_nums2.size() < k - i + 1
					|| max_nums1.get(i).size() + max_nums2.get(k - i).size() < k) {
				continue;
			}

			int[] tmp = new int[k];
			merge(max_nums1.get(i), max_nums2.get(k - i), tmp);

			if (result == null || compare(result, tmp) < 0) {
				result = tmp;
			}
		}

		return result;
	}

	/**
	 * 求出每个数组i个最大子数组时的组合（i = 0 to k）
	 * 
	 * @param nums1
	 * @param max_nums1
	 *            存放找到的最大子数组
	 * @param k
	 */
	private void genILengthMax(int[] nums1, List<List<Integer>> max_nums1, int k) {
		List<Integer> tmp = new ArrayList<>();
		for (int i : nums1) {
			tmp.add(i);
		}

		int start = 0, m;
		while (tmp.size() > 0) {
			if (tmp.size() <= k) {
				max_nums1.add(0, new ArrayList<>(tmp));
			}

			// 找到要删除的数，即当该数比其后一个数小时，删除该数
			m = start;
			while (m + 1 < tmp.size() && tmp.get(m) >= tmp.get(m + 1)) {
				m++;
			}

			tmp.remove(m);
			// 下次寻找的起始坐标，不必每次从0开始
			start = (m == 0 ? 0 : m - 1);
		}
		// i = 0时，放入一个空数组
		max_nums1.add(0, new ArrayList<>());
	}

	/**
	 * 合并两个数组，使其组合最大
	 * 
	 * @param list1
	 * @param list2
	 * @param tmp
	 *            合并后的结果
	 */
	private void merge(List<Integer> list1, List<Integer> list2, int[] tmp) {
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < list1.size() && j < list2.size()) {
			// 找到第一个不相等的位置
			int m = i;
			int n = j;
			while (m < list1.size() && n < list2.size() && list1.get(m) == list2.get(n)) {
				m++;
				n++;
			}
			// 将较大者所在的数组中的下一个数作为结果。当数组中下一个数相等时，通过前面的判定可以知道取哪一个得到的数会更大。
			if (n >= list2.size() || (m < list1.size() && list1.get(m) > list2.get(n))) {
				tmp[k] = list1.get(i);
				i++;
			} else {
				tmp[k] = list2.get(j);
				j++;
			}
			k++;
		}
		// 将剩下的数放入结果中
		while (i < list1.size()) {
			tmp[k++] = list1.get(i++);
		}

		while (j < list2.size()) {
			tmp[k++] = list2.get(j++);
		}
	}

	/**
	 * 比较两个数组组成的数的大小
	 * 
	 * @param result
	 * @param tmp
	 * @return 0 前者大，-1后者大
	 */
	private int compare(int[] result, int[] tmp) {

		int i = 0;
		while (i < result.length && result[i] == tmp[i]) {
			i++;
		}

		if (i < result.length && result[i] < tmp[i]) {
			return -1;
		}

		return 0;
	}

	public static void main(String[] args) {
		// int[] nums2 = new int[] { 9, 1, 2, 5, 8, 3 };
		// int[] nums1 = new int[] { 3, 4, 6, 5 };
		// int[] nums2 = new int[] { 6, 7 };
		// int[] nums1 = new int[] { 6, 0, 4 };

		int[] nums2 = new int[] { 8, 9, 7, 3, 5, 9, 1, 0, 8, 5, 3, 0, 9, 2, 7, 4, 8, 9, 8, 1, 0, 2,
				0, 2, 7, 2, 3, 5, 4, 7, 4, 1, 4, 0, 1, 4, 2, 1, 3, 1, 5, 3, 9, 3, 9, 0, 1, 7, 0, 6,
				1, 8, 5, 6, 6, 5, 0, 4, 7, 2, 9, 2, 2, 7, 6, 2, 9, 2, 3, 5, 7, 4, 7, 0, 1, 8, 3, 6,
				6, 3, 0, 8, 5, 3, 0, 3, 7, 3, 0, 9, 8, 5, 1, 9, 5, 0, 7, 9, 6, 8, 5, 1, 9, 6, 5, 8,
				2, 3, 7, 1, 0, 1, 4, 3, 4, 4, 2, 4, 0, 8, 4, 6, 5, 5, 7, 6, 9, 0, 8, 4, 6, 1, 6, 7,
				2, 0, 1, 1, 8, 2, 6, 4, 0, 5, 5, 2, 6, 1, 6, 4, 7, 1, 7, 2, 2, 9, 8, 9, 1, 0, 5, 5,
				9, 7, 7, 8, 8, 3, 3, 8, 9, 3, 7, 5, 3, 6, 1, 0, 1, 0, 9, 3, 7, 8, 4, 0, 3, 5, 8, 1,
				0, 5, 7, 2, 8, 4, 9, 5, 6, 8, 1, 1, 8, 7, 3, 2, 3, 4, 8, 7, 9, 9, 7, 8, 5, 2, 2, 7,
				1, 9, 1, 5, 5, 1, 3, 5, 9, 0, 5, 2, 9, 4, 2, 8, 7, 3, 9, 4, 7, 4, 8, 7, 5, 0, 9, 9,
				7, 9, 3, 8, 0, 9, 5, 3, 0, 0, 3, 0, 4, 9, 0, 9, 1, 6, 0, 2, 0, 5, 2, 2, 6, 0, 0, 9,
				6, 3, 4, 1, 2, 0, 8, 3, 6, 6, 9, 0, 2, 1, 6, 9, 2, 4, 9, 0, 8, 3, 9, 0, 5, 4, 5, 4,
				6, 1, 2, 5, 2, 2, 1, 7, 3, 8, 1, 1, 6, 8, 8, 1, 8, 5, 6, 1, 3, 0, 1, 3, 5, 6, 5, 0,
				6, 4, 2, 8, 6, 0, 3, 7, 9, 5, 5, 9, 8, 0, 4, 8, 6, 0, 8, 6, 6, 1, 6, 2, 7, 1, 0, 2,
				2, 4, 0, 0, 0, 4, 6, 5, 5, 4, 0, 1, 5, 8, 3, 2, 0, 9, 7, 6, 2, 6, 9, 9, 9, 7, 1, 4,
				6, 2, 8, 2, 5, 3, 4, 5, 2, 4, 4, 4, 7, 2, 2, 5, 3, 2, 8, 2, 2, 4, 9, 8, 0, 9, 8, 7,
				6, 2, 6, 7, 5, 4, 7, 5, 1, 0, 5, 7, 8, 7, 7, 8, 9, 7, 0, 3, 7, 7, 4, 7, 2, 0, 4, 1,
				1, 9, 1, 7, 5, 0, 5, 6, 6, 1, 0, 6, 9, 4, 2, 8, 0, 5, 1, 9, 8, 4, 0, 3, 1, 2, 4, 2,
				1, 8, 9, 5, 9, 6, 5, 3, 1, 8, 9, 0, 9, 8, 3, 0, 9, 4, 1, 1, 6, 0, 5, 9, 0, 8, 3, 7,
				8, 5 };
		int[] nums1 = new int[] { 7, 8, 4, 1, 9, 4, 2, 6, 5, 2, 1, 2, 8, 9, 3, 9, 9, 5, 4, 4, 2, 9,
				2, 0, 5, 9, 4, 2, 1, 7, 2, 5, 1, 2, 0, 0, 5, 3, 1, 1, 7, 2, 3, 3, 2, 8, 2, 0, 1, 4,
				5, 1, 0, 0, 7, 7, 9, 6, 3, 8, 0, 1, 5, 8, 3, 2, 3, 6, 4, 2, 6, 3, 6, 7, 6, 6, 9, 5,
				4, 3, 2, 7, 6, 3, 1, 8, 7, 5, 7, 8, 1, 6, 0, 7, 3, 0, 4, 4, 4, 9, 6, 3, 1, 0, 3, 7,
				3, 6, 1, 0, 0, 2, 5, 7, 2, 9, 6, 6, 2, 6, 8, 1, 9, 7, 8, 8, 9, 5, 1, 1, 4, 2, 0, 1,
				3, 6, 7, 8, 7, 0, 5, 6, 0, 1, 7, 9, 6, 4, 8, 6, 7, 0, 2, 3, 2, 7, 6, 0, 5, 0, 9, 0,
				3, 3, 8, 5, 0, 9, 3, 8, 0, 1, 3, 1, 8, 1, 8, 1, 1, 7, 5, 7, 4, 1, 0, 0, 0, 8, 9, 5,
				7, 8, 9, 2, 8, 3, 0, 3, 4, 9, 8, 1, 7, 2, 3, 8, 3, 5, 3, 1, 4, 7, 7, 5, 4, 9, 2, 6,
				2, 6, 4, 0, 0, 2, 8, 3, 3, 0, 9, 1, 6, 8, 3, 1, 7, 0, 7, 1, 5, 8, 3, 2, 5, 1, 1, 0,
				3, 1, 4, 6, 3, 6, 2, 8, 6, 7, 2, 9, 5, 9, 1, 6, 0, 5, 4, 8, 6, 6, 9, 4, 0, 5, 8, 7,
				0, 8, 9, 7, 3, 9, 0, 1, 0, 6, 2, 7, 3, 3, 2, 3, 3, 6, 3, 0, 8, 0, 0, 5, 2, 1, 0, 7,
				5, 0, 3, 2, 6, 0, 5, 4, 9, 6, 7, 1, 0, 4, 0, 9, 6, 8, 3, 1, 2, 5, 0, 1, 0, 6, 8, 6,
				6, 8, 8, 2, 4, 5, 0, 0, 8, 0, 5, 6, 2, 2, 5, 6, 3, 7, 7, 8, 4, 8, 4, 8, 9, 1, 6, 8,
				9, 9, 0, 4, 0, 5, 5, 4, 9, 6, 7, 7, 9, 0, 5, 0, 9, 2, 5, 2, 9, 8, 9, 7, 6, 8, 6, 9,
				2, 9, 1, 6, 0, 2, 7, 4, 4, 5, 3, 4, 5, 5, 5, 0, 8, 1, 3, 8, 3, 0, 8, 5, 7, 6, 8, 7,
				8, 9, 7, 0, 8, 4, 0, 7, 0, 9, 5, 8, 2, 0, 8, 7, 0, 3, 1, 8, 1, 7, 1, 6, 9, 7, 9, 7,
				2, 6, 3, 0, 5, 3, 6, 0, 5, 9, 3, 9, 1, 1, 0, 0, 8, 1, 4, 3, 0, 4, 3, 7, 7, 7, 4, 6,
				4, 0, 0, 5, 7, 3, 2, 8, 5, 1, 4, 5, 8, 5, 6, 7, 5, 7, 3, 3, 9, 6, 8, 1, 5, 1, 1, 1,
				0, 3 };

		for (int i : new Solution321().maxNumber(nums1, nums2, 500)) {
			System.out.print(i);
		}
	}
}
