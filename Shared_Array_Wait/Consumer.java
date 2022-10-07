

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class reads values from a shared global circular buffer and removes 
 * values in the order they were added to the global circular buffer, 
 * outputting it to a text file called output.txt.
 * This class extends Thread to run concurrently with the Producer thread.
 * 
 * ICS 462
 * Assignment 3
 * September 8th, 2022
 * 
 * @author Sharon Shin
 *
 */

public class Consumer extends Thread{
	//Value to keep track of which value was just read from the shared buffer
	public int lastRead;
	//Filewriter object to write to output.txt
	FileWriter writer;
	
	/**
	 * This method runs when the Driver calls the start method for the Consumer.
	 * This method write out to an output.txt file the int values that the 
	 * Consumer object reads from the shared buffer. While the shared buffer
	 * is empty, it will sleep and output that it is waiting. 
	 */
	public void run() {
		//Creating output file and writing the necessary information to it
		File outFile = new File("output.txt");
		try {
			writer = new FileWriter(outFile);
			writer.write("Sharon Shin\n");
			writer.write("ICS 462 Assignment #3\n\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//Wait a random second count between 1-3 until the value read is -1
		while (lastRead != -1) {
			try {
				Thread.sleep((int) (Math.round(Math.random() * 2)* 1000) + 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			/* If buffer is empty or there hasn't been a new value added to 
			 * buffer yet, wait for a second and print that the Consumer is 
			 * waiting.
			 */
			while(Driver.list.isEmpty() || lastRead == Driver.lastAdded) {
				try {
					writer.write("Consumer Waiting...\n");
					Thread.sleep(1000);
				} catch (InterruptedException | IOException e) {
					e.printStackTrace();
				}
			}
			/*
			 * Get the head of the buffer and output it to the output text
			 * file. If the value is -1, we stop.
			 */
			try {
				lastRead = Driver.list.get();
				if(lastRead == -1) {
					continue;
				}
				writer.write(lastRead + "\n");
				Driver.lastRemoved = lastRead;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//Write the consumer is done when the value we read is -1
		try {
			writer.write("Consumer done.");
			//Flush and close to ensure all output has been outputted.
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
