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

public class Bird extends Critter 
{
    private int move = -1;

	public Attack fight(String opponent) 
	{
		if (opponent == "%")
		{
			return Attack.ROAR;
		}
		else
			return Attack.POUNCE;
	}
	
	public Color getColor() 
	{
		return Color.BLUE;
	}
	
	public Direction getMove()
	{ 
        move = (move + 1) % 12;
        Direction dir;
        if (move < 3 || move == -1)
        {
            dir = Direction.NORTH;
        } 
        else if (move >= 3 && move < 6)
        {
            dir = Direction.EAST;
        } 
        else if (move >= 6 && move < 9)
        {
            dir = Direction.SOUTH;
        } 
        else 
        {
            dir = Direction.WEST;
        }
        return dir;
    }

	
	public String toString()
	{
        String symbol;
        if(move < 3 || move == -1 )
        {
            symbol = "^";
        } 
        else if (move >= 3 && move < 6)
        {
            symbol = ">";
        } 
        else if (move >= 6 && move < 9)
        {
            symbol = "V";
        } 
        else 
        {
            symbol = "<";
        }
        return symbol;
    }

		
	public boolean eat() 
	{
		return false;
	}
}
