package leetcode;

public class Solution357UniqueDigits {
	public int countNumbersWithUniqueDigits(int n) {
		if (n < 0) {
			return 0;
		}
		if (n == 0) {
			return 2;
		}
		if (n == 1) {
			return 10;
		}

		int len = String.valueOf((long)Math.pow(10, n)).length();
		int sum = 0;
		for (int i = 1; i < len; ++i) {
			sum += getNCountNums(i);
		}

		return sum;
	}

	private int getNCountNums(int i) {
		if (i <= 0 || i > 10) {
			return 0;
		}

		if (i == 1) {
			return 10;
		} else {
			int total = 9;
			for (int j = 1; j < i; ++j) {
				total *= (10 - j);
			}
			return total;
		}
	}

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(
				new Solution357UniqueDigits().countNumbersWithUniqueDigits(3));
	}
}
