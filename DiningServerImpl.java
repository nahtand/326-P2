
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

	private final Lock lock = new ReentrantLock();

	public DiningServerImpl() {
		//puts all the philosophers /into a thinking state
		//initializes the condition(?)
		for (int i = 0; i < 5; i++) {
			philState[i] = states.THINKING;
			self[i] = lock.newCondition();
		}
	}
	
	//lets given philosopher start eating if it passes test conditions
	public void takeForks(int philNumber) {
		lock.lock();

		philState[philNumber] = states.HUNGY;
		System.out.println("philosopher " + philNumber + " is soooooo hungy");

		//test to see if philosopher's neighbors are eating
		test(philNumber);

		//not eating, goes into wait
		if (philState[philNumber] != states.EATING){
			try {
				self[philNumber].await();
			} catch (InterruptedException e){
				System.err.println("interrupted out of wait");
			}
		}

			
	}

	//puts philosopher back into a thinking state, tests other neighboring philosophers
	public void returnForks(int philNumber) {

		//goes into thinking state
		philState[philNumber] = states.THINKING;
		System.out.println("philosopher " + philNumber + " is thinky, hmmmmmm");

		// test neighboring philosophers and lets them eat if conditions are met
		test((philNumber + 1) % 5); // left
		test((philNumber + 4) % 5); // right
	}

	//checks neighboring philosophers' states and lets the current philospher start eating
	public void test(int philNumber) {
		lock.lock();
		if (philState[(philNumber + 1) % 5] != states.EATING && // Checks the left philosopher
				philState[(philNumber + 4) % 5] != states.EATING && // Checks the right philosopher 
				philState[philNumber] == states.HUNGY) {
					// philosopher starts eating, yummy
					philState[philNumber] = states.EATING;
					System.out.println("philosopher " + philNumber + "eating, yummmmm");

					self[philNumber].signal(); //idk wtf the self and signal stuff it for TODO: figure dis shit out
				}
	}

	public static void main(String args[]) {
		//TODO: figure out threading stuff, get this thing tested asap
			DiningServerImpl server = new DiningServerImpl();
			for (int i = 0; i < 5; i++){
				server.takeForks(i);
			}
			
	}
}

