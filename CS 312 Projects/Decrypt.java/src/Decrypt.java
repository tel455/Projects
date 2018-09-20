import java.util.Scanner;

/**
 * CS312 Assignment 9.
 *
 * On my honor, Thi Le, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 *  email address: thi_le@utexas.edu
 *  UTEID: TEL455
 *  Section 5 digit ID: 52827
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment:1
 *  
 * Program to decrypt a message that has been
 * encrypted with a substitution cipher.
 * We assume only characters with ASCII codes
 * from 32 to 126 inclusive have been encrypted.
 */

public class Decrypt 
{
	// CS312 students, add your constants here
	public static final int StartCharASCII = 32;
	public static final int keyLength = 128;
	public static final int messageLength = 2459;
	
    public static void main(String[] arg)
    {
    	// CS312 Students, do not create any other Scanners connected to System.in
        Scanner keyboard = new Scanner(System.in);
        String fileName = getFileName(keyboard);
        String encryptedText = DecryptUtilities.convertFileToString(fileName);
        
        // The other method from DecryptUtilities you will have to use is
        // DecryptUtilities.getDecryptionKey(int[]), but first you need to
        // create an array with the frequency of all the ASCII characters in the 
        // encrypted text. Count ALL characters from ASCII code 0 to ASCII code 127

        // CS312 students, add your code here.
        
        char[] originalKey = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, ' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~'};
        int[] frequencies = getFrequency(originalKey, encryptedText);
        char[] updatedKey = DecryptUtilities.getDecryptionKey(frequencies);
        introduction(encryptedText, originalKey, frequencies, updatedKey);
        String decrypted = decryptedString(encryptedText, originalKey, updatedKey);
        current(keyboard, decrypted, encryptedText, originalKey, updatedKey);
        String finalKey = decrypt(keyboard, encryptedText, originalKey, frequencies, updatedKey, decrypted);
        conclusion(finalKey);
        keyboard.close();
    }
    
    // CS312 students, add your methods here
    
// get the name of file to use
    public static String getFileName(Scanner kbScanner) 
    {
        System.out.print("Enter the name of the encrypted file: ");
        String fileName = kbScanner.nextLine().trim();
        System.out.println();
        return fileName;
    }
    
//This method is called to count the frequency of each character present in the message.
    public static int[] getFrequency(char[] originalKey, String encryptedText) 
    {
        int[] frequency = new int[keyLength];
        for (int i = 0; i < keyLength - 1; i++) 
        {
            for (int j = 0; j < messageLength; j++) 
            {
                if (encryptedText.charAt(j) == originalKey[i]) 
                {
                    frequency[i]++;
                }
            }
        }
        return frequency;
    }
    
//This method prints the introduction of the program and counts the frequencies of each character
    public static void introduction(String encryptedText, char[] originalKey, int[] frequencies, char[] updatedKey) {
        System.out.println("The encrypted text is:");
        System.out.println(encryptedText);
        System.out.println("Frequencies of characters.");
        System.out.println("Character - Frequency");
        for(int i = StartCharASCII; i < keyLength - 1; i++) 
        {
            System.out.println(originalKey[i] + " - " +frequencies[i]);
        }
        System.out.println();
        System.out.println("The current version of the key for ASCII characters 32 to 126 is: ");
        for(int i = StartCharASCII; i < keyLength - 1; i++)
        {
            System.out.println("Encrypt character: " + originalKey[i] + ", decrypt character: " + updatedKey[i]);
        }
        System.out.println();
    }  
    
//This method will be called to update the current version of the decrypted message.
    public static String decryptedString(String encryptedText, char[] key1, char[] key2) 
    {
    	String updated = "";
        for (int i = 0; i < messageLength; i++)
        {
            int x = encryptedText.charAt(i);
            updated += key2[x];
        }
        return updated;
    }  
    
//This method will print the current version of the decrypted key thus far.
    public static String current(Scanner keyboard, String decrypted, String encryptedText, char[] key1, char[] key2)
    {
    	System.out.println("The current version of the decrypted text is: ");
        System.out.println();
        decrypted = decryptedString(encryptedText, key1, key2);
        System.out.print(decrypted);
        System.out.println();
        return decrypted;
    }  
    
//This method will decrypt the message in the file, continuously asking the user for the two characters they want to 
//switch while updating the decoding key and message itself until the user does not want to decrypt the message anymore. 
    public static String decrypt(Scanner keyboard, String encryptedText, char[] originalKey, int[] frequencies, char[] updatedKey, String decrypted) 
    {
        //For when the key starts to change
        char[] updatedKey2 = new char[keyLength];
        //For when the message starts to change
        String decrypted2 = "";
        String input = continueDecrypt(keyboard);
        while (input.equals("Y") || input.equals("y")) 
        {
            System.out.print("Enter the decrypt character you want to change: ");
            String first = keyboard.next();
            char firstChar = first.charAt(0);
            System.out.print("Enter what the character " + first + " should decrypt to instead: ");
            String second = keyboard.next();
            char secondChar = second.charAt(0);
            System.out.println(first + "'s will now decrypt to " + second + "'s and vice versa.");
            System.out.println();

            //Key starts to update here
            updatedKey2 = updateCurrentKey(updatedKey, firstChar, secondChar, encryptedText);
            decrypted2 = current(keyboard, decrypted2, encryptedText, updatedKey, updatedKey2);
            input = continueDecrypt(keyboard);
        }
        System.out.println();
        System.out.println("The current version of the key for ASCII characters 32 to 126 is: ");
        for (int i = StartCharASCII; i < keyLength - 1; i++) 
        {
            System.out.println("Encrypt character: " + originalKey[i] + ", decrypt character: " + updatedKey2[i]);
        }
        return decrypted2;
    }


    
//This method asks the user if they want to continue decrypting.
    public static String continueDecrypt(Scanner keyboard)
    {
        System.out.println("Do you want to make a change to the key?");
        System.out.print("Enter 'Y' or 'y' to make change: ");
        String input = keyboard.next();
        return input;
    }
    
//This method will be called to update and make the user's chosen characters switch places to decode the message 
    public static char[] updateCurrentKey(char[] updatedKey, char firstChar, char secondChar, String encryptedText)
    {
        for (int i = 0; i < keyLength; i++)
        {
            if (updatedKey[i] == firstChar)
            {
                updatedKey[i] = secondChar;
            } else if (updatedKey[i] == secondChar)
            {
                updatedKey[i] = firstChar;
            }
        }
        return updatedKey;
    }
    
//This method will print out the final version of the decrypted message.
    public static void conclusion(String finalKey)
    {
        System.out.println();
        System.out.println("The final version of the decrypted text is: ");
        System.out.println();
        System.out.print(finalKey);
    }
}