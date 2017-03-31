package com.hackbulgaria.sudoku_solution_validator.attempt;

import java.util.concurrent.Callable;

public class ValidateColumn implements Callable<Boolean>{
	int columnIndex;
	int[][] matrix;
	
	public ValidateColumn(int columnIndex, int[][] matrix) {
		this.columnIndex = columnIndex;
		this.matrix = matrix;
	}
	
	@Override
	public Boolean call() throws Exception {
		return Validation.validateColumn(columnIndex, matrix);
	}

}
