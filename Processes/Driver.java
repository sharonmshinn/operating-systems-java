
/**
 * This class acts as the play button when running the programs.
 * It has a global var that is initialized to 100 and will get overwritten 
 * by the Producer, then read by the Consumer. 
 * 
 * ICS 462
 * Assignment 2
 * August 24th, 2022
 * 
 * @author Sharon Shin
 */

public class Driver {
	//global var that the producer will write to and consumer will read from
	public static int globalVariable = 100;
	
	/**
	 * Main method that initializes Producer and Consumer objects then
	 * runs their threads.
	 * @param args
	 */
	public static void main(String[] args) {
		Producer producer = new Producer();
		Consumer consumer = new Consumer();
		producer.start();
		consumer.start();
	}
	
}
