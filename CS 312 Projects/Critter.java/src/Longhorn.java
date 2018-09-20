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

public class Longhorn extends Critter
{
    Random rand = new Random();
    int r = 0;
    private int life;
    private int moves = 0;
    public boolean full = false;
    public boolean fought = false;
    
    public Longhorn() 
    {
        life = 15;
    }
    
    public boolean eat() 
    {
    	life++;
        full = true;
        return true;
    }
    
    public String toString() 
    {
        return "L";
    }
    
    public Color getColor() 
    {
        if (full)
        {
            return Color.GREEN;
        }
        else if (fought)
        {
            return Color.RED;
        }
        else
        {
            return Color.ORANGE;
        }
    }
    
    public Attack fight (String opponent) 
    {
        fought = true;
        if (life > 0) 
        {
            life--;
            if (opponent == "S")
            {
                return Attack.POUNCE;
            }
            else if (opponent == "^" || opponent == ">" || opponent == "V" || opponent == "<" || opponent == "0")
            {
                return Attack.SCRATCH;
            }
            else
            {
                return Attack.ROAR;
            }
        }
        else
        {
            return Attack.POUNCE;
        }
    }
    
//Animal will move depending on the situation
    public Direction getMove() 
    {
        full = false;
        fought = false;
        moves++;
        //Looks for food
        if(getNeighbor(Direction.NORTH) == ".")
        {
            return Direction.NORTH;
        }
        else if (getNeighbor(Direction.EAST) == ".")
        {
            return Direction.EAST;
        }
        else if (getNeighbor(Direction.SOUTH) == ".")
        {
            return Direction.SOUTH;
        }
        else if (getNeighbor(Direction.WEST) == ".")
        {
            return Direction.WEST;
        }
        
        //Hunts others if Longhorn has life
        if (life > 0) 
        {
            if (!(getNeighbor(Direction.NORTH) == " ") && !(getNeighbor(Direction.NORTH) == "L"))
            {
                return Direction.NORTH;
            }
            else if (!(getNeighbor(Direction.EAST) == " ") && !(getNeighbor(Direction.EAST) == "L"))
            {
                return Direction.EAST;
            }
            else if (!(getNeighbor(Direction.SOUTH) == " ")&& !(getNeighbor(Direction.SOUTH) == "L"))
            {
                return Direction.SOUTH;
            }
            else if (!(getNeighbor(Direction.WEST) == " ")&& !(getNeighbor(Direction.WEST) == "L"))
            {
                return Direction.WEST;
            }
        }
        
        //Roams if not eating or fighting
        if(moves % 4 == 0) 
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
}
