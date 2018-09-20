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
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment:2
 */

public class Cipher
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
        String message = messageToEncrypt(keyboard);
        noEncryptPadding(message);
        encryptWithPadding(message);
        String messageToDecrypt = decryption(keyboard);
        decrypt(messageToDecrypt);
        keyboard.close();
    }


// shows the introduction to the program
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
    
//This method will prompt the user to input a message that they want encrypted.
    public static String messageToEncrypt(Scanner keyboard)
    {
    	System.out.println();
    	System.out.println("A demonstration of encrypting with a columnar transposition cipher:");
    	System.out.println();
    	System.out.print("Enter the message to encrypt: ");
    	String inputMessage = keyboard.nextLine();
    	return inputMessage;
	}
    
//This method will encrypt the user's message without padding.
    public static void noEncryptPadding(String message)
    {
    	System.out.println();
    	System.out.println("Message encrypted with columnar transposition cipher and no padding.");
    	for (int rows = 2; rows <= MAX_ROWS; rows++)
    	{
    		encryptMessage(message, rows);
    	}
    	System.out.println();
    }
    
  //This method will call others methods to pad the user's input message and encrypt the padded message.
  //encryptMessage will be called again by this method except this time, the string parameter
  //will be the padded message instead of the unpadded (original) message input by the user.
      public static void encryptWithPadding(String message)
      {
      	System.out.println("Message encrypted with columnar transposition cipher and padding.");
      	System.out.println();
      	for (int rows = 2; rows <= MAX_ROWS; rows++)
      	{
      		int columns = message.length()/ rows + Math.min(1, message.length() % rows);
      		int charsNeeded = columns * rows;
          	int numCharsForPadding = charsNeeded - message.length();
          	String paddedMessage = padding(message, numCharsForPadding); 
      		System.out.println("Clear text padded for " + rows + " rows: " + paddedMessage);
      		encryptMessage(paddedMessage, rows);
      		System.out.println();
      	}
      }
    
//This method will be called by other methods to encrypt the user's message
//with and without padding.
    public static void encryptMessage(String message, int rows)
    {
		System.out.print("Encrypted with " + rows + " rows: ");
    	for(int i = 0; i < rows; i++)
    	{
    	    for(int indexPosition = i; indexPosition < message.length(); indexPosition +=rows)
    	    {
    	        System.out.print(message.charAt(indexPosition));
    	    }
    	}
    	System.out.println();
    }
    
//This method will be called to pad the user's input message with X's as the pad.
    public static String padding(String numberOfX, int numCharsForPadding)
    {
    	for (int i = 1; i <= numCharsForPadding; i++)
    	{
			numberOfX += "X";
    	}
    	return numberOfX;
    }
    
//This method will prompt the user to input an encrypted message that will be decrypted.
    public static String decryption(Scanner keyboard)
    {
    	System.out.println();
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
    
//This method is called by the main method which will call other methods to decrypt the user's encrypted message.
    public static void decrypt(String messageToDecrypt)
    {
    	System.out.println();
    	for (int rows = 2; rows <= MAX_ROWS; rows++)
    	{
    		int remainder = messageToDecrypt.length() % rows;
			int numToPad = rows - remainder;
			String paddedDecryptMessage = decryptPadding(messageToDecrypt, rows, numToPad);
    		System.out.println("Decrypted text padded for " + rows + " rows: " + paddedDecryptMessage);
    		System.out.print("Decrypted with " + rows + " rows: ");
    		int numCol = paddedDecryptMessage.length() / rows;
    		decryptTheMessage(paddedDecryptMessage, numCol);
    		if (rows < 10)
    		{
    			System.out.println();
    		}
    	}
    }
    
//This method will be called to decrypt the user's input message.
    public static void decryptTheMessage(String decryptMessage, int numCol)
    {
    	for(int i = 0; i < numCol; i++)
    	{
    	    for(int indexPosition = i; indexPosition < decryptMessage.length(); indexPosition += numCol)
    	    {
    	        System.out.print(decryptMessage.charAt(indexPosition));
    	    }
    	}
		System.out.println();
    }
    
//This method will provide the necessary padding (as X's) to the user's encrypted message which will be decrypted.
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