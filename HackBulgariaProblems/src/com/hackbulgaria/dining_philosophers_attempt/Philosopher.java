package com.hackbulgaria.dining_philosophers_attempt;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Philosopher implements Runnable {
	
	private final int id;
	private Fork leftFork;
	private Fork rightFork;
	
	volatile boolean isFull = false;
    private Random randomGenerator = ThreadLocalRandom.current();
	
	public Philosopher(int id, Fork leftFork, Fork rightFork) {
		this.id = id;
		this.setLeftFork(leftFork);
		this.setRightFork(rightFork);
	}
	

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Philosopher:" + id;
	}

	public Fork getLeftFork() {
		return leftFork;
	}

	public void setLeftFork(Fork leftFork) {
		this.leftFork = leftFork;
	}

	public Fork getRightFork() {
		return rightFork;
	}

	public void setRightFork(Fork rightFork) {
		this.rightFork = rightFork;
	}
	
//	private synchronized boolean pickUpBothForks() throws InterruptedException {
//		boolean couldEat = false;
//		if(leftFork.pickUp(this) && rightFork.pickUp(this)){
//			eat();
//			couldEat = true;
//		}
//		leftFork.putDown(this);
//		rightFork.putDown(this);
//		return couldEat;
//	}

	@Override
	public void run() {
		try {
			while (!isFull) {
				think();
				if (leftFork.pickUp(this)) {
					if (rightFork.pickUp(this)) {
						eat();
						rightFork.putDown(this);
					}
					leftFork.putDown(this);
				}
//				pickUpBothForks();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void think() throws InterruptedException {
		System.out.println(this.toString() + " is thinking");
		Thread.sleep(randomGenerator.nextInt(1000));
	}

	private void eat() throws InterruptedException {
		System.out.println(this.toString() + " is eating");
		Thread.sleep(randomGenerator.nextInt(1000));
	}

}
