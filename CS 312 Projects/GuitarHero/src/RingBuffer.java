/**
* CS312 Assignment 12
*
* On my honor, Thi Le, this programming assignment is my own work
* and I have not provided this code to any other student.
*
* Student name: Thi Le
* UTEID: TEL455
* email address: thi_le@utexas.edu
* Section 5 digit ID: 52827
* Grader name: Donghyuk
* Number of slip days used on this assignment:0
*
*/

//Part of GuitarHero. This will model a ring buffer (or buffered queue) and 
//allow access to the most recently added item.
public class RingBuffer
{
	private double[] ringBufferArr;
	private int capacity;
	private int item;
	private int first;
	private int last;
	
//The constructor for RingBuffer with a capacity parameter. The constructor will create and 
//set the size of the array.
	public RingBuffer(int capacity)
	{
		ringBufferArr = new double[capacity];
		this.capacity = capacity;
		item = 0;
		first = 0;
		last = 0;
	}
	
//This method will return the number of items in the ring buffer.
	public int size()
	{
		return item;
	}
	
//If the buffer is empty, this method will return a boolean value.
	public boolean isEmpty()
	{
		return (size() <= 0);
	}
	
//If the buffer is full, this method will return a boolean value.
	public boolean isFull()
	{
		return (size() >= capacity);
	}
	
//This method adds an item to the end of the buffer. 
//If the buffer is full, the method will throw an exception.
	public void enqueue(double x)
	{
		if(isFull())
		{
			throw new RuntimeException("The buffer is full!");
		}	
		ringBufferArr[last] = x;
		last = (last + 1) % capacity;
		item++;

	}
	
//This method deletes and returns the item from the front of the buffer. 
//If the buffer is empty, the method will throw an exception.
	public double dequeue()
	{
		if(isEmpty())
		{
			throw new RuntimeException("The buffer is empty!");
		}	
		double value = ringBufferArr[first];
		ringBufferArr[first] = 0;
		first = (first + 1) % capacity;
		item--;
		return value;
	}
	
//This method returns the item from the front of the buffer.
	public double peek()
	{
		return ringBufferArr[first];
	}
	
//This method returns a string in the form of "[front, next, next, last]".
	public String toString()
	{
		String value = "[";
		for(int i = 0; i < size(); i++)
		{
			int wrap = (first + i) % capacity;
			if(i == 0)
			{
				value += ringBufferArr[wrap];
			}
			else
			{
				value += ", " + ringBufferArr[wrap];
			}
		}
		value += "]";
		return value;
	}
}