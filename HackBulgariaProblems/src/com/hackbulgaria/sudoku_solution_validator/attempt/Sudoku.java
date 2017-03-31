package com.hackbulgaria.sudoku_solution_validator.attempt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class Sudoku {
	private final int[][] matrix;
	
	public Sudoku() {
		matrix = new int[Validation.matrixSize][Validation.matrixSize];
	}

	public Sudoku(int[][] matrix) {
		this.matrix = matrix;
	}

	public boolean testSudoku() throws InterruptedException, ExecutionException {
		List<Callable<Boolean>> tests = new ArrayList<>();
		tests.add(() -> Boolean.valueOf(Validation.checkMatrix(matrix)));
		tests.add(() -> Boolean.valueOf(Validation.checkRows(matrix)));
		tests.add(() -> Boolean.valueOf(Validation.checkColumns(matrix)));
		tests.add(() -> Boolean.valueOf(Validation.checkRegions(matrix)));

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
	
	public void fill() {
		if (!Validation.checkMatrix(matrix)) {
			return;
		}
		System.out.println("Test your sudoku: ");
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				
				matrix[i][j]= scanner.nextInt();
			}
		}
		scanner.close();
	}

	@Override
	public String toString() {
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
		return stringBuilder.toString();
	}
	

}
