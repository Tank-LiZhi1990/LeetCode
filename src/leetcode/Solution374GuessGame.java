package leetcode;

public class Solution374GuessGame {
	private int num = 1702766719;
	public int guessNumber(int n) {
		int start = 1;
		int end = n;
		int mid = end / 2;
		int result = guess(mid);
		System.out.println(mid);
		while (result != 0) {
			if (result < 0) {
				end = mid - 1;
			} else if (result > 0) {
				start = mid + 1;
			}
			mid = start + (end - start) / 2;
			System.out.println(mid);
			result = guess(mid);
		}

		return mid;
	}

	private int guess(int guess) {
		return guess < num ? 1 : (guess == num ? 0 : -1);
	}

	public static void main(String[] args) {
		System.out.println(new Solution374GuessGame().guessNumber(2126753390));
	}
}