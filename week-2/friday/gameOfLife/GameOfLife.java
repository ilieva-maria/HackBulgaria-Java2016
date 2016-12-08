package gameOfLife;

import java.util.Scanner;

public class GameOfLife {
	static boolean[][] matrix = new boolean[20][20];

	public static void main(String[] args) throws InterruptedException {

		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		String[] coordinates = new String[n];
		scanner.nextLine();
		for (int i = 0; i < n; i++) {
			coordinates[i] = scanner.nextLine();
			Pair<Integer, Integer> pair = parseLine(coordinates[i]);
			matrix[pair.getLeft()][pair.getRight()] = true;
		}
		scanner.close();

		while (true) {
			Thread.sleep(1000);
			changeMatrix();
			printMatrix();
			System.out.println();
		}

	}

	public static void changeMatrix() {
		boolean newMatrix[][] = new boolean[20][20];
		int livingNeighbours = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				livingNeighbours = countLivingNeighbours(i, j);
				if (matrix[i][j] == false) {
					if (livingNeighbours == 3) {
						newMatrix[i][j] = true;
					}
				} else {
					if (livingNeighbours < 2 || livingNeighbours > 3) {
						newMatrix[i][j] = false;
					} else {
						newMatrix[i][j] = true;
					}
				}
			}
		}
		matrix = newMatrix;
	}

	public static void printMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] == false) {
					System.out.print("□ ");
				} else {
					System.out.print("■ ");
				}
			}
			System.out.println();
		}
	}

	public static int countLivingNeighbours(int i, int j) {
		int livingNeighbours = 0;
		if ((i > 0 && i < matrix.length - 1) && (j > 0 && j < matrix.length - 1)) {
			if (j > 0 && (matrix[i][j - 1] == true)) {
				livingNeighbours++;
			}
			if (i > 0 && (matrix[i - 1][j] == true)) {
				livingNeighbours++;
			}
			if (i < matrix.length - 1 && (matrix[i + 1][j] == true)) {
				livingNeighbours++;
			}
			if (j < matrix.length - 1 && (matrix[i][j + 1] == true)) {
				livingNeighbours++;
			}
			if ((i > 0 && j > 0) && (matrix[i - 1][j - 1] == true)) {
				livingNeighbours++;
			}
			if ((i < matrix.length - 1 && j < matrix.length - 1) && (matrix[i + 1][j + 1] == true)) {
				livingNeighbours++;
			}
			if ((i > 0 && j < matrix.length - 1) && (matrix[i - 1][j + 1] == true)) {
				livingNeighbours++;
			}
			if ((i < matrix.length - 1 && j > 0) && (matrix[i + 1][j - 1] == true)) {
				livingNeighbours++;
			}

		}
		return livingNeighbours;
	}

	public static Pair<Integer, Integer> parseLine(String line) {
		String[] coordinates = line.split("\\s+");
		int L = Integer.parseInt(coordinates[0]);
		int R = Integer.parseInt(coordinates[1]);
		return new Pair<Integer, Integer>(L, R);
	}
}
