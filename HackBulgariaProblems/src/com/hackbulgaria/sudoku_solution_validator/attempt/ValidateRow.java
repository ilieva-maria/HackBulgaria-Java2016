package com.hackbulgaria.sudoku_solution_validator.attempt;

import java.util.concurrent.Callable;

public class ValidateRow implements Callable<Boolean>{
	int rowIndex;
	int[][] matrix;
	
	public ValidateRow(int rowIndex, int[][] matrix) {
		this.rowIndex = rowIndex;
		this.matrix = matrix;
	}
	@Override
	public Boolean call() throws Exception {
		return Validation.validateRow(rowIndex, matrix);
	}

}
