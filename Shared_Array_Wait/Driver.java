
/**
 * This class acts as the start button when running this project.
 * It keeps two global int variables to keep track of what has been
 * added last to the shared buffer and what has been removed last.
 * 
 * It creates one Producer object and one Consumer object and starts the 
 * threads.
 * 
 * ICS 462
 * Assignment 3
 * September 8th, 2022
 * 
 * @author Sharon Shin
 *
 */

public class Driver {
	public static CircularBuffer list = new CircularBuffer(5);
	public static int lastAdded = 100;
	public static int lastRemoved = 100;
	
	public static void main(String[] args) {
		Producer producer = new Producer();
		Consumer consumer = new Consumer();
		producer.start();
		consumer.start();
	}
}
