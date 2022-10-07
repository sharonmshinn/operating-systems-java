
/**
 * This class runs through a loop, sleeps a random number between 1 and 3 
 * seconds and then adds the loop count to the global variable in 
 * the Driver class, where the Consumer will read it and add it to a sum.
 * 
 * ICS 462
 * Assignment 2
 * August 24th, 2022
 * 
 * @author Sharon Shin
 */

public class Producer extends Thread{
	
	/**
	 * This method will be kicked off when we call start from the Driver for 
	 * the producer. It writes the loop count to the global variables.
	 */
	public void run() {
			for(int i=0; i<=4; i++) {
				//Sleeping a random second amount between 1 and 3.
				try {
					Thread.sleep((int) (Math.round(Math.random() * 2)* 1000) + 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//Write loop count to Driver global variable.
				Driver.globalVariable = i;
			}
		}

}
