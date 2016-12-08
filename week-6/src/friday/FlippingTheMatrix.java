package friday;

import java.util.Scanner;

public class FlippingTheMatrix {
	int[][] matrix;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int numberOfQueries = scanner.nextInt();
		for (int k = 0; k < numberOfQueries; k++) {
			int n = scanner.nextInt();
			int matrixSize = 2 * n;
			FlippingTheMatrix m = new FlippingTheMatrix(matrixSize);
			for (int i = 0; i < matrixSize; i++) {
				int[] row = new int[matrixSize];
				for (int j = 0; j < matrixSize; j++) {
					row[j] = scanner.nextInt();
				}
				m.fillRow(i, row);
			}
			System.out.println(m.maxSumOfUpperLeftQuadrant());
		}
		scanner.close();

	}

	public FlippingTheMatrix(int n) {
		matrix = new int[n][n];
	}

	public void fillRow(int i, int[] row) {
		for (int j = 0; j < matrix.length; j++) {
			matrix[i][j] = row[j];
		}
	}

	public int maxSumOfUpperLeftQuadrant() {
		int sum = 0;
		for (int i = 0; i < matrix.length / 2; i++) {
			for (int j = 0; j < matrix.length / 2; j++) {
				sum += maxElement(matrix[i][j], matrix[i][matrix.length - j - 1], matrix[matrix.length - i - 1][j],
						matrix[matrix.length - i - 1][matrix.length - j - 1]);
			}
		}
		return sum;
	}

	private int maxElement(int a, int b, int c, int d) {
		return Math.max(Math.max(a, b), Math.max(c, d));
	}

}
