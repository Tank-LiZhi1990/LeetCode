package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Solution336 {
	/**
	 * Given a list of unique words. Find all pairs of distinct indices (i, j)
	 * in the given list, so that the concatenation of the two words, i.e.
	 * words[i] + words[j] is a palindrome.
	 * 
	 * Example 1:
	 * Given words = ["bat", "tab", "cat"]
	 * Return [[0, 1], [1, 0]]
	 * The palindromes are ["battab", "tabbat"]
	 * Example 2:
	 * Given words = ["abcd", "dcba", "lls", "s", "sssll"]
	 * Return [[0, 1], [1, 0], [3, 2], [2, 4]]
	 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
	 */

	class TrieTreeNode {
		char ch;
		TrieTreeNode[] childs;
		int frequency;
		int id;

		public TrieTreeNode() {
			childs = new TrieTreeNode[26];
			id = -1;
		}
	}

	public void addTrieNode(TrieTreeNode root, char[] cs, int index, int id) {
		if (cs == null || cs.length == 0) {
			return;
		}

		int k = cs[index] - 'a';

		if (root.childs[k] == null) {
			TrieTreeNode node = new TrieTreeNode();
			node.ch = cs[index];
			root.childs[k] = node;
		}

		index++;
		if (index == cs.length) {
			root.childs[k].id = id;
			return;
		}

		addTrieNode(root.childs[k], cs, index, id);
	}

	// trie tree 前缀树
	public TrieTreeNode buildTireTree(String[] words) {
		TrieTreeNode root = new TrieTreeNode();

		for (int i = 0; i < words.length; ++i) {
			if (!words[i].equals("")) {
				addTrieNode(root, words[i].toCharArray(), 0, i);
			} else {
				// 空串的情况
				root.id = i;
			}
		}

		return root;
	}

	private void getPalindromePairs(String[] words, List<List<Integer>> result,
			TrieTreeNode root) {

		HashSet<Integer> ids;
		List<Integer> tmp;
		for (int i = 0; i < words.length; ++i) {
			ids = new HashSet<>();
			search(root, words[i], ids);
			if (ids.size() > 0) {
				Iterator<Integer> it = ids.iterator();
				while (it.hasNext()) {
					int j = it.next();
					if (j != i) {
						tmp = new ArrayList<>();
						tmp.add(j);
						tmp.add(i);
						result.add(tmp);
					}
				}
			}
		}
	}

	private void hasMirrors(TrieTreeNode root, StringBuilder builder,
			HashSet<Integer> set) {
		for (int i = 0; i < root.childs.length; i++) {
			if (root.childs[i] != null) {
				// 保存当前字符到集合
				builder.append(root.childs[i].ch);
				// 存在单词
				if (root.childs[i].id != -1) {
					// 是否对称
					if (isMirrorSide(builder.toString())) {
						set.add(root.childs[i].id);
					}
				}
				// 搜索子树
				hasMirrors(root.childs[i], builder, set);
				// 从集合去除当前字符
				builder.deleteCharAt(builder.length() - 1);
			}
		}
	}

	private boolean isMirrorSide(String w) {
		int s = 0;
		int e = w.length() - 1;

		while (s < e) {
			if (w.charAt(s) != w.charAt(e)) {
				return false;
			}
			++s;
			--e;
		}

		return true;
	}

	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> result = new ArrayList<>();
		if (words == null || words.length == 0) {
			return result;
		}

		TrieTreeNode root = buildTireTree(words);

		getPalindromePairs(words, result, root);

		return result;
	}

	private void search(TrieTreeNode root, String word, HashSet<Integer> ids) {
		// 空串
		if (word.length() == 0) {
			StringBuilder builder = new StringBuilder();
			hasMirrors(root, builder, ids);
		} else {
			// 如果单词对称，将根节点加入
			if (isMirrorSide(word) && root.id != -1) {
				ids.add(root.id);
			}
			// 搜索其他情况
			search(root, word.toCharArray(), word.length() - 1, ids);
		}
	}

	private void search(TrieTreeNode root, char[] word, int index,
			HashSet<Integer> set) {

		// 到达一个单词，已经包含了字符长度大于树的完全匹配的情况
		if (root.id != -1) {
			String w = new String(word, 0, index + 1);
			if (isMirrorSide(w)) {
				set.add(root.id);
			}
		}

		// 获得相应的位移
		int k = word[index] - 'a';

		// 不能匹配
		if (root.childs[k] == null) {
			return;
		}

		// 单词短于或等于树长度，此时单词已匹配完
		if (index == 0) {
			// 子节点有单词，则加入
			if (root.childs[k].id != -1) {
				set.add(root.childs[k].id);
			}
			// 判断该节点下的所有子树节点有没有对称节点，有则将该节点id加入集合
			StringBuilder builder = new StringBuilder();
			hasMirrors(root.childs[k], builder, set);
			return;
		}

		// 未比较完
		search(root.childs[k], word, index - 1, set);
	}
}
