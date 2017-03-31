package com.hackbulgaria.sudoku_solution_validator.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.hackbulgaria.sudoku_solution_validator.attempt.Validation;

public class SudokuSolutionValidator {
	static final int matrixSize = 9;
	private static int[][] matrix;
	

	public static void setMatrix(int[][] matrix) {
		SudokuSolutionValidator.matrix = matrix;
	}

	public static class ValidateColumn implements Callable<Boolean> {
		int columnIndex;

		public ValidateColumn(int columnIndex) {
			this.columnIndex = columnIndex;
		}

		@Override
		public Boolean call() throws Exception {
			CheckFunctions.validateIndex(columnIndex);
			return Validation.validateColumn(columnIndex, matrix);
		}

	}

	public static class ValidateRow implements Callable<Boolean> {
		int rowIndex;

		public ValidateRow(int rowIndex) {
			this.rowIndex = rowIndex;
		}

		@Override
		public Boolean call() throws Exception {
			CheckFunctions.validateIndex(rowIndex);
			return Validation.validateRow(rowIndex, matrix);
		}

	}

	public static class ValidateBox implements Callable<Boolean> {
		int rowIndex;
		int columnIndex;

		public ValidateBox(int rowIndex, int columnIndex) {
			this.rowIndex = rowIndex;
			this.columnIndex = columnIndex;
		}

		@Override
		public Boolean call() throws Exception {
			validateBoxIndex();
			return Validation.validateBox(rowIndex, columnIndex, matrix);
		}

		private void validateBoxIndex() {
			if (rowIndex > 6 || rowIndex % 3 != 0 || columnIndex > 6 || columnIndex % 3 != 0) {
				throw new IndexOutOfBoundsException("Invalid region!");
			}
		}

	}
	
	public static boolean testSolution() throws InterruptedException, ExecutionException {
		List<Callable<Boolean>> tests = new ArrayList<>();
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				if (i % 3 == 0 && j % 3 == 0) {
					tests.add(new ValidateBox(i, j));
				}
				if (i == 0) {
					tests.add(new ValidateColumn(j));
				}
				if (j == 0) {
					tests.add(new ValidateRow(i));
				}
			}
		}

		ExecutorService threadPool = Executors.newCachedThreadPool();
		List<Future<Boolean>> results = threadPool.invokeAll(tests);

		threadPool.shutdown();
		for (Future<Boolean> future : results) {
			if (!Boolean.TRUE.equals(future.get())) {
				return false;
			}
		}
		return true;
	}
	
	public static void fillSudoku() {
		matrix = new int[matrixSize][matrixSize];
		System.out.println("Test your sudoku: ");
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				matrix[i][j]= scanner.nextInt();
			}
		}
		scanner.close();
	}
	
	public static void fillSudoku(int[][] matrix) {
		setMatrix(matrix);
	}
	
	public void printMatrix() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < matrix.length; i++) {
			if (i % 3 == 0) {
				stringBuilder.append(System.getProperty("line.separator"));
			}
			for (int j = 0; j < matrix.length; j++) {
				if (j % 3 == 0) {
					stringBuilder.append(" ");
				}
				stringBuilder.append(matrix[i][j] + " ");
			}
			stringBuilder.append(System.getProperty("line.separator"));
		}
		System.out.println(stringBuilder.toString());
	}
}
