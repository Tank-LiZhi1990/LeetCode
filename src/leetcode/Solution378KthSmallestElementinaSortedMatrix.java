package leetcode;

public class Solution378KthSmallestElementinaSortedMatrix {

	public static void main(String[] args) {
		int[][] matrix = new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
		Solution378KthSmallestElementinaSortedMatrix o = new Solution378KthSmallestElementinaSortedMatrix();
		System.out.println(o.kthSmallest(matrix, 8));
	}

	public int kthSmallest(int[][] matrix, int k) {

		// 建小顶堆，每次取出当前元素并将它的同列的下一个元素进堆，执行k-1次，堆顶就是所需的元素
		// 因为要知道元素所在的行和列，所以要使用包装类
		// 因为要实现堆，所以要实现compare接口

		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return -1;
		}

		if (k <= 0) {
			return -1;
		}

		int row = matrix.length;
		int col = matrix[0].length;
		if (k > row * col) {
			return -1;
		}

		if (col == 1) {
			return matrix[k - 1][0];
		}

		if (row == 1) {
			return matrix[0][k - 1];
		}

		MatrixValue[] minHeap = new MatrixValue[matrix[0].length];
		for (int i = 0; i < minHeap.length; i++) {
			minHeap[i] = new MatrixValue(0, i, matrix[0][i]);
		}
		buildMinHeap(minHeap);

		for (int i = 1; i < k; i++) {
			replaceHeadAndupdate(minHeap, matrix);
		}

		return minHeap[0].value;
	}

	private void buildMinHeap(MatrixValue[] minHeap) {
		// 0
		// 1 2
		// length = 3
		int fIdx = (minHeap.length - 2) / 2;
		while (fIdx >= 0) {
			heapAjust(minHeap, fIdx);
			fIdx--;
		}
	}

	private void heapAjust(MatrixValue[] minHeap, int idx) {

		int child = idx + idx + 1;
		while (child < minHeap.length) {
			if (child + 1 < minHeap.length
					&& minHeap[child + 1].compareTo(minHeap[child]) < 0) {
				child++;
			}

			if (minHeap[child].compareTo(minHeap[idx]) < 0) {
				swap(minHeap, child, idx);
				idx = child;
				child = idx + idx + 1;
			} else {
				break;
			}
		}
	}

	private void swap(MatrixValue[] minHeap, int cIdx, int fIdx) {
		MatrixValue tmp = minHeap[cIdx];
		minHeap[cIdx] = minHeap[fIdx];
		minHeap[fIdx] = tmp;
	}

	private void replaceHeadAndupdate(MatrixValue[] minHeap, int[][] matrix) {
		MatrixValue first = minHeap[0];
		if (first.row + 1 < matrix.length) {
			minHeap[0] = new MatrixValue(first.row + 1, first.column,
					matrix[first.row + 1][first.column]);
		} else {
			minHeap[0] = new MatrixValue(matrix.length, matrix[0].length,
					Integer.MAX_VALUE);
		}

		heapAjust(minHeap, 0);
	}

	private class MatrixValue implements Comparable<MatrixValue> {

		int row, column;
		int value;

		public MatrixValue(int r, int c, int v) {
			this.row = r;
			this.column = c;
			this.value = v;
		}

		@Override
		public int compareTo(MatrixValue o) {
			return this.value - o.value;
		}
	}
}
