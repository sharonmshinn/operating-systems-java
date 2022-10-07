

/**
 * This class loops through from 0 to 99, waits a random amount of seconds from 
 * 1-3 and adds the loop value into a shared global buffer where the Consumer
 * will read from and output to a text file. 
 * This class extends Thread to run concurrently with the Consumer thread.
 * 
 * ICS 462
 * Assignment 3
 * September 8th, 2022
 * 
 * @author Sharon Shin
 *
 */

public class Producer extends Thread{
	
	/**
	 * This method runs when the Driver calls the start method for the Producer.
	 * This method write out to the global buffer loop int values after waiting 
	 * a random second count from 1-3.If the shared buffer is full, it will 
	 * sleep for a second until it isn't. This method also updates the shared
	 * lastAdded value. 
	 */
	public void run() {
		//Loop through 0-99
		for(int i=0; i<100; i++) {
			//Sleep for a random second count from 1-3
			try {
				Thread.sleep((int) (Math.round(Math.random() * 2)* 1000) + 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//If shared buffer is full, wait 1 second until it is not
			while(Driver.list.isFull()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//Add loop count to shared buffer and update lastAdded val to loop count
			try {
				Driver.list.add(i);
				Driver.lastAdded = i;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		//When done, add -1 to the shared global buffer and lastAdded
		try {
			Driver.list.add(-1);
			Driver.lastAdded = -1;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
