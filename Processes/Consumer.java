
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class grabs integers from the shared variable in the Driver and adds
 * the sum of all numbers grabbed from the consumer and prints the sum to 
 * an output text file. Both the consumer and the producer sleep a random
 * number of seconds from 1 to 3. 
 * 
 * ICS 462
 * Assignment 2
 * August 24th, 2022
 * 
 * @author Sharon Shin
 */

public class Consumer extends Thread{
	//Variable to keep track of the running sum
	public int sum = 0;
	
	/**
	 * This method will be kicked off when we call start from the Driver for 
	 * the consumer. It outputs necessary information and the total sum.
	 */
	public void run() {
		//Opening a output file in the root directory of this class file
		File outFile = new File("output.txt");
		for(int i=0; i<=4; i++) {
			//Sleeping for a random number of seconds from 1-3 seconds
			try {
				Thread.sleep((int) (Math.round(Math.random() * 2)* 1000) + 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Adding the global variable from Driver to the sum
			sum += Driver.globalVariable;
		}
		try {
			//Writing to the output file
			FileWriter writer = new FileWriter(outFile);
			writer.write("Sharon Shin\n");
			writer.write("ICS 462 Assignment #2\n");
			writer.write("August 24th, 2022\n\n");
			writer.write("The sum is " + sum);
			//Flushing and closing the writer to ensure proper output
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
