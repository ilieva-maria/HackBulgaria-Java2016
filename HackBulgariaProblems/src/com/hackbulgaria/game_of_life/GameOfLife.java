package com.hackbulgaria.game_of_life;

import java.util.Scanner;

public class GameOfLife {
	private static final int matrixSize = 30;
	private static boolean[][] matrix = new boolean[matrixSize][matrixSize];

	public static void main(String[] args) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);

		int numLines = scanner.nextInt();
		int x, y;
		for (int i = 0; i < numLines; i++) {
			x = scanner.nextInt();
			y = scanner.nextInt();
			matrix[x][y] = true;
		}
		
		scanner.close();

		while (true) {
			Thread.sleep(1000);
			changeMatrix();
			printMatrix();
		}
	}

	public static void changeMatrix() {
		boolean newMatrix[][] = new boolean[matrixSize][matrixSize];
		int counter;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				counter = countLivingNeighbours(i, j);
				if (matrix[i][j] == true) {
					if (counter < 2 || counter > 3) {
						newMatrix[i][j] = false;
					} else {
						newMatrix[i][j] = true;
					}
				} else {
					if (counter == 3) {
						newMatrix[i][j] = true;
					}
				}
			}
		}
		matrix = newMatrix;
	}

	public static int countLivingNeighbours(int i, int j) {
		int counter = 0;
		if (j > 0 && (matrix[i][j - 1] == true)) {
			counter++;
		}
		if (i > 0 && (matrix[i - 1][j] == true)) {
			counter++;
		}
		if (i < matrix.length - 1 && (matrix[i + 1][j] == true)) {
			counter++;
		}
		if (j < matrix.length - 1 && (matrix[i][j + 1] == true)) {
			counter++;
		}
		if ((i > 0 && j > 0) && (matrix[i - 1][j - 1] == true)) {
			counter++;
		}
		if ((i < matrix.length - 1 && j < matrix.length - 1) && (matrix[i + 1][j + 1] == true)) {
			counter++;
		}
		if ((i > 0 && j < matrix.length - 1) && (matrix[i - 1][j + 1] == true)) {
			counter++;
		}
		if ((i < matrix.length - 1 && j > 0) && (matrix[i + 1][j - 1] == true)) {
			counter++;
		}
		return counter;
	}

	public static void printMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] == true) {
					System.out.print("■");
				} else {
					System.out.print("□");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}