package com.hackbulgaria.dining_philosophers_attempt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
	private final int id;
	
	public Fork(int id) {
		this.id = id;
	}
	
    Lock up = new ReentrantLock();
    public boolean pickUp(Philosopher philosopher) throws InterruptedException {
      if (up.tryLock(10, TimeUnit.MILLISECONDS)) {
    	System.out.println(philosopher + " picked up " + this);
        return true;
      }
      return false;
    }

    public void putDown(Philosopher philosopher) {
      up.unlock();
      System.out.println(philosopher + " put down " + this);
    }

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "fork:" + id;
	}
	
}
