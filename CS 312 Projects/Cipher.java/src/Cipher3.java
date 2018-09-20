import java.util.Scanner;

/**
 * CS312 Assignment 5.
 * 
 * On my honor, Thi Edison Le, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to encrypt and decrypt messages using a columnar transposition cipher.
 *
 *  email address: thi_le@utexas.edu
 *  UTEID: TEL455
 *  Unique 5 digit course ID: 52827
 *  Grader name: Donghuyk
 *  Number of slip days used on this assignment:2
 */

public class Cipher3 
{
    // CS312 Students: This constant must be set to 10 in the
    // final version of your program that you turn in.
    public static final int MAX_ROWS = 10;

    // main method to demonstrate various encryptions and
    // decryptions using a columnar transposition cipher
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in); 
        showIntro();    
        //String message = messageToEncrypt(keyboard);
        //noEncryptPadding(message);
        //encryptWithPadding(message);
        String messageToDecrypt = decryption(keyboard);
        decrypt(messageToDecrypt);
        keyboard.close();
    }


    // show the introduction to the program
    public static void showIntro() 
    {
        System.out.println("This program demonstrates a transposition cipher.");
        System.out.println("A cipher is an algorithm to encrypt or decrypt a message.");
        System.out.println();
        System.out.println("This program will demonstrate encrypting a message with");
        System.out.println("a columnar transposition cipher both with and without");
        System.out.println("padding characters. The program will then decrypt a message");
        System.out.println("assuming it was encrypted with a columnar transposition cipher");
        System.out.println("with padding.");
        System.out.println("After accepting user input, the program displays some tests.");
        System.out.println();
    }
    
    public static String messageToEncrypt(Scanner keyboard)
    {
    	System.out.println();
    	System.out.println("A demonstration of encrypting with a columnar transposition cipher:");
    	System.out.println();
    	System.out.print("Enter the message to encrypt: ");
    	String inputMessage = keyboard.nextLine();
    	return inputMessage;
	}
    
    public static void noEncryptPadding(String message)
    {
    	System.out.println();
    	System.out.println("Message encrypted with columnar transposition cipher and no padding.");
    	
    	for (int rows = 2; rows <= MAX_ROWS; rows++)
    	{
    		//encryptMessage(message, rows);
    	}
    	System.out.println();
    }
    
//    public static void encryptMessage(String message, int rows)
//    {
//    		System.out.print("Encrypted with " + rows + " rows: ");
//    		int columns = message.length()/ rows + Math.min(1, message.length() % rows);
//    		int charsNeeded = columns * rows;
//        	int numCharsForPadding = charsNeeded - message.length();
//        	int place = 0;
//        	System.out.println("columns" + columns);
//        	for (int j = 1; j <= MAX_ROWS; j++)
//        	{
//	        	for (int i = 1; i <= columns; i++)
//	        	{
//	    			System.out.print(message.charAt(place));
//	    			place += rows;
//	    		}
//	        	place = 1;
//	        	for (int i = 2; i <= columns; i++)
//	        	{
//	        		System.out.print(message.charAt(place));
//	    			place += rows;
//	        	}
//	    		place = 3;
//	    		for (int i = 3; i <= columns; i++)
//	        	{
//	        		System.out.print(message.charAt(place));
//	    			place += rows;
//	        	}
//        	}
//        	System.out.println();
//    }
    
    public static String decryption(Scanner keyboard)
    {
    	System.out.println("A demonstration of decrypting with a columnar transposition cipher:");
    	System.out.println("If the length of the message is not a multiple of the number of rows");
    	System.out.println("it will be padded which may throw off the decryption.");
    	System.out.println();
    	System.out.print("Enter the message to decrypt: ");
    	String decryptMessage = keyboard.nextLine();
    	System.out.println();
    	System.out.println("Messages Decrypted with a Columnar Transposition Cipher");
    	return decryptMessage;
    }
    
    public static void decrypt(String messageToDecrypt)
    {
    	System.out.println();
    	for (int rows = 2; rows <= MAX_ROWS; rows++)
    	{
    		int remainder = messageToDecrypt.length() % rows;
			int numToPad = rows - remainder;
			String decryptMessage = decryptPadding(messageToDecrypt, rows, numToPad);
    		System.out.println("Decrypted text padded for " + rows + " rows: " + decryptMessage);
    		System.out.print("Decrypted with " + rows + " rows: ");
    		int numCol = decryptMessage.length() / rows;
    		decryptTheMessage(decryptMessage, numCol, rows);
    		System.out.println();
    	}
    }
    
    public static void decryptTheMessage(String decryptMessage, int numCol, int rows)
    {
    	for(int i = 0; i < numCol; i++)
    	{
    	    int j = i;
    	    for(int k = j; k < decryptMessage.length(); k += numCol)
    	    {
    	        System.out.print(decryptMessage.charAt(k));
    	    }
    	}
		System.out.println();
    }
    
    public static String decryptPadding(String messageToDecrypt, int rows, int numToPad)
    {
    	if ((messageToDecrypt.length() % rows) != 0)
    	{
			for (int i = 1; i <= numToPad; i++)
			{
				messageToDecrypt += "X";
			}
    	}
    	return messageToDecrypt;
    }
}


