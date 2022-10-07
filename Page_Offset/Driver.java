
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class acts as the start button when the project is ran. It has a static
 * final list of test data to do its calculations on and writes the results to a 
 * text file called "output.txt".
 * 
 * ICS 462
 * Assignment 4
 * September 13th, 2022
 * 
 * @author Sharon Shin
 *
 */
public class Driver {
	//Static final test int data
	public static final int[] TEST_DATA = {19986, 347892, 5978};
	
	/**
	 * Automatically runs when running the project.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		//Create file to write output information to
		File outFile = new File("output.txt");
		FileWriter writer = new FileWriter(outFile);
		PageAndOffset pageAndOffset = new PageAndOffset();
		//Write necessary information to output file
		writer.write("Sharon Shin\n");
		writer.write("Asssignment #4\n");
		writer.write("September 13th, 2022\n\n");
		
		//For all of the test data, do the proper calculations and write to output
		for(int i=0; i<TEST_DATA.length; i++) {
			writer.write("The address " + TEST_DATA[i] + " is in:\n");
			int[] nums = pageAndOffset.calculate(TEST_DATA[i]);
			writer.write("\tPage Number = " + nums[0] + "\n");
			writer.write("\tOffset = " + nums[1] + "\n\n");
		}
		//Flush and close writer
		writer.flush();
		writer.close();
		
	}
}
