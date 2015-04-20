package all;

import java.util.NoSuchElementException;

class Item {
	String name;
	int next;
	public Item(String name, int next) {
		this.name = name;
		this.next = next;
	}
	public String toString() {
		return "(" + name + "," + next + ")";
	}
}

public class ArrayLL {
	
	private Item[] all;
	private int numItems;
	private int front;
	private int[] avail;
	private int numAvail;
	
        // Constructor, initializes all data fields to represent an empty array
        // linked list of length maxItems
	public ArrayLL(int maxItems) {
		this.all = new Item[maxItems];
		this.numItems = 0;
		this.front = 0;
		this.avail = new int[maxItems];
		for (int i = 0; i < maxItems; i++)
		{
			avail[i] = maxItems - 1 - i;
		}
		this.numAvail = maxItems;
	}

        // Adds a name to the front of this array linked list, in worst case O(1) time
        // Throws an ArrayIndexOutOfBoundsException if the array is full
	public void addFront(String name) 
	throws ArrayIndexOutOfBoundsException {
		if (numItems == all.length)
		{
			throw new ArrayIndexOutOfBoundsException("Array is FULL");
		}
		if (numItems == 0)
		{
			all[0] = new Item(name, -1);
			front = 0;
			numAvail--;
			numItems++;
			return;
		}
		all[avail[numAvail-1]] = new Item(name, front);
		front = avail[numAvail-1];
		numAvail--;
		numItems++;
	}

        // Deletes the name that is at the front this array linked list, in worst case O(1) time
        // Throws a NoSuchElementException if the list is empty
	public String deleteFront() 
	throws NoSuchElementException {
		if(numItems == 0)
		{
			throw new NoSuchElementException("List is EMPTY");
		}
		String Deleted = all[front].name;
		avail[numAvail] = front;
		front = all[front].next;
		numItems--;
		numAvail++;
		return Deleted;

	}

        // Deletes the given name from this array linked list, 
        // Throws a NoSuchElementException if the name is not in the list
        // Note: If there are n items in the linked list, then this method must run in
        // worst case O(n) time, i.e. time does not depend on the length of the all array.
        // Also, avail array should be accessed/updated in O(1) time
	public String delete(String name) 
	throws NoSuchElementException {
		if (numItems == 0) throw new NoSuchElementException();
		if ((numItems == 1) && all[front].name == name){
			numItems--;
			avail[numAvail] = front;
			numAvail--;
			return name;
		}
		else if(numItems == 1)
		{
			throw new NoSuchElementException();
		}
		if (all[front].name.equals(name)){
			avail[numAvail] = front;
			front = all[front].next;
			numAvail++;
			numItems--;
			return name;
		}
		int ptr = front;
		for(int i = 1; i < numItems; i++)
		{
			if((i == numItems-1) && (all[all[ptr].next].name.equals(name)))
			{
				avail[numAvail] = all[ptr].next;
				numAvail++;
				numItems--;
				return name;
			}
			else if(all[all[ptr].next].name.equals(name)){
				avail[numAvail] = all[ptr].next;
				all[ptr].next = all[all[ptr].next].next;
				numAvail++;
				numItems--;
				return name;
			}
			else ptr = all[ptr].next;
		}
		throw new NoSuchElementException();
	}

        // Checks if the given name is in this array linked list
        // Note: If there are n items in the linked list, then this method must run in
        // worst case O(n) time, i.e. time does not depend on the length of the all array.
	public boolean contains(String name) {
		if (numItems == 0) return false;
		if (name.equals(null)) throw new NoSuchElementException();
		int ptr = front;
		for(int i = 0; i < numItems; i++)
		{
			if(all[ptr].name.equals(name)){
			return true;
			}
			ptr = all[ptr].next;
		}
		return false;
	}
	
	
        // Prints the items in this array linked list in sequence from first to last,
        // in worst case O(n) time where n is the number of items in the linked list
        // The list should be printed in a single line, separated by commas
        // Example: earth,mercury,venus
        // Make sure there aren't any extra commas in your output.
        // If the list is empty, you may print either nothing, or an empty string
	public void printList() {
		if (numItems == 0){
			System.out.println("List is EMPTY");
			return;
		}
		if (numItems == 1)
		{
			System.out.println(all[front].name);
			return;
		}
		int ptr = front;
		for (int i = 0; i < numItems; i++){ 
			if(all[ptr].next == -1){
				System.out.print(all[ptr].name);
			}
			else{
				System.out.print(all[ptr].name + ",");
				ptr = all[ptr].next;
			}
		}
	}
	
        // Prints all the entries in the main array (including unused spaces)
        // You may fill in this method and use it for debugging
        // This method WILL NOT be graded
	public void printArray() {
		System.out.println("front: " + all[front] + front);
		for (int i = 0; i < all.length; i++){
			if(all[i] == null) System.out.println("null");
			else System.out.println(all[i].name + all[i].next);
				
			}
	}

        // Prints all the entries in the avail array that correspond to
        // available spaces in the main array
        // You may fill in this method and use it for debugging
        // This method WILL NOT be graded
	public void printBlanks() {
		for (int i = 0; i < numAvail; i++)
			System.out.println(all[avail[i]]);
	}
}
