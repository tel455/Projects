/*
 *  CS312 Assignment 2.
 *  On my honor, Thi Edison Le, this programming assignment is my own work.
 *
 *  A program to print out the UT Tower in ASCII art form.
 *
 *  Name: Thi Edison Le
 *  email address: thi_le@utexas.edu
 *  UTEID: tel455
 *  Section 5 digit ID: 52827
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment:0
 */

public class Tower 
{	
	// CS312 students, DO NOT ALTER THE FOLLOWING LINE.
	public static final int SIZE = TowerSize.getSize();

    public static void main(String[] args) 
    {
/*
 * This method will call 3 static methods which will create the top, body,
 * and base parts of the UT Tower which will scale according to the SIZE. 
 */
    	drawTop();
    	drawBody();
    	drawBase();
    }

//Methods used to create the top part of the UT Tower begins here.
    public static void drawTop()
    {
/*
 * This method will be called by the main method to draw the 
 * top part of the UT Tower. It will call static methods that 
 * will scale according to SIZE.
 */
    	drawPound();
    	drawLines();
    	drawPound();
    }
    
    public static void drawPound() 
    {
/*
 * This method will draw pound signs on a single line. The amount of
 * signs in each line will depend on the SIZE.
 */
    	drawSpacesTop();
    	for (int i = 1; i <= (2 * SIZE - 1); i++)
    	{
    		System.out.print("#");
    	}
    	System.out.println();
    }
    
    public static void drawLines()
    {
/*
 * This method will draw the amount of rows containing vertical lines as well 
 * as the amount of vertical lines inside each row. The amounts will adjust 
 * according to the SIZE of the UT Tower.
 */
    	for (int i = 1; i <= (2 * SIZE -2); i++)
    	{
	    	drawSpacesTop();
	    	for (int j = 1; j <= (2 * SIZE -1); j++)
	    	{
	    		System.out.print("|");
	    	}
	    	System.out.println(); 
    	}
    }
    
    public static void drawSpacesTop()
    {
/*
 * This method will be called by other static methods to draw the amount of spaces
 * required in the top part of the UT Tower. The amount of spaces will vary
 * according to SIZE.
 */
    	for (int i = 1; i <= (4 * SIZE + 2); i++)
    	{
    		System.out.print(" ");
    	}
    }
//This comment ends the methods used to create the top part of the UT Tower.

//Methods used to create the middle body part of the UT Tower begins here.
    public static void drawBody()
    {
/*
 * This method will be called by the main method to create the middle
 * body part of the UT Tower which will scale according to SIZE.
 * The number of rows of tildes and windows will also vary according to 
 * the SIZE of the UT Tower.
 */
    	for (int windows = 1; windows <= (SIZE * SIZE); windows++)
    	{
	    	drawTilde();
	    	drawWindows();
    	}
    	drawTilde();
    }
    
    public static void drawTilde()
    {
/*
 * This method will be called to create lines of tildes in the middle
 * body part of the UT Tower. The amount of tildes will vary upon SIZE.
 */
    	drawSpacesBody();
    	for (int i = 1; i <= (2 * SIZE + 3); i++)
    	{
    		System.out.print("~");
    	}
    	System.out.println();
    }
    
    public static void drawWindows()
    {
/*
 * This method will be called to create the windows in the middle body
 * part of the UT Tower. Amount of windows will vary according to the SIZE.
 */
    	drawSpacesBody();
    	System.out.print("|");
    	for (int i = 1; i <= SIZE; i++)
    	{
    		System.out.print("-O");
    	}
    	System.out.println("-|");
    }
    
    public static void drawSpacesBody()
    {
/*
 * This method will be called by other static methods to draw the amount of spaces
 * required for each row of tildes and windows in the middle body part of the UT 
 * Tower. The amount of spaces will vary according to SIZE.
 */
    	for (int i = 1; i <= (SIZE * 4); i++)
    	{
    		System.out.print(" ");
    	}
    }
//This comment ends the methods used for the middle body part of the UT Tower.

//Methods used to create the base of the UT Tower begins here.
    public static void drawBase()
    {
/*
 * This method will be called by the main method to draw the bottom
 * part of the UT Tower which will scale according to SIZE.
 */
    	drawUpperBase();
    	drawLowerBase();
    }
    
    public static void drawUpperBase()
    {
/*
 * This method will be called to create the upper part of the base
 * of the UT Tower which will vary according to SIZE.
 */
    	
//The first outer for loop in this nested loop shown below will determine the 
//amount of lines or rows in the upper base.
    	for (int line = (SIZE / 2 + 1); line >= 1; line--)
    	{
//The for loop below will space out the lines depending on the line
//number from the first for loop.
    		for (int spaces = 1; spaces <= (line - 1); spaces++)
    		{
    			System.out.print("   ");
    		}
    		System.out.print("/");
//The for loop shown below here will determine the amount of quotation and 
//apostrophe marks inside each line or row depending on each line number.
	    	for (int i = 1; i <= ((SIZE * 5 + 3) - 3 * line); i++)
	    	{
	  			System.out.print("\"'");
	    	}
	    	System.out.println("\"\\");
    	}
    }
    
    public static void drawLowerBase()
    {
/*
 * This method will be called to create the lower part of the base
 * of the UT Tower which will vary according to SIZE.
 */
    	for (int i = 1; i <= SIZE; i++)
    	{
    		System.out.print("/");
    		for (int k = 1; k <= (5 * SIZE); k++)
    		{
    			System.out.print("\"O");
    		}
    		System.out.println("\"\\");
    	}
    }
//This comment ends the methods used to create the base of the UT Tower.
}

