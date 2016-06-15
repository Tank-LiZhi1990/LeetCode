package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution315CountofSmallerNumbersAfterSelf {
	/*
	 * You are given an integer array nums and you have to return a new counts
	 * array. The counts array has the property where counts[i] is the number of
	 * smaller elements to the right of nums[i].
	 * 
	 * Example:
	 * 
	 * Given nums = [5, 2, 6, 1]
	 * 
	 * To the right of 5 there are 2 smaller elements (2 and 1).
	 * To the right of 2 there is only 1 smaller element (1).
	 * To the right of 6 there is 1 smaller element (1).
	 * To the right of 1 there is 0 smaller element.
	 * Return the array [2, 1, 1, 0].
	 */

	/**
	 * <p>
	 * 从后往前
	 * <p>
	 * 每向前搜索一次,执行一次插入排序(链表比数组快?)
	 * <p>
	 * 每次搜索用二分搜索,找到第一个比n小的数
	 * <p>
	 * 与起点比较,算出数目
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> result = new ArrayList<>();

		if (nums == null || nums.length == 0) {
			return result;
		}

		result.add(0);

		// 从要遍历的数开始,剩下的数组的第一个位置为begin,数组末尾为end
		int begin = nums.length - 1;
		int end = nums.length - 1;
		// 表示第一个比n小的数的下标
		int index;
		for (int i = nums.length - 2; i >= 0; --i) {
			index = divideSearch(nums, begin, end, nums[i]);
			// 计算比n小的数的个数,并存储
			result.add(index - begin + 1);
			begin = i;
			end = nums.length - 1;
			// 将nums[i]放到合适位置
			insertSort(nums, begin, index);
		}

		// 遍历是从后向前的,所以这里要反序
		Collections.reverse(result);

		return result;
	}

	/**
	 * 二分法找到最后一个比n小的数的坐标
	 * <p>
	 * 若所有数比n大,index=end(begin-1);
	 * <p>
	 * 若所有数比n小,index=end(nums.length-1),此时begin=nums.length;
	 * <p>
	 * 若该数在数组中某个位置,index=最后一个比n小的数的下标(此时,end<n,begin>=n)
	 * 
	 * @param nums
	 *            数组
	 * @param begin
	 *            查找起始范围
	 * @param end
	 *            查找结束范围
	 * @param n
	 *            要比较的数
	 * @return 最后一个比n的小的数的坐标
	 */
	private int divideSearch(int[] nums, int begin, int end, int n) {
		int mid;
		while (end >= begin) {
			mid = (end + begin) / 2;
			if (nums[mid] >= n) {
				end = mid - 1;
			} else if (nums[mid] < n) {
				begin = mid + 1;
			}
		}
		return end;
	}

	/**
	 * 直接插入排序
	 * <p>
	 * 二分法已经找到了最后一个比n小的数(end位置)
	 * <p>
	 * 该函数实现将所有比n小的数向前移动一个位置
	 * <p>
	 * 并将要插入的数放到原来最后一个比n小的数的位置
	 * 
	 * @param nums
	 *            数组
	 * @param begin
	 *            从要插入的数开始,循环时从该坐标下一个坐标开始
	 * @param end
	 *            最后一个比n小的数的坐标(要移动的数的坐标)
	 */
	private void insertSort(int[] nums, int begin, int end) {
		int x = nums[begin];
		while (begin < end) {
			nums[begin] = nums[begin + 1];
			begin++;
		}
		nums[end] = x;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 1 };
		for (int i : new Solution315CountofSmallerNumbersAfterSelf().countSmaller(nums)) {
			System.out.print(i + " ");
		}
	}
}
