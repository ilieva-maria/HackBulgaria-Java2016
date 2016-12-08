package thursday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Pancakes {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numberOfTests = scanner.nextInt();
		int numberOfPancakes = 0;
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

			List<Integer> cookingTimes = new ArrayList<>();
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
			Collections.sort(cookingTimes);
			System.out.println(makePancakes(numberOfPans, cookingTimes, numberOfPancakes));
		}

		scanner.close();

	}

	public static int makePancakes(int numberOfPans, List<Integer> cookingTimes, int numberOfPancakes) {
		if (cookingTimes.size() == 0 || numberOfPancakes == 0) {
			return 0;
		}
		Set<List<Integer>> partitions = SumPartition.partition(numberOfPancakes);

		Collections.sort(cookingTimes);
		int minimumCookingTime = Integer.MAX_VALUE;
		for (List<Integer> partition : partitions) {
			if (partition.size() <= cookingTimes.size()) {
				int cookingTime = calculateCookingTime(cookingTimes, partition);
				if (cookingTime < minimumCookingTime) {
					minimumCookingTime = cookingTime;
				}
			}

		}
		return minimumCookingTime;

	}

	public static int calculateCookingTime(List<Integer> cookingTimes, List<Integer> partition) {
		int elapsedTime = 0;

		for (int i = 0; i < partition.size(); i++) {
			int currentTime = partition.get(i) * cookingTimes.get(i);
			if (currentTime > elapsedTime) {
				elapsedTime = currentTime;
			}
		}
		return elapsedTime;

	}

}
