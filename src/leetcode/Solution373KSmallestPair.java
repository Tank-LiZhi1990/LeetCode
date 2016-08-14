package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution373KSmallestPair {
	private class Pair {
		long sum;
		int index;
		int[] pair;
		public Pair(int a, int b, int index) {
			this.index = index;
			this.pair = new int[]{a, b};
			this.sum = (long) a + (long) b;
		}
	}

	private class PairCompare implements Comparator<Pair> {

		@Override
		public int compare(Pair o1, Pair o2) {

			return Long.compare(o1.sum, o2.sum);
		}
	}
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<int[]> result = new ArrayList<>();

		if (nums2 == null || nums2 == null || nums1.length == 0
				|| nums2.length == 0) {

			return result;
		}
		
		int len;
		if (k > (len = nums1.length * nums2.length)) {
			k = len;
		}

		PriorityQueue<Pair> queue = new PriorityQueue<>(k, new PairCompare());
		for (int i = 0; i < nums1.length; ++i) {
			queue.offer(new Pair(nums1[i], nums2[0], 0));
		}

		for (int i = 0; i < k; ++i) {
			Pair cur = queue.poll();
			result.add(cur.pair);
			if (cur.index == nums2.length - 1) {
				continue;
			}

			queue.offer(
					new Pair(cur.pair[0], nums2[cur.index + 1], cur.index + 1));

		}
		return result;
	}

	public static void main(String[] args) {

		int[] a = new int[]{1, 1, 2};
		int[] b = new int[]{1, 2, 3};
		int k = 10;

		for (int[] i : new Solution373KSmallestPair().kSmallestPairs(a, b, k)) {
			System.out.println(i[0] + " " + i[1]);
		}
	}
}
