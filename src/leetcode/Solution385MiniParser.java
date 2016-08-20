package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution385MiniParser {

	public static void main(String[] args) {
		Solution385MiniParser n = new Solution385MiniParser();
		// String s = "[123,[456,[789]]]";
		String s = "345";
		n.deserialize(s);
	}
	public NestedInteger deserialize(String s) {
		// 0-9 [ , - ]
		if (!s.contains("[") && !s.contains(",")) {
			return new NestedInteger(Integer.valueOf(s));
		}
		Deque<NestedInteger> deque = new ArrayDeque<>();
		NestedInteger integer = new NestedInteger();
		char[] chs = s.toCharArray();

		int spos = -1;
		for (int i = 0; i < chs.length; i++) {
			char c = chs[i];
			if (c == '[') {
				NestedInteger integer2 = new NestedInteger();
				integer.getList().add(integer2);
				deque.offerFirst(integer);
				integer = integer2;
				continue;
			}
			if (c == ']') {
				if (spos != -1) {
					String value = s.substring(spos, i);
					int val = Integer.valueOf(value);
					// integer.setInteger(val);
					integer.getList().add(new NestedInteger(val));
					spos = -1;
				} // ]]

				integer = deque.removeFirst();
				continue;
			}
			if (c == ',') {
				if (spos != -1) {
					String value = s.substring(spos, i);
					int val = Integer.valueOf(value);
					// integer.setInteger(val);
					integer.getList().add(new NestedInteger(val));
					spos = -1;
				} // ],
				continue;
			}
			if (spos == -1) {
				spos = i;
			}
		}

		return integer;
	}

	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementation
	static class NestedInteger {

		private List<NestedInteger> list;
		private int value;

		// Constructor initializes an empty nested list.
		public NestedInteger() {
			list = new ArrayList<>();
		}

		// Constructor initializes a single integer.
		public NestedInteger(int value) {
			this.value = value;
		}

		// @return true if this NestedInteger holds a single integer, rather
		// than a nested list.
		public boolean isInteger() {

			return list.size() == 1;
		}

		// @return the single integer that this NestedInteger holds, if it holds
		// a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger() {

			return value;
		}

		// Set this NestedInteger to hold a single integer.
		public void setInteger(int value) {

			this.value = value;
		}

		// Set this NestedInteger to hold a nested list and adds a nested
		// integer to it.
		public void add(NestedInteger ni) {

			list.add(ni);
		}

		// @return the nested list that this NestedInteger holds, if it holds a
		// nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList() {

			return list;
		}
	}
}
