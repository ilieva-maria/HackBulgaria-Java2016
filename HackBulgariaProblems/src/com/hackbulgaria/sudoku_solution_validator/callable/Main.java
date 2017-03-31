package com.hackbulgaria.sudoku_solution_validator.callable;

import java.util.concurrent.ExecutionException;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		SudokuSolutionValidator.fillSudoku();
		System.out.println(SudokuSolutionValidator.testSolution());
	}
}
