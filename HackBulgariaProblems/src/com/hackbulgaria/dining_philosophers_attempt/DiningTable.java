package com.hackbulgaria.dining_philosophers_attempt;

public class DiningTable {
	private final int PHILOSOPHERS_COUNT = 5;
	private final Philosopher[] philosophers;
	private final Fork[] forks;
	
	public DiningTable() {
		philosophers = new Philosopher[PHILOSOPHERS_COUNT];
		forks = new Fork[PHILOSOPHERS_COUNT];
	}
	
	public void initialize() {
		for (int i = 1; i <= PHILOSOPHERS_COUNT; i++) {
			forks[i-1] = new Fork(i);
		}
		
		for (int i = 1; i <= PHILOSOPHERS_COUNT; i++) {
			philosophers[i-1] = new Philosopher(i, forks[i-1], forks[(i)%PHILOSOPHERS_COUNT]);
		}
		
	    
	}

	public Philosopher[] getPhilosophers() {
		return philosophers;
	}

	@Override
	public String toString() {
		return philosophers.toString();
	}
	
	
}
