/**
* CS312 Assignment 11.
*
* On <MY|OUR> honor, Thi Le and <NAME2), this programming assignment is <MY|OUR> own work
* and <I|WE> have not provided this code to any other student.
*
* Student 1 name: Thi Le
* UTEID: TEL455
* email address: thi_le@utexas.edu
* Section 5 digit ID: 52827
* Grader name: Donghyuk
* Number of slip days used on this assignment: 1
*
* Student 2 name:
* UTEID:
* email address:
* 
*/

import java.awt.*;
import java.util.Random;

public class Ant extends Critter 
{
	Random r = new Random();
	private boolean walkSouth = r.nextBoolean();
	private int moves = 0;
	
	public Ant(boolean walkSouth)
	{
		this.walkSouth = walkSouth;
	}
	
	public Attack fight(String opponent) 
	{
		return Attack.SCRATCH;
	}
	
	public Color getColor() 
	{
		return Color.RED;
	}
	
	public String toString() 
	{
		return "%";
	}
	
	public boolean eat() 
	{
		return true;
	}
	
	public Direction getMove() 
	{
		moves++;
		if (walkSouth) 
		{
			if (moves % 2 == 0) 
			{
				return Direction.SOUTH;
			} else 
			{
				return Direction.EAST;
			}
		} 
		else 
		{
			if (moves % 2 == 0) 
			{
				return Direction.NORTH;
			} 
			else 
			{
				return Direction.EAST;
			}
		}
	}
}
