/**
 * DiningPhilosophers.java
 *
 * This program starts the dining philosophers problem.
 *
 */

public class DiningPhilosophers {

  public static void main(String args[]) {
    for (int i = 0; i < 5; i++) {
      Philosopher philosopher = new Philosopher(i);
      Thread p1 = new Thread(philosopher);
      p1.start();
    }
  }
}
