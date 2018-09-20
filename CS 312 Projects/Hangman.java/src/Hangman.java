/**
 * CS312 Assignment 6.
 * 
 * On my honor, Thi Edison Le, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play Hangman.
 *
 *  email address: thi_le@utexas.edu
 *  UTEID: TEL455
 *  Unique 5 digit course ID:52827
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment:0
 */

import java.util.Scanner;

public class Hangman 
{
    public static final int GUESSES = 5;
    public static void main(String[] args) 
    {
    	intro();
        PhraseBank phrases = buildPhraseBank(args);
// CS312 Students -- Do not create any additional Scanners.
        Scanner keyboard = new Scanner(System.in);
        
// CS312 students: add your code here
        do 
        {
        	String secretPhrase = phrases.getNextPhrase();
        	String codedPhrase = getCodedPhrase(secretPhrase);
        	topic(phrases);
        	playHangman(keyboard, secretPhrase, codedPhrase);
        } 
        while
        	(playAgain(keyboard));
    }
    
// CS12 students: add your methods here.

//This method will code the current secret phrase with _ for spaces 
//and * for letters in the phrase.
  	public static String getCodedPhrase(String secretPhrase)
  	{
	  	String newPhrase = "";
	      for (int i = 0; i < secretPhrase.length(); i++)
	  	{
	      	if (secretPhrase.charAt(i) == '_')
	  	    {
	  	        newPhrase += "_";
	  	    } 
	  	    else
	  	    {
	  	        newPhrase += "*";
	  	    }
	  	} 
	      return newPhrase;
  	}
      
//This method will print the topic of words for hangman.
    public static void topic(PhraseBank phrases)
    {
    	System.out.println();
        System.out.println("I am thinking of a " + phrases.getTopic() +" ...");
        System.out.println();
    }
    
//This method will play the hangman game with the user.
    public static void playHangman(Scanner keyboard, String secretPhrase, String codedPhrase) 
    {
        int wrong = 0;
        String alphabet = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z ";
        while (wrong < GUESSES && !secretPhrase.equals(codedPhrase)) 
        {
        	System.out.println("The current phrase is " + codedPhrase);
        	enterGuess(alphabet);
        	String input = keyboard.nextLine().toUpperCase();
            System.out.println();
            if (!alphabet.contains(input))
            {
            	input = invalidGuess(keyboard, alphabet, input);
            }
            System.out.println("You guessed " + input + ".");
            wrong += wrongGuesses(secretPhrase, input);
            System.out.println("Number of wrong guesses so far: " + wrong);
            codedPhrase = updateCodedPhrase(codedPhrase, secretPhrase, input );
            alphabet = lettersNotGuessed(alphabet, input);
        }
        result(secretPhrase, wrong);
    }
    
//This method prints out guidelines for the user.
    public static void enterGuess(String alphabet)
    {
    	System.out.println("The letters you have not guessed yet are: ");
        System.out.println(alphabet);
        System.out.print("Enter your next guess: ");
    }
    
//This method will prompt the user to input a valid guess that is a letter (if it 
//has not aleady been guessed).
    public static String invalidGuess(Scanner keyboard, String alphabet, String input)
    {
    	while (!alphabet.contains(input)) 
    	{
            System.out.println(input + " is not a valid guess.");
            enterGuess(alphabet);
            input = keyboard.nextLine().toUpperCase();
            System.out.println();
    	}
    	return input;
    }
    
//This method will check if the user made a wrong or correct guess. If wrong, the number of wrong
//guesses will be incremented in the method it was called.
    public static int wrongGuesses (String secretPhrase, String input) 
    {
    	if (!secretPhrase.contains(input)) 
    	{
    		System.out.println("That is not present in the secret phrase.");
    		return 1;
    	}
    	else
    	{
    		System.out.println("That is present in the secret phrase.");
    		return 0;
    	}
    }

//This method will update the coded phrase with the correctly guessed letters inputted by the user.
    public static String updateCodedPhrase(String codedPhrase, String secretPhrase, String input) 
    {
    	String updatedPhrase = "";
    	for (int i = 0; i < secretPhrase.length(); i++)
    	{
        	if (secretPhrase.substring(i, i+1).equals(input))
    	    {
        		updatedPhrase += secretPhrase.charAt(i);
    	    } 
    	    else
    	    {
    	        updatedPhrase += codedPhrase.charAt(i);
    	    }
    	}
    	codedPhrase = updatedPhrase;
        return codedPhrase;
    }
    
//This method will update the list of letters not yet guessed.
    public static String lettersNotGuessed (String alphabet, String input) 
    {
        if (alphabet.contains(input)) 
        {
            int inputIndex = alphabet.indexOf(input);
            alphabet = alphabet.substring(0, inputIndex) + alphabet.substring(inputIndex + 2);
            return alphabet;
        } 
        else
        {
        return alphabet;
        }
    }
    
//This method will determine whether the user won or lost.
    public static void result(String secretPhrase, int wrong)
    {
        if(wrong < 5) 
        {
            System.out.println("The phrase is " + secretPhrase + ".");
            System.out.println("You win!!");
        }
        else
        {
            System.out.println("You lose. The secret phrase was " + secretPhrase);
        }
    }
    
//This method asks the player if they want to play hangman again.
    public static boolean playAgain(Scanner keyboard) 
    {
        System.out.println("Do you want to play again?");
        System.out.print("Enter 'Y' or 'y' to play again: ");
        char input = keyboard.nextLine().toUpperCase().charAt(0);
        return input == 'Y';
    }
    
// Build the PhraseBank.
// If args is empty or null, build the default phrase bank.
// If args is not null and has a length greater than 0
// then the first elements is assumed to be the name of the 
// file to build the PhraseBank from.
    public static PhraseBank buildPhraseBank(String[] args) 
    {
        PhraseBank result;
        if(args == null || args.length == 0 
                || args[0] == null || args[0].length() == 0)
            result =  new PhraseBank();
        else
            result = new PhraseBank(args[0]);
        return result;
    }
    
// show the intro to the program
    public static void intro() 
    {
        System.out.println("This program plays the game of hangman.");
        System.out.println();
        System.out.println("The computer will pick a random phrase.");
        System.out.println("Enter capital letters as your guesses.");
        System.out.println("After 5 wrong guesses you lose.");
    }
}