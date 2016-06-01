package leetcode;

public class Solution345 {

	/*
	 * Write a function that takes a string as input and reverse only the vowels
	 * of a string.
	 * 
	 * Example 1: Given s = "hello", return "holle".
	 * 
	 * Example 2: Given s = "leetcode", return "leotcede".
	 */
	public String reverseVowels(String s) {

		if (s == null) {
			return null;
		}

		char[] cs = s.toCharArray();
		char[] rs = new char[cs.length];
		int index = -1;
		for (char c : cs) {
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
					|| c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
				rs[++index] = c;
			}
		}

		for (int i = 0; i < cs.length; i++) {
			if (cs[i] == 'a' || cs[i] == 'e' || cs[i] == 'i' || cs[i] == 'o'
					|| cs[i] == 'u' || cs[i] == 'A' || cs[i] == 'E'
					|| cs[i] == 'I' || cs[i] == 'O' || cs[i] == 'U') {
				cs[i] = rs[index--];
			}
		}

		return new String(cs);
	}

	public static void main(String[] args) {
		System.out.println(new Solution345().reverseVowels("Aa"));
	}
}
