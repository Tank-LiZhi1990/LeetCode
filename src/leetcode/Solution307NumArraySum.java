package leetcode;

public class Solution307NumArraySum {

	// 线段树
	private class Node {
		int leftNum;
		int rightNum;

		int sum;

		Node left;
		Node right;
		Node parent;

		public Node(int ln, int rn) {
			this.leftNum = ln;
			this.rightNum = rn;
		}
	}

	private Node root;
	private Node[] nodes;

	public Solution307NumArraySum(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}

		nodes = new Node[nums.length];

		root = new Node(0, nums.length - 1);
		buildTree(root, nums, 0, nums.length - 1);
	}

	private int buildTree(Node node, int[] nums, int l, int r) {
		if (l > r) {
			return 0;
		}

		node.leftNum = l;
		node.rightNum = r;

		if (l == r) {
			node.sum = nums[l];
			nodes[l] = node;
			return node.sum;
		}

		int mid = (r + l) / 2;
		Node left = new Node(l, mid);
		Node right = new Node(mid + 1, r);
		node.left = left;
		node.right = right;
		left.parent = node;
		right.parent = node;

		node.sum = buildTree(left, nums, l, mid)
				+ buildTree(right, nums, mid + 1, r);

		return node.sum;
	}

	void update(int i, int val) {
		Node tmp = root;
		if (tmp == null || i < tmp.leftNum || i > tmp.rightNum) {
			return;
		}

		Node node = nodes[i];
		int originalNum = node.sum;
		node.sum = val;
		int res = val - originalNum;

		while (node.parent != null) {
			node = node.parent;
			node.sum += res;
		}
	}

	public int sumRange(int i, int j) {
		Node tmp = root;
		if (tmp == null || i > j || i > tmp.rightNum || j < tmp.leftNum) {
			return 0;
		}

		return sumRange(tmp, i, j);
	}

	private int sumRange(Node node, int i, int j) {

		if (i == node.leftNum && j == node.rightNum) {
			return node.sum;
		}

		int mid = (node.leftNum + node.rightNum) / 2;
		if (i > mid) {
			return sumRange(node.right, i, j);
		} else if (j <= mid) {
			return sumRange(node.left, i, j);
		} else {
			return sumRange(node.left, i, mid)
					+ sumRange(node.right, mid + 1, j);
		}
	}

	public static void main(String[] args) {

		int[] nums = new int[]{1, 3, 5};
		Solution307NumArraySum numArray = new Solution307NumArraySum(nums);
		System.out.println(numArray.sumRange(0, 1));
		numArray.update(1, 10);
		System.out.println(numArray.sumRange(1, 2));
	}
}
