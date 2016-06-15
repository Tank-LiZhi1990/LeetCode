package leetcode;

import java.util.Arrays;

public class Solution316RemoveDuplicateLetters {
	/*
	 * Given a string which contains only lowercase letters, remove duplicate
	 * letters so that every letter appear once and only once. You must make
	 * sure your result is the smallest in lexicographical order among all
	 * possible results.
	 * 
	 * Example:
	 * Given "bcabc"
	 * Return "abc"
	 * 
	 * Given "cbacdcbc"
	 * Return "acdb"
	 */

	public String removeDuplicateLetters(String s) {

		int[] exist = new int[26];
		Arrays.fill(exist, -1);

		char[] chs = s.toCharArray();
		for (int i = 0; i < chs.length; ++i) {
			// 记录最后出现的坐标
			exist[chs[i] - 'a'] = i;
		}

		// 包含的字母的数目
		int count = 0;
		for (int i : exist) {
			if (i != -1) {
				++count;
			}
		}

		// 找到begin到end的最小字母
		int begin = 0;
		int end = findMinLastPos(exist);

		// 存放结果
		char[] result = new char[count];

		for (int i = 0; i < result.length; ++i) {
			char minchar = 'z' + 1;
			for (int k = begin; k <= end; ++k) {
				// 该字母尚未加入结果集中,且该字母比当前找到的字母靠前
				if (exist[chs[k] - 'a'] != -1 && chs[k] < minchar) {
					minchar = chs[k];
					begin = k + 1;
				}
			}

			// 将该字母加入结果集
			result[i] = minchar;

			// 表示该字母已经加入结果集
			exist[chs[begin - 1] - 'a'] = -1;

			// 寻找新的最大下标
			end = findMinLastPos(exist);

		}

		return new String(result);
	}

	/**
	 * 找到尚未加入到结果集中的字母中最后一次出现最靠前的那个字母
	 * <p>
	 * 因为在这个段中,该字母必须被加入到结果集中
	 * 
	 * @param exist
	 * @return
	 */
	private int findMinLastPos(int[] exist) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < exist.length; ++i) {
			if (exist[i] != -1 && exist[i] < min) {
				min = exist[i];
			}
		}

		return min;
	}

	public static void main(String[] args) {
		String s = "bcabc";
		System.out.println(new Solution316RemoveDuplicateLetters().removeDuplicateLetters(s));
	}
}
