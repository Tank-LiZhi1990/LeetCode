package leetcode;

public class Solution344 {

	/*
	 * Write a function that takes a string as input and returns the string
	 * reversed.
	 * 
	 * Example: Given s = "hello", return "olleh".
	 */
	public String reverseVowels(String s) {

		if (s == null) {
			return null;
		}

		char[] cs = s.toCharArray();

		char c;
		int n = cs.length;
		for (int i = 0; i < n / 2; ++i) {
			c = cs[i];
			cs[i] = cs[n - i - 1];
			cs[n - i - 1] = c;
		}

		return new String(cs);
	}
	public static void main(String[] args) {
		System.out.println(new Solution344().reverseVowels("iodshkjasf"));
	}
}
