
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class acts as the play button when the project is ran. This class 
 * initializes two page reference lists and utilizes a helper method
 * within the Paging class to create a randomized 20-digit page to do testing.
 * It runs through three page replacement algorithms (FIFO, LRU, and Optimal) 
 * and outputs to a file called output.txt for frames 1-7 per page.
 * 
 * @author Sharon Shin
 * October 1st, 2022
 * Assignment 5
 *
 */

public class Driver {
	//Two given page reference lists to test with
	public static final int[] PAGE_REFERENCE1 = {0,7,0,1,2,0,8,9,0,3,0,4,5,6,7,0,8,9,1,2};
	public static final int[] PAGE_REFERENCE2 = {7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1};
	
	/**
	 * This method runs automatically when the project is ran. 
	 * It creates a randomized 20-digit page and uses the two given page
	 * reference lists to perform FIFO, LRU, and Optimal page replacement
	 * algorithms to and outputs the results to an output file called 
	 * output.txt.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		File outFile = new File("output.txt");
		FileWriter writer = new FileWriter(outFile);
		int[] pages = Paging.generatePage();
		writer.write("Sharon Shin\nOctober 1st, 2022\nAssignment 5\n");
		for(int i=1; i<8; i++) {
			writer.write("\n\nFor " + i + " page frame(s), and using string page "
					+ "reference string: " + Paging.listToString(pages) + "\n");
			writer.write("\n\n\tFIFO had " + Paging.calculateFIFO(pages, i) + " page faults.\n");
			writer.write("\n\n\tLRU had " + Paging.calculateLRU(pages, i) + " page faults.\n");
			writer.write("\n\n\tOptimal had " + Paging.calculateOptimal(pages, i) + " page faults.\n");
		}
		for(int i=1; i<8; i++) {
			writer.write("\n\nFor " + i + " page frame(s), and using string page "
					+ "reference string: " + Paging.listToString(PAGE_REFERENCE1) + "\n");
			writer.write("\n\n\tFIFO had " + Paging.calculateFIFO(PAGE_REFERENCE1, i) + " page faults.\n");
			writer.write("\n\n\tLRU had " + Paging.calculateLRU(PAGE_REFERENCE1, i) + " page faults.\n");
			writer.write("\n\n\tOptimal had " + Paging.calculateOptimal(PAGE_REFERENCE1, i) + " page faults.\n");
		}
		for(int i=1; i<8; i++) {
			writer.write("\n\nFor " + i + " page frame(s), and using string page "
					+ "reference string: " + Paging.listToString(PAGE_REFERENCE2) + "\n");
			writer.write("\n\n\tFIFO had " + Paging.calculateFIFO(PAGE_REFERENCE2, i) + " page faults.\n");
			writer.write("\n\n\tLRU had " + Paging.calculateLRU(PAGE_REFERENCE2, i) + " page faults.\n");
			writer.write("\n\n\tOptimal had " + Paging.calculateOptimal(PAGE_REFERENCE2, i) + " page faults.\n");
		}
		writer.flush();
		writer.close();
	}

}
