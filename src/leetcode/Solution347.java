package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution347 {
	/*
	 * Given a non-empty array of integers, return the k most frequent elements.
	 * 
	 * For example, Given [1,1,1,2,2,3] and k = 2, return [1,2].
	 * 
	 * Note: You may assume k is always valid, 1 ≤ k ≤ number of unique
	 * elements. Your algorithm's time complexity must be better than O(n log
	 * n), where n is the array's size.
	 */
	public List<Integer> topKFrequent(int[] nums, int k) {

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i : nums) {
			if (map.containsKey(i)) {
				map.put(i, map.get(i) + 1);
			} else {
				map.put(i, 1);
			}
		}

		List<Integer> result = new ArrayList<>();
		int maxCount = 0;
		int index = -1;
		ArrayList<Integer> keys = new ArrayList<>(map.keySet());
		while (k-- > 0) {
			Integer km = null;
			for (int i = 0; i < keys.size(); i++) {
				km = keys.get(i);
				if (map.get(km) > maxCount) {
					index = i;
					maxCount = map.get(km);
				}
			}

			result.add(keys.get(index));
			keys.remove(index);
			maxCount = 0;
			index = -1;
		}

		return result;
	}

	public static void main(String[] args) {
		int[] a = new int[]{1, 1, 1, 2, 2, 3};
		int k = 2;
		for (int i : new Solution347().topKFrequent(a, k)) {
			System.out.println(i);
		}
	}

}
