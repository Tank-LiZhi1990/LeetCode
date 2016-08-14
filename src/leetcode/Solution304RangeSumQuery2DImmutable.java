package leetcode;

public class Solution304RangeSumQuery2DImmutable {

	private int[][] matrix;
	private int[][] rightEndSum;
	private boolean invalid = false;

	public Solution304RangeSumQuery2DImmutable(int[][] matrix) {
		this.matrix = matrix;
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			invalid = true;
		} else {
			rightEndSum = new int[matrix.length][matrix[0].length];
			countRightEndSum();
		}
	}

	private void countRightEndSum() {
		int sum = 0;
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix[0].length; ++j) {
				sum = matrix[i][j];
				if (i > 0) {
					sum += rightEndSum[i - 1][j];
				}
				if (j > 0) {
					sum += rightEndSum[i][j - 1];
				}
				if(i>0&&j>0){
					sum-=rightEndSum[i-1][j-1];
				}
				rightEndSum[i][j] = sum;
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if (invalid) {
			return 0;
		}

		int sum = rightEndSum[row2][col2];
		if (row1 > 0) {
			sum -= rightEndSum[row1 - 1][col2];
		}
		if (col1 > 0) {
			sum -= rightEndSum[row2][col1 - 1];
		}

		if (row1 > 0 && col1 > 0) {
			sum += rightEndSum[row1 - 1][col1 - 1];
		}

		return sum;
	}

	public static void main(String[] args) {
		Solution304RangeSumQuery2DImmutable s = new Solution304RangeSumQuery2DImmutable(
				new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5},
						{4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}});
		System.out.println(s.sumRegion(2, 1, 4, 3));
	}
}
