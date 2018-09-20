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

public class Vulture extends Bird 
{
	private boolean hungry = true;
	
	public boolean eat() 
	{
		if (hungry) 
		{
			hungry = false;
			return true;
		}
		return false;
	}
	
	public Attack fight(String opponent) 
	{
		hungry = true;
		if (opponent == "%") 
		{
			return Attack.ROAR;
		}
		else 
		{
			return Attack.POUNCE;
		}
	}
	
	public Color getColor() 
	{
		return Color.BLACK;
	}
}
