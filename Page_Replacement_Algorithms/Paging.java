
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * This class handles three different types of paging, FIFO, LRU, and Optimal 
 * based on the input parameters of a list of page integers and the integer
 * value of how many frames will be used for memory storage. 
 * This class also has helper methods to convert a list to a string variable,
 * generate a randomized 20-digit page, and finding the highest index for 
 * the Optimal Page Replacement algorithm.
 * 
 * @author Sharon Shin
 * October 1st, 2022
 * Assignment 5
 *
 */

public class Paging {
	
	/**
	 * This method takes in a list of page integers and an integer value
	 * of frames that represent how many frames will be used for memory storage.
	 * This method uses a Queue data structure to remove values from the 
	 * frames in a FIFO structure if there is a page fault. It returns the
	 * total amount of page faults once all calculations are done.
	 * 
	 * @param page list
	 * @param frame size
	 * @return total page fault amount
	 */
	public static int calculateFIFO(int[] page, int frame) {
		//Page faults to return at the end
		int pageFaults = 0;
		//Queue data structure to keep track of what values to add and remove
		Queue<Integer> frames = new LinkedList<Integer>();
		//Loop over each page integer
		for(int i=0; i<page.length; i++) {
			//If the frames are not full
			if(frames.size() < frame) {
				//If the frames do not contain the page integer (page fault)
				if(!frames.contains(page[i])) {
					//Increment page faults and enqueue page digit
					pageFaults += 1;
					frames.add(page[i]);
				} 
			//If frames are full
			} else {
				//If frames do not contain the page integer (page fault)
				if(!frames.contains(page[i])) {
					//Dequeue head of frames/queue and enqueue page digit
					frames.remove();
					frames.add(page[i]);
					//Increment page faults
					pageFaults += 1;
				}
			}
		}
		//Return final amount of page faults
		return pageFaults;
	}
	
	/**
	 * This method takes in a list of page integers and an integer value
	 * of frames that represent how many frames will be used for memory storage.
	 * This algorithm adds page digits to its frames if they do not already
	 * exist within the frames and determines which existing frame value
	 * to remove by removing the least recently used value in the page 
	 * starting at the index of the page digit being inserted into the frame.
	 * 
	 * @param page list
	 * @param frame size
	 * @return final count of page faults
	 */
	public static int calculateLRU(int[] page, int frame) {
		//Page faults to return at the end
		int pageFaults = 0;
		//Array to keep track of page digits to add and remove
		ArrayList<Integer> frames = new ArrayList<Integer>();
		//Loop over each page integer
		for(int i=0; i<page.length; i++) {
			//If frames are not full
			if(frames.size() < frame) {
				//If frames do contain the page digit yet
				if(!frames.contains(page[i])) {
					//Increment page faults and add page digit to frames
					pageFaults += 1;
					frames.add(page[i]);
				}
			//If frames are full
			} else {
				//If frames do not contain the page digit yet
				if(!frames.contains(page[i])) {
					//Index in frames to remove
					int indexToRemove = 0;
					//Array to keep track of which values in frames we have checked
					ArrayList<Integer> checked = new ArrayList<Integer>();
					/*
					 * Start from page digit we are trying to add to frame and 
					 * go backwards to index 0
					 */
					for(int k=i; k>=0; k--) {
						//If frames contains page digit, but checked does not contain it
						if(frames.contains(page[k]) && !checked.contains(page[k])) {
							//Set index to remove as the index of the page digit in frames
							indexToRemove = frames.indexOf(page[k]);
							//Add page digit to checked array
							checked.add(page[k]);
						}
						/*
						 * If the size of the checked array equals the size of 
						 * our frames, we are done and found the index to replace
						 */
						if(checked.size() == frame) {
							break;
						}
					}
					/*
					 * Increment page faults and replace frames at index to 
					 * remove with new page value
					 */
					pageFaults ++;
					frames.set(indexToRemove, page[i]);
				}
			}
		}
		//Return final total of page faults
		return pageFaults;
	}
	
	/**
	 * This method takes in a list of page integers and an integer value
	 * of frames that represent how many frames will be used for memory storage.
	 * This algorithm adds page digits to its frames if they do not already
	 * exist within the frames and determines which existing frame value
	 * to remove by removing the last occurring value in the page, or the
	 * value that does not occur again and inserting the new page value into
	 * the last occurring value's index.
	 * 
	 * @param page list
	 * @param frame size
	 * @return final count of page faults
	 */
	public static int calculateOptimal(int[] page, int frame) {
		//Page fault total to return at the end
		int pageFaults = 0;
		//Array to keep track of page digits to add and remove
		ArrayList<Integer> frames = new ArrayList<Integer>();
		//Loop over each page integer
		for(int i=0; i<page.length; i++) {
			//If frames are not full
			if(frames.size() < frame) {
				//If frames do not contain the page value
				if(!frames.contains(page[i])) {
					//Increment page faults and add page value to frames
					pageFaults += 1;
					frames.add(page[i]);
				} 
			//If frames are full
			} else {
				//If frames do not contain the page value
				if(!frames.contains(page[i])) {
					//Array to keep track of last index occurrence of frame value
					ArrayList<Integer> lastIndex = new ArrayList<Integer>();
					//Iterate over frames
					for(int k=0; k<frame; k++) {
						//Boolean flag to see if value exists in the rest of the page
						boolean exists = false;
						/*
						 * Iterate over page starting from the end to the page 
						 * value index we are trying to add to the frames
						 */
						for(int y=page.length-1; y>i; y--) {
							//If frame value is equal to page value
							if(frames.get(k).equals(page[y])) {
								//Add the index of the page to last index array
								lastIndex.add(y);
								//Set flag to true
								exists = true;
								//Break loop to move onto next page value
								break;
							} 
						}
						/*
						 * If flag hasn't been set to true, frame value doesn't 
						 * exist in rest of page
						 */
						if(exists == false) {
							//Add MAX_VALUE to last index array
							lastIndex.add(Integer.MAX_VALUE);
						}
					}
					//Increment page faults
					pageFaults ++;
					//Call helper method to find the highest value index
					int index = findHighestIndex(lastIndex);
					//Replace highest value index with page value
					frames.set(index, page[i]);
				}
			}
		}
		//Return total page faults
		return pageFaults;
	}
	
	/**
	 * This method is a helper method for the optimal page replacement algorithm.
	 * This method takes in an array of index values and returns the highest index.
	 * @param indexes array
	 * @return highest index value
	 */
	public static int findHighestIndex(ArrayList<Integer> indexes) {
		int highest = 0;
		int index = 0;
		for(int i=0; i<indexes.size(); i++) {
			if(indexes.get(i) > highest) {
				highest = indexes.get(i);
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * This helper method is used by the Driver class to create a randomized
	 * 20-digit page.
	 * @return randomized 20-digit page
	 */
	public static int[] generatePage() {
		int[] page = new int[20];
		for(int i=0; i<page.length; i++) {
			Random rand = new Random();
			page[i] = rand.nextInt(0, 10);
		}
		return page;
	}

	/**
	 * This helper method is used by the Driver class to convert the page list
	 * to String representation
	 * @param pages list
	 * @return String representation of pages
	 */
	public static String listToString(int[] pages) {
		String output = "";
		for(int i=0; i<pages.length-1; i++) {
			output += pages[i] + ",";
		}
		output += pages[pages.length-1];
		return output;
	}

}
