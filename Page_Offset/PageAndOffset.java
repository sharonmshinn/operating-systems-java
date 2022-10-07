

/**
 * This class takes care of the calculations of the test data and returns it as
 * a list per address input.
 * 
 * ICS 462
 * Assignment 4
 * September 13th, 2022
 * 
 * @author Sharon Shin
 *
 */

public class PageAndOffset {
	public static final int KB = 1024;
	public static final int MEMORY_SIZE = KB*4;
	
	/**
	 * This method calculates the page and offset based on the address
	 * input and returns the page and offset in a list of ints.
	 * @param address
	 * @return list of page and offset vals
	 */
	public int[] calculate(int address) {
		int[] nums = new int[2];
		nums[0] = address/MEMORY_SIZE;
		nums[1] = address%MEMORY_SIZE;
		return nums;
	}

}
