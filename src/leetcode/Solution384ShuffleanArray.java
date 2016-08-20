package leetcode;

import java.util.Arrays;
import java.util.Random;

public class Solution384ShuffleanArray {

	private final int[] nums;
	private int[] shuffle;

	public Solution384ShuffleanArray(int[] nums) {
		this.nums = nums;
		shuffle = new int[nums.length];
		System.arraycopy(nums, 0, shuffle, 0, nums.length);
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {

		return Arrays.copyOf(nums, nums.length);
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		// 选一个数和最后一个数交换,再在前n-1个数中选一个和倒数第二个数交换,直到只剩一个数 ???
		long seed = System.nanoTime();
		Random r = new Random(seed);
		int randomPos;
		int len = shuffle.length;
		for (int i = 0; i < shuffle.length; i++) {
			randomPos = r.nextInt(len - i);
			swap(shuffle, randomPos, len - 1 - i);
		}

		return shuffle;
	}

	private void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		Solution384ShuffleanArray sa = new Solution384ShuffleanArray(arr);
		int[] arr1 = sa.shuffle();
		print(arr1);
		arr1 = sa.reset();
		print(arr1);
	}

	private static void print(int[] arr1) {
		for (int i : arr1) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
