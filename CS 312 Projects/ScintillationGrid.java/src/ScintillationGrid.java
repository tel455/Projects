import java.awt.Color;
import java.awt.Graphics;

/**
 * @author  Thi Edison Le
 * @version 9/23/14
 * CS312 Assignment 3.
 * 
 * On my honor, Thi Edison Le, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to print out various scintillation grids and a student designed drawing. 
 *
 *  email address: thi_le@utexas.edu
 *  UTEID: TEL455
 *  Unique 5 digit course ID: 52827
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment:0
 */

public class ScintillationGrid 
{
	
// Main method that creates the DrawingPanel with scintillation grids.
// Restricted to chapters 1 - 3 of Building Java Programs
    public static void main(String[] args) 
    {
    	
/* In the final version of the program DO NOT call method drawingOne 
   from main or anywhere else in the program */
    	DrawingPanel panel = new DrawingPanel(900, 650);
        panel.setBackground(Color.CYAN);
        Graphics g = panel.getGraphics();
        
/*
 * The parameters (other than Graphics g) listed in the method drawScintGrid 
 * below are the (x,y) position, size of the large square (a), size of
 * the small squares (b), number of lines (c), and the thickness of the
 * line (d).
 */
        drawScintGrid(g, 0, 0, 348, 75, 3, 16);
        drawScintGrid(g, 400, 50, 422, 50, 6, 12);
        drawScintGrid(g, 50, 400, 220, 100, 1, 20);
        drawScintGrid(g, 500, 500, 148, 15, 7, 4);
    }
    
//This method will be called by the main method to draw the complete 
//scintillation grids.
    public static void drawScintGrid(Graphics g, int x, int y, int a, int b, int c, int d)
    {
    	g.setColor(Color.BLACK);
    	g.fillRect(x, y, a, a);
    	g.setColor(Color.GRAY);
    	drawGrayVertLines(g, x, y, a, b, c, d);
    	drawGrayHoriLines(g, x, y, a, b, c, d);
    	g.setColor(Color.WHITE);
    	drawWhiteCircles(g, x, y, b, c, d);
    }

/*This method will be called to create the vertical gray lines of 
 *the scintillation grids using the parameters that were stated in
 *the main method of this program. The loop will determine how many
 *vertical lines are generated.
 */
    public static void drawGrayVertLines(Graphics g, int x, int y, int a, int b, int c, int d)
    {
    	for (int z = 0; z < c; z++)
    	{
    		g.fillRect((x + b) + (b + d) * z, y, d, a);
    	}
    }
 
/*This method will be called to create the horizontal gray lines of 
 *the scintillation grids using the parameters that were listed in
 *the main method of this program. The loop will determine how many 
 *horizontal lines are generated.
 */
    public static void drawGrayHoriLines(Graphics g, int x, int y, int a, int b, int c, int d)
    {
    	for (int i = 0; i < c; i++)
    	{
    		g.fillRect(x, (y + b) + (b + d) * i, a, d);
    	}
    }
    
/*This method will be called to create the white circles at the 
 *intersections of the gray horizontal and vertical lines in 
 *the scintillation grids using the parameters that were listed in
 *the main method of this program.
 */
    public static void drawWhiteCircles(Graphics g, int x, int y, int b, int c, int d)
    {
    	//e is the variable for the size of the white circles. 
    	//The largest of the two calculations listed below will be
    	//used for e.
    	int e = Math.max((int) d + 2, (int) d / 5 * 2 + d);
    	
    	//The nested loop will determine the amount of rows and the
    	//amount of white circles in each row.
    	for (int line = 0; line < c; line++)
    	{
    		for (int i = 0; i < c; i++)
        	{
        		g.fillOval((x + (int)(b * 0.96)) + (b + d) * i, (y + (int)(b * 0.96)), e, e);
        	}
    		y += (b + d);
    	}
    }
//This comment ends the methods used to create the 4 scintillation grids.
    
// method for the student designed drawing
// NOT restricted to chapters 1 - 3 of Building Java Programs
    public static void drawingOne() 
    {
    	DrawingPanel panel = new DrawingPanel(400, 200);
        panel.setBackground(Color.GREEN);
        Graphics g = panel.getGraphics();
        
//Draws red ovals
        g.setColor(Color.RED);
        g.fillOval(0, 0, 200, 100);
        g.fillOval(200, 100, 200, 100);
        
//Draws black lines on top of the ovals
        g.setColor(Color.BLACK);
        g.drawLine(100, 0, 100, 100);
        g.drawLine(0, 50, 200, 50);
        g.drawLine(300, 100, 300, 200);
        g.drawLine(200, 150, 400, 150);
        
//Draws black lines to split drawing panel into four quadrants or rectangles
        g.drawLine(0,100, 400, 100);
        g.drawLine(200, 0, 200, 200);
	}
}