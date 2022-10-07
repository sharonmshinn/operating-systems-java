

/**
 * This class acts as a Circular Buffer, or a list that wraps around from
 * front to back. This Circular Buffer is used by both the Consumer and 
 * Producer by the Driver class. 
 * It does the following functions:
 * 
 * 		Constructor with max capacity parameter
 * 		Adds a value to the circular buffer if it's not full
 * 		Grabs a value and removes it from the circular buffer
 * 		Checks if the circular buffer is full
 * 		Checks if the circular buffer is empty
 * 		Returns the size of the circular buffer
 * 
 * ICS 462
 * Assignment 3
 * September 8th, 2022
 * 
 * @author Sharon Shin
 *
 */

class CircularBuffer {
 
    //Capacity of the buffer. Cannot go above this value.
    private int capacity = 0;
    //Manages size of buffer so we don't have overflow.
    private int size = 0;
    //Head index to keep track of where the front of the list is.
	public int head = 0;
	//Tail index to keep track of where the back of the list is.
	public int tail = -1;
	//Using a list structure to manage the circular buffer.
    private int[] array;
 
    /**
     * This method creates a new CircularBuffer object with a max capacity
     * parameter. 
     * @param capacity int value
     */
    CircularBuffer(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
    }
 
    /**
     * This method takes an int value to insert into the list. The logic 
     * that decides what to do if the list is in the Producer class logic
     * and is thus left out of this method. 
     * @param int value to insert into list
     * @throws Exception
     */
    public void add(int element) throws Exception {
        int index = (tail + 1) % capacity;
        size++;
        array[index] = element;
        tail++;
    }
 
    /**
     * This method takes the element at the front of the list and removes it,
     * returning it. The logic for if the list is empty is in the Consumer class
     * logic and is excluded from this method as a result
     * @return int value that was removed
     * @throws Exception
     */
    public int get() throws Exception {
        int index = head % capacity;
        int element = array[index];
        head++;
        size--;
        return element;
    }
 
    /**
     * This method returns a boolean value saying if the list is empty or not.
     * @return boolean
     */
    public boolean isEmpty() { 
    	return size == 0; }
    
    /**
     * This method returns a boolean value saying if the list is full or not.
     * @return boolean
     */
    public boolean isFull() {
    	return size == capacity;
    }
 
    /**
     * This method returns the size of the list, or the number of values
     * currently in the list.
     * @return int size value
     */
    public int size() { 
    	return size; }
}
