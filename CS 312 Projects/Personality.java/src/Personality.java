/**
 * CS312 Assignment 8.
 * 
 * On my honor, Thi Le, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to read a file with raw data from a Keirsey personality test
 * and print out the results.
 *
 *  email address:thi_le@utexas.edu
 *  UTEID:TEL455
 *  Unique 5 digit course ID:52827
 *  Grader name:Donghyuk
 *  Number of slip days used on this assignment:0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class Personality
{
 // CS312 Student- Add your constants here.

// The main method to process the data from the personality tests
    public static void main(String[] args) throws FileNotFoundException 
    {
        Scanner keyboard = new Scanner (System.in);
        Scanner fileScanner = getFileScanner(keyboard);
        
        // CS312 students - your code and methods calls go here
        int numNames = fileScanner.nextInt();
        procedure(fileScanner, numNames);

        fileScanner.close();
        keyboard.close();
        
    }

// CS312 Students add you methods here

//This method will output the results of the personality test
    public static void procedure(Scanner fileScanner, int numNames) 
    {
        fileScanner.nextLine();
        for (int i = 0; i < numNames; i++) 
        {
            String names = names(fileScanner);
            int currentColumn = 0;
            System.out.printf("%29s", names + ":");
            calculate(fileScanner,  currentColumn);
        }
    }

//This method gets the person's name
    public static String names(Scanner fileScanner)
    {
        String name = "";
        if (fileScanner.hasNextLine())
        {
            name = fileScanner.nextLine();
        } 
        return name;
    }

//This method gets the choices from the inputted file and puts them into an array
    public static char[] choices (Scanner fileScanner)
    {
        char[] choices = new char[70];
        if(fileScanner.hasNextLine()) 
        {
            String line = fileScanner.nextLine();
            for (int i = 0; i < line.length(); i++) 
            {
                choices[i] = line.charAt(i);
            }
        }
        return choices;
    }

//This method will count up the choices and calculate the percentage of B choices. The method will then choose the corresponding letter for the personality
    public static String calculate(Scanner fileScanner, int currentColumn)
    {
        int[] percentB = new int[4];
        char[] AB = choices(fileScanner);
        String result = "";
        String types = "EISNTFJP";
        
        //calculates percentage of B answers
        int currentDimension = 0;
        for(currentDimension = 0; currentDimension <= 3; currentDimension++)
        {
            if (currentColumn == 0)
            {
                double countA = countA(AB, currentColumn);
                double countB = countB(AB, currentColumn);
                if (countA + countB != 0)
                {
                    percentB[currentDimension] = (int) Math.round(countB*100/(countA+countB));
                    if (percentB[currentDimension] != 0)
                    {
                    	System.out.printf("%11d", percentB[currentDimension]);
                    }
                    else if (percentB[currentDimension] == 0)
                    {
                    	System.out.printf("%11s", "0");
                    }
                    
                  //Chooses the corresponding letter
                    if (percentB[currentDimension] > 50)
                    {
                        result += types.charAt(2*currentDimension+1);
                    } 
                    else if (percentB[currentDimension] < 50)
                    {
                        result += types.charAt(2*currentDimension);
                    } 
                    else if (percentB[currentDimension] == 50) 
                    {
                        result += "X";
                    }
                    currentColumn++;
                } 
                else 
                {
                	System.out.printf("%11s", "NO ANSWERS");
                    currentColumn++;
                    result += "-";
                }
            } 
            else 
            {
                double countA = countA(AB, currentColumn)+countA(AB, currentColumn+1);
                double countB = countB(AB, currentColumn)+countB(AB,currentColumn+1);
                if (countA + countB != 0)
                {
                    percentB[currentDimension] = (int) Math.round(countB*100/(countA+countB));
                    if (percentB[currentDimension] != 0)
                    {
                    	System.out.printf("%11d", percentB[currentDimension]);
                    }
                    else if (percentB[currentDimension] == 0)
                    {
                    	System.out.printf("%11s", "0");
                    }
                    
                  //Chooses the corresponding letter
                    if (percentB[currentDimension] > 50)
                    {
                        result += types.charAt(2*currentDimension+1);
                    } 
                    else if (percentB[currentDimension] < 50)
                    {
                        result += types.charAt(2*currentDimension);
                    } 
                    else if (percentB[currentDimension] == 50) 
                    {
                        result += "X";
                    }
                    currentColumn+=2;
                } 
                else
                {
                	System.out.printf("%11s", "NO ANSWERS");
                    currentColumn+=2;
                    result += "-";
                }
            }
        }
        System.out.printf("%s\n", " = " + result);
        result = Arrays.toString(percentB) + result;
        return result;
    }

//Method to count A answers
    public static double countA(char[] AB, int currentColumn)
    {
        double countA = 0;
        for(int i = currentColumn; i < AB.length; i +=7) 
        {
            if (AB[i] == 'A' ) 
            {
                countA ++;
            }
        }
        return countA;
    }

//Method to count B answers
    public static double countB(char[] AB, int currentColumn) 
    {
        double countB = 0;
        for (int i = currentColumn; i < AB.length; i += 7) 
        {
            if (AB[i] == 'B' )
            {
                countB ++;
            }
        }
        return countB;
    }
    
// Method to choose a file.
// Asks user for name of file. 
// If file not found create a Scanner hooked up to a dummy set of data
// Example use: 
    public static Scanner getFileScanner(Scanner keyboard)
    {
        Scanner result = null;
        try 
        {
            System.out.print("Enter the name of the file with the personality data: ");
            String fileName = keyboard.nextLine().trim();
            System.out.println();
            result = new Scanner(new File(fileName));
        }
        catch(FileNotFoundException e) 
        {
            System.out.println("Problem creating Scanner: " + e);
            System.out.println("Creating Scanner hooked up to default data " + e);
            String defaultData = "1\nDEFAULT DATA\n"
                + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
            result = new Scanner(defaultData);
        }
        return result;
    }    
}