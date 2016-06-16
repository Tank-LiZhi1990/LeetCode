package leetcode;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Solution310MinimumHeightTrees2 {
	/*
	 * For a undirected graph with tree characteristics, we can choose any node
	 * as the root. The result graph is then a rooted tree. Among all possible
	 * rooted trees, those with minimum height are called minimum height trees
	 * (MHTs). Given such a graph, write a function to find all the MHTs and
	 * return a list of their root labels.
	 * 
	 * Format
	 * The graph contains n nodes which are labeled from 0 to n - 1. You will be
	 * given the number n and a list of undirected edges (each edge is a pair of
	 * labels).
	 * 
	 * You can assume that no duplicate edges will appear in edges. Since all
	 * edges are undirected, [0, 1] is the same as [1, 0] and thus will not
	 * appear together in edges.
	 * 
	 * Example 1:
	 * 
	 * Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
	 * 
	 * 0
	 * |
	 * 1
	 * / \
	 * 2 3
	 * return [1]
	 * 
	 * Example 2:
	 * 
	 * Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
	 * 
	 * 0 1 2
	 * \ | /
	 * 3
	 * |
	 * 4
	 * |
	 * 5
	 * return [3, 4]
	 */

	private class Node {
		// 节点的值
		int val;
		// 节点当前高度
		int h;
		// 节点父节点编号
		int parent;
		// 节点邻居
		List<Node> adjs;

		public Node(int v) {
			this.val = v;
			adjs = new ArrayList<>();
		}
	}

	public List<Integer> findMinHeightTrees(int n, int[][] edges) {

		List<Integer> result = new ArrayList<Integer>();
		// 图不正确
		if (n == 0 || n != edges.length + 1) {
			return result;
		}

		// 只有一个节点
		if (n == 1) {
			result.add(0);
			return result;
		}

		// 构建图
		List<Node> graph = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			graph.add(new Node(i));
		}

		for (int[] is : edges) {
			graph.get(is[0]).adjs.add(graph.get(is[1]));
			graph.get(is[1]).adjs.add(graph.get(is[0]));
		}

		BreadFirstSearch(graph, result);

		return result;
	}

	private void BreadFirstSearch(List<Node> graph, List<Integer> result) {
		MQueue queue = new MQueue(graph.size());
		int min = Integer.MAX_VALUE, height;
		Node node;
		for (int i = 0; i < graph.size(); i++) {
			node = graph.get(i);
			node.h = 1;
			node.parent = -1;
			queue.offer(node);
			height = calculateHeight(queue);

			// 找到了高度更低的,则把数组清空
			if (height < min) {
				min = height;
				result.clear();
				result.add(i);
			} else if (height == min) {
				result.add(i);
			}
		}
	}

	private int calculateHeight(MQueue queue) {
		Node tmp, node = null;
		int size, i;
		while (queue.peek() != null) {
			node = queue.poll();
			size = node.adjs.size();
			i = 0;
			while (i < size) {
				tmp = node.adjs.get(i);
				// 避免环路,节点的子节点不再把父节点放入队列
				if (tmp.val != node.parent) {
					tmp.h = node.h + 1;
					tmp.parent = node.val;
					queue.offer(tmp);
				}
				i++;
			}
		}

		return node != null ? node.h : 1;
	}

	private class MQueue {
		private Node[] elements;
		private int first;
		private int last;
		private int size;
		private int length;

		public MQueue(int n) {
			elements = new Node[n];
			length = n;
			size = 0;
			// 往first放;
			first = 0;
			// 从last出
			last = 0;
		}

		/**
		 * 放入元素
		 * 
		 * @param e
		 *            要放入的元素
		 * @return 如果队列满,返回false,否则返回true
		 */
		public boolean offer(Node e) {
			if (size == length) {
				return false;
			}
			elements[first] = e;
			first++;
			size++;
			if (first == length) {
				first = 0;
			}
			return true;
		}

		/**
		 * 取出元素
		 * 
		 * @return 移除队列的下一个元素并返回该元素,或者null,如果队列为空
		 */
		public Node poll() {
			if (size == 0) {
				return null;
			}
			Node tmp = elements[last];
			last++;
			size--;
			if (last == length) {
				last = 0;
			}
			return tmp;
		}

		/**
		 * 返回队列的顶端元素
		 * 
		 * @return
		 */
		public Node peek() {
			if (size == 0) {
				return null;
			}
			return elements[last];
		}
	}

	public static void main(String[] args) {

		int[][] edges = new int[][] { { 0, 1 }, { 1, 2 }, { 1, 3 }, { 2, 4 }, { 3, 5 }, { 4, 6 } };
		long cur = System.currentTimeMillis();
		System.out.println(new Solution310MinimumHeightTrees2().findMinHeightTrees(7, edges));
		System.out.println(System.currentTimeMillis() - cur);
	}
}
