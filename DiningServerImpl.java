
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

	//the different states that the philosophers can be in
	private enum states {
		THINKING, EATING, HUNGY
	}
	private Condition[] self = new Condition[5];

	//array of 5 represents each philosopher and their current state
	private states[] philState = new states[5];

	public DiningServerImpl() {
		//puts all the philosophers into a thinking state
		for (int i = 0; i < 5; i++) {
			philState[i] = states.THINKING;
		}
	}
	
	//lets given philosopher start eating if it passes test conditions
	public void takeForks(int philNumber) {
		philState[philNumber] = states.HUNGY;
		//test to see if philosopher's neighbors are eating
		test(philNumber);
		if (philState[philNumber] != states.EATING)
			self[philNumber].wait(); // bruh
	}

	//puts philosopher back into a thinking state, tests other neighboring philosophers
	public void returnForks(int philNumber) {
		philState[philNumber] = states.THINKING;
		// test neighboring philosophers and lets them eat if conditions are met
		test((philNumber + 1) % 5); // left
		test((philNumber + 4) % 5); // right
	}

	//checks neighboring philosophers' states and lets the current philospher start eating
	public void test(int philNumber) {
		if (philState[(philNumber + 1) % 5] != states.EATING && // Checks the left philosopher
				philState[(philNumber + 4) % 5] != states.EATING && // Checks the right philosopher 
				philState[philNumber] == states.HUNGY) {
					// philosopher starts eating, yummy
					philState[philNumber] = states.EATING;
					self[philNumber].signal(); //idk wtf the self and signal stuff it for TODO: figure dis shit out
				}
	}
}
