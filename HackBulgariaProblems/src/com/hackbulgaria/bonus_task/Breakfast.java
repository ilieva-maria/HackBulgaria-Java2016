package com.hackbulgaria.bonus_task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Breakfast {
	
	private static int numberOfTests = 0;
	private static int numberOfPancakes = 0;
	private static List<Integer> cookingTimes = new ArrayList<>();
	
	public static void main(String[] args) {
		System.out.println(solve());
	}
	
	private static void initialize() {
		Scanner scanner = new Scanner(System.in);
		numberOfTests = scanner.nextInt();
		for (int i = 0; i < numberOfTests; i++) {
			do {
				numberOfPancakes = scanner.nextInt();
				if (numberOfPancakes < 0 || numberOfPancakes > 1015) {
					System.out.println(
							"Number of pancakes must be between 0 and 1015. Number of pancakes: " + numberOfPancakes);
				}
			} while (numberOfPancakes < 0 || numberOfPancakes > 1015);

			int numberOfPans = 0;
			do {
				numberOfPans = scanner.nextInt();
				if (numberOfPans < 0 || numberOfPans > 20) {
					System.out.println("Number of pans must be between 0 and 20. Number of pans: " + numberOfPans);
				}
			} while (numberOfPans < 0 || numberOfPans > 20);

			for (int j = 0; j < numberOfPans; j++) {
				int cookingTime = 0;
				do {
					cookingTime = scanner.nextInt();
					if (cookingTime < 0 || cookingTime > 500) {
						System.out.println("Cooking time must be betweem 0 and 500. Cooking time: " + cookingTime);
					}
				} while (cookingTime < 0 || cookingTime > 500);
				cookingTimes.add(cookingTime);
			}
		}

		scanner.close();
	}

	private static int solve() {
		initialize();
		return calculateMinimumTime(numberOfTests, numberOfPancakes, cookingTimes);
	}
	
	private static int calculateMinimumTime(int numberOfTests, int numberOfPancakes, List<Integer> cookingTimes) {
		int low = 0;
		int high = Collections.max(cookingTimes) * numberOfPancakes;
		while (low < high) {
			int mid = low + ((high - low) / 2);
			
			if(canPreparePancakes(numberOfTests, numberOfPancakes, cookingTimes, mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
				
		return low;
	}

	private static boolean canPreparePancakes(int numberOfTests, int numberOfPancakes, List<Integer> cookingTimes, int availableTime) {
		int prepared = 0;
		for(Integer time : cookingTimes) {
			prepared += availableTime / time;
		}
		return prepared >= numberOfPancakes;
	}

}
