/**
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 *
 */

public class Philosopher implements Runnable {
    private int philosopher_number;

    public Philosopher(int philosopher_number) {
        this.philosopher_number = philosopher_number;
    }

    public void pickup_forks(int philosopher_number) {

    }

    public void return_forks(int philosopher_number) {

    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " from Philosopher " + this.philosopher_number);
            System.out.println();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
