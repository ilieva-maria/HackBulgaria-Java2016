package com.hackbulgaria.sudoku_solution_validator.attempt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Validation {
	static final int matrixSize = 9;

	public static boolean checkRows(int[][] matrix) {
		for (int i = 0; i < matrixSize; i++) {
			if (!validateRow(i, matrix)) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkColumns(int[][] matrix) {
		for (int j = 0; j < matrixSize; j++) {
			if (!validateColumn(j, matrix)) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkRegions(int[][] matrix) {
		for (int i = 0; i < matrixSize; i += 3) {
			for (int j = 0; j < matrixSize; j += 3) {
				if (!validateBox(i, j, matrix)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean checkMatrix(int[][] matrix) {
		return matrix != null && matrix.length == matrixSize && matrix[0].length == matrixSize;
	}

	public static boolean validateRow(int i, int[][] matrix) {
		return validate(i, i + 1, 0, matrixSize, matrix);
	}

	public static boolean validateColumn(int j, int[][] matrix) {
		return validate(0, matrixSize, j, j + 1, matrix);
	}

	public static boolean validateBox(int i, int j, int[][] matrix) {
		return validate(i, i + 3, j, j + 3, matrix);
	}

	private static boolean validate(int fromRowIndex, int toRowIndex, int fromColumnIndex, int toColumnIndex,
			int[][] matrix) {
		List<Integer> list = IntStream.range(1, 10).boxed().collect(Collectors.toList());
		for (int i = fromRowIndex; i < toRowIndex; i++) {
			for (int j = fromColumnIndex; j < toColumnIndex; j++) {
				if (!isValidDigit(matrix[i][j])) {
					return false;
				}
				list.remove(Integer.valueOf(matrix[i][j]));
			}
		}
		return list.isEmpty();
	}

	private static boolean isValidDigit(int digit) {
		return digit >= 1 && digit <= 9;
	}

//	public static void main(String[] args) {
//		int[][] matrix = new int[matrixSize][matrixSize];
//		Random rand = new Random();
//		for (int j = 0; j < matrixSize; j++) {
//			matrix[0][j] = j + 1;
//		}
//
//		for (int i = 1; i < matrixSize; i++) {
//			for (int j = 0; j < matrixSize; j++) {
//				matrix[i][j] = rand.nextInt(9) + 1;
//			}
//		}
//
//		for (int i = 0; i < matrixSize; i++) {
//			if (i % 3 == 0) {
//				System.out.println();
//			}
//			for (int j = 0; j < matrixSize; j++) {
//				if (j % 3 == 0) {
//					System.out.print(" ");
//				}
//				System.out.print(matrix[i][j] + " ");
//
//			}
//			System.out.println();
//		}
//		System.out.println(validateRow(0, matrix));
//		System.out.println(validateBox(0, 0, matrix));
//		System.out.println(validateBox(0, 3, matrix));
//	}
}
