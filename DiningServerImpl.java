
/**
 * DiningServer.java
 *
 * This class contains the methods called by the  philosophers.
 *
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl implements DiningServer {
	// Your code here

	//the different states that the philosophers can be in
	private enum states {
		THINKING, EATING, HUNGY
	}

	//array of 5 represents each philosopher and their current state
	private states[] philState = new states[5];

	public DiningServerImpl() {
		//puts all the philosophers into a thinking state
		for (int i = 0; i < 5; i++) {
			philState[i] = states.THINKING;
		}
	}
	
	public void takeForks(int philNumber) {
		
	}

	public void returnForks(int philNumber) {

	}
}
