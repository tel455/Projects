/*
 * CS312 Assignment 1.
 * On my honor, Thi Edison Le, this programming assignment is my own work.
 * Section Number: 52827
 *
 * A program to print out the lyrics to the
 * children's song, "There was an old woman".
 *
 *  Name: Thi Edison Le
 *  email address: thi_le@utexas.edu
 *  UTEID: TEL455
 *  Section 5 digit ID: 52827
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment:0
 */

public class Song 
{
	public static void main(String[] args) 
	{
/*
 * This method will call and create the lyrics to the 
 * song "There Was an Old Woman Who Swallowed a Fly".
 */
		fly();
		spiderToFly();
		birdToFly();
		catToFly();
		dogToFly();
		goatToFly();
		cowToFly();
		horse();	
	}
	
	public static void fly() 
	{
/*
 * This method will be called by the main method to 
 * create the first paragraph of the lyrics to the song.
 */
		System.out.println("There was an old woman who swallowed a fly.");
		swallowFlyAndDie();
	}
	
	public static void spiderToFly() 
	{
/*
 * This method will be called by the main method to
 * create the second paragraph of the lyrics to the song.
 */
		System.out.println("There was an old woman who swallowed a spider,");
		System.out.println("That wriggled and iggled and jiggled inside her.");
		swallowSpiderToFly();
	}
	
	public static void birdToFly() 
	{
/*
 * This method will be called by the main method to
 * create the third paragraph of the lyrics to the song.
 */
		System.out.println("There was an old woman who swallowed a bird,");
		System.out.println("How absurd to swallow a bird.");
		swallowBirdToFly();
	}
	
	public static void catToFly() 
	{
/*
 * This method will be called by the main method to
 * create the fourth paragraph of the lyrics to the song.
 */
		System.out.println("There was an old woman who swallowed a cat,");
		System.out.println("Imagine that to swallow a cat.");
		swallowCatToFly();
	}
	
	public static void dogToFly() 
	{
/*
 * This method will be called by the main method to
 * create the fifth paragraph of the lyrics to the song.
 */
		System.out.println("There was an old woman who swallowed a dog,");
		System.out.println("What a hog to swallow a dog.");
		swallowDogToFly();
	}
	
	public static void goatToFly() 
	{
/*
 * This method will be called by the main method to
 * create the sixth paragraph of the lyrics to the song.
 */
		System.out.println("There was an old woman who swallowed a goat,");
		System.out.println("She just opened her throat to swallow a goat.");
		swallowGoatToFly();
	}
	
	public static void cowToFly() 
	{
/*
 * This method will be called by the main method to
 * create the seventh paragraph of the lyrics to the song.
 */
		System.out.println("There was an old woman who swallowed a cow,");
		System.out.println("I don't know how she swallowed a cow.");
		swallowCowToFly();
	}
	
	public static void horse() 
	{
/*
 * This method will be called by the main method to 
 * create the eighth and final paragraph of the lyrics
 * to the song.
 */
		System.out.println("There was an old woman who swallowed a horse,");
		System.out.println("She died of course.");
	}

	public static void swallowFlyAndDie() 
	{
/*
 * This method will be called to create the redundant last 
 * two lines plus a blank line of lyrics of every paragraph 
 * excluding the last paragraph.
 */
		System.out.println("I don't know why she swallowed that fly,");
		System.out.println("Perhaps she'll die.");
		System.out.println("");
	}
	
	public static void swallowSpiderToFly()
	{
/*
 * This method will be called to create the lyrics of the 
 * second paragraph of the spider catching the fly.
 */
		System.out.println("She swallowed the spider to catch the fly,");
		swallowFlyAndDie();
	}
	
	public static void swallowBirdToFly()
	{
/*
 * This method will be called to create the lyrics of the 
 * third paragraph of the bird catching the spider
 * which catches the fly.
 */
		System.out.println("She swallowed the bird to catch the spider,");
		swallowSpiderToFly();
	}
	
	public static void swallowCatToFly()
	{
/*
 * This method will be called to create the lyrics of the 
 * fourth paragraph of the cat catching the bird
 * which catches the next organism and so on.
 */
		System.out.println("She swallowed the cat to catch the bird,");
		swallowBirdToFly();
	}
	
	public static void swallowDogToFly()
	{
/*
 * This method will be called to create the lyrics of the 
 * fifth paragraph of the dog catching the cat
 * which catches the next organism and so on.
 */
		System.out.println("She swallowed the dog to catch the cat,");
		swallowCatToFly();
	}
	
	public static void swallowGoatToFly()
	{
/*
 * This method will be called to create the lyrics of the 
 * sixth paragraph of the goat catching the dog
 * which catches the next organism and so on.
 */
		System.out.println("She swallowed the goat to catch the dog,");
		swallowDogToFly();
	}
	
	public static void swallowCowToFly()
	{
/*
 * This method will be called to create the lyrics of the 
 * seventh paragraph of the cow catching the goat
 * which catches the next organism and so on.
 */
		System.out.println("She swallowed the cow to catch the goat,");
		swallowGoatToFly();
	}
}