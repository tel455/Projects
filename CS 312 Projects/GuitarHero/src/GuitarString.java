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

import java.util.Random;

//Part of GuitarHero. This will mimic a vibrating guitar string. 
public class GuitarString
{
	private Random r = new Random();
	private RingBuffer buff;
	private int capacity = 0;
	private int time = 0;
	
//This constructor will create a guitar string of the given frequency, 
//using a sampling rate of 44,100. It will create a ring buffer of the desired capacity N
//and initializes it to represent a guitar string at rest by enqueuing N zeros. 
	public GuitarString(double frequency)
	{
		capacity = (int) (44100/frequency);
		buff = new RingBuffer(capacity);
		for(int i = 0; i < capacity; i++)
		{
			buff.enqueue(0);
		}
	}
	
//This constructor will create a guitar string whose size and initial values are 
//given by the array. It will create a ring buffer with a capacity equal to the size of the 
//array, and initializes the contents of the buffer to the values in the array.
	public GuitarString(double[] init)
	{
		buff = new RingBuffer(init.length);   
		for(double d : init)
		{
            buff.enqueue(d);
		}
	}
	
//This method will set the items in the buffer to white noise.
//It will replace the N items in the ring buffer with N random values between -0.5 and +0.5. 
	public void pluck()
	{
		while(!buff.isEmpty())
		{
			buff.dequeue();
		}
		for(int i = 0; i < capacity; i++)
		{
			buff.enqueue(r.nextFloat() - 0.5);
		}
		
	}
	
//This method advances the simulation one time step. It will apply the Karplus-Strong update: 
//delete the sample at the front of the ring buffer and add to the end of the ring buffer the 
//average of the first two samples, multiplied by the energy decay factor (0.994). 
	public void tic()
	{
		double front = buff.dequeue();
		buff.enqueue(((front + buff.peek()) * 0.5) * 0.994); 
		time++;
	}
	
//This method will return the current sample, or value or the item, at the front of the buffer.
	public double sample()
	{
		return buff.peek();
	}
	
//This method returns the total number of times tic() was called.
	public int time()
	{
		return time;
	}
}