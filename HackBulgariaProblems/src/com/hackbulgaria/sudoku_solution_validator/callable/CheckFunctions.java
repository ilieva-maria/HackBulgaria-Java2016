package com.hackbulgaria.sudoku_solution_validator.callable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CheckFunctions {
	public static boolean validateRow(int i, int[][] matrix) {
		return validate(i, i + 1, 0, SudokuSolutionValidator.matrixSize, matrix);
	}

	public static boolean validateColumn(int j, int[][] matrix) {
		return validate(0, SudokuSolutionValidator.matrixSize, j, j + 1, matrix);
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
	
	public static boolean checkMatrix(int[][] matrix) {
		return matrix != null && matrix.length == SudokuSolutionValidator.matrixSize 
				&& matrix[0].length == SudokuSolutionValidator.matrixSize;
	}
	
	public static void validateIndex(int index) {
		if (index < 0 || index > SudokuSolutionValidator.matrixSize - 1) {
			throw new IndexOutOfBoundsException("Invalid row index!");
		}
	}
}
