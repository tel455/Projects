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

public class Hippo extends Critter 
{
	private int requiredEatNum;
    private Random rand = new Random();
    private int r;
    private int moves = 0;
	
    public Hippo(int hunger)
    {
    	requiredEatNum = hunger;
    }
    
	public Attack fight(String opponent) 
	{
		if (requiredEatNum > 0) 
		{
			return Attack.SCRATCH;
		}
		else 
		{
			return Attack.POUNCE;
		}
	}
	
	public Color getColor() 
	{
		if (requiredEatNum > 0) 
		{
			return Color.GRAY;
		}
		else 
		{
			return Color.WHITE;
		}
	}
	
	public Direction getMove()
	{
        moves++;
        if(moves % 5 == 0) 
        {
            moves = 0;
            r = rand.nextInt(4);
        }
        if (r == 0)
        {
            return Direction.NORTH;
        } 
        else if (r == 1) 
        {
            return Direction.EAST;
        } 
        else if (r == 2) 
        {
            return Direction.SOUTH;
        } 
        else
        {
            return Direction.WEST;
        }
    }

	public boolean eat() 
	{
		if (requiredEatNum > 0) 
		{
			requiredEatNum--;
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public String toString() 
	{
		return "" + requiredEatNum;
	}
}
