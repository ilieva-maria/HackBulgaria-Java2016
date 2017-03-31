package com.hackbulgaria.gas_stations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GPS {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int tripDistance = scanner.nextInt();
		int tankSize = scanner.nextInt();
		
		int gasStationsCount = scanner.nextInt();
		List<Integer> gasStations = new ArrayList<>();
		
		for (int i = 0; i < gasStationsCount; i++) {
			gasStations.add(scanner.nextInt());
		}
		
		List<Integer> result = getGasStations(tripDistance, tankSize, gasStations);
		
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
		
		scanner.close();
	}
	
	public static void checkInput(int tripDistance, int tankSize, List<Integer> gasStations) {
		if (tripDistance < 0) {
			System.out.println("Where are you going? Your distance is a negative number: " + tripDistance);
		} else if (tripDistance < tankSize) {
			System.out.println("There is no need of refill.");
		} else if (gasStations.isEmpty()) {
			System.out.println("There are no gas stations on the way. " 
								+ "You must think of some other way to refill.");
		} else if (tripDistance > gasStations.get(gasStations.size()-1)) {
			System.out.println("Maybe you should reconsider your tour. You will be out of gas.");
		}
	}

	public static List<Integer> getGasStations(int tripDistance, int tankSize, List<Integer> gasStations) {
		int currentStationDistance = 0;
		int currentTankSize = tankSize;
		int nextStationIndex = 0;
		List<Integer> visited = new LinkedList<>();
		while (currentStationDistance + tankSize < tripDistance && nextStationIndex < gasStations.size()) {
			int nextStationDistance = gasStations.get(nextStationIndex) - currentStationDistance;
			if (nextStationDistance > tankSize) {
				System.out.println("You could not get to the next gas station. You are doomed.");
			} else if (nextStationDistance <= currentTankSize) {
				currentStationDistance += nextStationDistance;
				nextStationIndex++;
				currentTankSize -= nextStationDistance;
			} else {
				currentTankSize = tankSize;
				visited.add(gasStations.get(nextStationIndex - 1));
			}
		}
		int remainDistance = tripDistance - currentStationDistance;
		if (remainDistance >= currentTankSize) {
			currentTankSize = tankSize;
			visited.add(gasStations.get(nextStationIndex - 1));
			System.out.println(visited);
		}

		return visited;

	}

}