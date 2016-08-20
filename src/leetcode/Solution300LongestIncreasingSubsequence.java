package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution300LongestIncreasingSubsequence {

	/**
	 * <p>
	 * [9, 10, 11, 1, 2, 3, 4, 5, 6, 7] <br/>
	 * [9] <br/>
	 * [9,10]<br/>
	 * [9,10,11]<br/>
	 * [1,10,11]<br/>
	 * [1,2,11]<br/>
	 * [1,2,3]<br/>
	 * [1,2,3,4]<br/>
	 * [1,2,3,4,5] <br/>
	 * [1,2,3,4,5,6]<br/>
	 * [1,2,3,4,5,6,7]<br/>
	 * <p>
	 * 
	 * @param nums
	 * @return
	 */
	public int lengthOfLIS(int[] nums) {

		List<Integer> list = new ArrayList<>();
		List<Integer> list2 = null;

		int idx;
		for (int i : nums) {
			if (list.size() == 0 || list.get(list.size() - 1) < i) {
				list.add(i);
				list2 = new ArrayList<>(list);
				continue;
			}
			// return -(low + 1)if not exist
			idx = Collections.binarySearch(list, i);
			if (idx < 0) {
				idx = -idx - 1;
			}
			list.set(idx, i);
		}

		print(list2);

		return list.size();
	}

	private void print(List<Integer> list2) {
		if (list2 != null) {
			for (Integer integer : list2) {
				System.out.print(integer + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6};
		Solution300LongestIncreasingSubsequence o = new Solution300LongestIncreasingSubsequence();
		System.out.println(o.lengthOfLIS(nums));
	}
}
