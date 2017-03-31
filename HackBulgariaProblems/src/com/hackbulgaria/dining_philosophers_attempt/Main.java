package com.hackbulgaria.dining_philosophers_attempt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	private static final int SIMULATION_MILLIS = 1000 * 10;

	public static void main(String[] args) throws InterruptedException {

		DiningTable diningTable = new DiningTable();
		diningTable.initialize();
		ExecutorService executorService = null;
		try {
			executorService = Executors.newFixedThreadPool(5);
			for (Philosopher philosopher : diningTable.getPhilosophers()) {
				executorService.execute(philosopher);
			}

			Thread.sleep(SIMULATION_MILLIS);
			for (Philosopher philosopher : diningTable.getPhilosophers()) {
				philosopher.isFull = true;
			}

		} finally {
			executorService.shutdown();
		}
	}

}
