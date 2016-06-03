package leetcode;

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

	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int[] result = new int[k];
		if (nums1 == null || nums2 == null || nums1.length + nums2.length < k) {
			return result;
		}

		int arr1LastIndex = nums1.length - 1;
		int arr2LastIndex = nums2.length - 1;

		int arr1FirstIndex = 0;
		int arr2FirstIndex = 0;

		search(nums1, nums2, k, arr1LastIndex, arr2LastIndex, arr1FirstIndex, arr2FirstIndex,
				result);

		return result;
	}

	private void search(int[] nums1, int[] nums2, int k, int arr1LastIndex, int arr2LastIndex,
			int arr1FirstIndex, int arr2FirstIndex, int[] result) {

		if (k == 0) {
			return;
		}

		int length1 = arr1LastIndex - arr1FirstIndex + 1;
		int length2 = arr2LastIndex - arr2FirstIndex + 1;

		// 在第二个数组的arr2FirstIndex到arr2LastIndex位置寻找最大值
		if (k - length1 <= 0) {
			arr2LastIndex = length2 - 1;
		} else {
			arr2LastIndex = length2 - (k - length1);
		}

		// 在第一个数组的arr1FirstIndex到arr1LastIndex位置寻找最大值
		if (k - length2 <= 0) {
			arr1LastIndex = length1 - 1;
		} else {
			arr1LastIndex = length1 - (k - length2);
		}

		// 最大值的下标
		int index1 = findMaxIndex(nums1, arr1FirstIndex, arr1LastIndex);
		int index2 = findMaxIndex(nums2, arr2FirstIndex, arr2LastIndex);

		// 得到最大值
		int max1 = -1;
		if (index1 != -1)
			max1 = nums1[index1];
		int max2 = -1;
		if (index1 != -1)
			max2 = nums2[index2];

		// 比较两个最大值的大小
		// 下次搜索的起始位置为该数的下一个
		if (max1 > max2) {
			// 如果较大的来自第一个数组
			arr1FirstIndex = index1 + 1;
			result[result.length - k] = max1;
		} else if (max2 > max1) {
			// 如果较大的来自第二个数组
			arr2FirstIndex = index2 + 1;
			result[result.length - k] = max2;
		} else {
			// 相等时
			// 只能分别看哪种得到的数更大
		}

		search(nums1, nums2, k - 1, arr1LastIndex, arr2LastIndex, arr1FirstIndex, arr2FirstIndex,
				result);
	}

	private int findMaxIndex(int[] nums, int arrFirstIndex, int arrLastIndex) {

		if (arrFirstIndex < 0 || arrLastIndex > nums.length - 1) {
			return -1;
		}

		int max = nums[arrFirstIndex];
		int index = arrFirstIndex;
		for (int i = arrFirstIndex + 1; i <= arrLastIndex; ++i) {
			if (nums[i] > max) {
				max = nums[i];
				index = i;
			}
		}

		return index;
	}
}
