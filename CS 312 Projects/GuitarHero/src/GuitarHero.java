/**
* CS312 Assignment 12
*
* On my honor, Thi Le, this programming assignment is my own work
* and I have not provided this code to any other student.
*
* Student name: Thi Le
* UTEID: TEL455
* email address: thi_le@utexas.edu
* Section 5 digit ID: 52827
* Grader name: Donghyuk
* Number of slip days used on this assignment:0
*
*/

//Program to simulate Guitar Hero. In actuality, mimicking the 
//plucking of a guitar string based on the key/note pressed.
public class GuitarHero
{
	private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
	private static double pluck = 0;
	
	public static void main(String[] args) 
	{
		final double TEXT_POS_X = .2;
        final double TEXT_POS_Y = .8;
		display(TEXT_POS_X, TEXT_POS_Y);
		GuitarString[] strings = createGuitar(KEYBOARD.length()); 
		play(strings, KEYBOARD); 
    }
    
//This method will display the instructions and keys that can be played.
	public static void display(double TEXT_POS_X, double TEXT_POS_Y)
	{
		StdDraw.textLeft(TEXT_POS_X, TEXT_POS_Y + .1, "WELCOME TO GUITAR HERO");
		StdDraw.textLeft(TEXT_POS_X, TEXT_POS_Y + 0.05, "Type the corresponding key to play:");
		StdDraw.textLeft(TEXT_POS_X, TEXT_POS_Y - 0.05, "  2       4  5   7 8 9      - =");
        StdDraw.textLeft(TEXT_POS_X, TEXT_POS_Y - 0.10, "Q W E R T Y U I O P [");
		StdDraw.textLeft(TEXT_POS_X, TEXT_POS_Y - 0.20 , "      D F G     J K   ; '");
		StdDraw.textLeft(TEXT_POS_X, TEXT_POS_Y - 0.25 , "Z X C V B N M , . / SpaceBar");
		for (double i = .3; i <= .7; i += .1)
		{
				StdDraw.line(TEXT_POS_X - .2, TEXT_POS_Y - i, TEXT_POS_X + .7, TEXT_POS_Y - i);
		}
		StdDraw.filledEllipse(TEXT_POS_X, TEXT_POS_Y - .7, .05, .04);
		StdDraw.filledEllipse(TEXT_POS_X + .5, TEXT_POS_Y - .7, .05, .04);
		StdDraw.filledRectangle(TEXT_POS_X + .04, TEXT_POS_Y -.5, .01, .2);
		StdDraw.filledRectangle(TEXT_POS_X + .54, TEXT_POS_Y -.5, .01, .2);
	}
	
//This method will create an array of GuitarString objects and accepts an 
//integer parameter to determine the size of the array in order to calculate
//the frequency of the string to create the sound.
	public static GuitarString[] createGuitar(int length)
	{
		GuitarString[] strings = new GuitarString[length]; 	
		for(int i = 0; i < length; i++)
		{
			//The ith character of the KEYBOARD string corresponds to a frequency of 440 x 1.05956^(i - 24)
			strings[i] = new GuitarString(440 * Math.pow(1.05956, i - 24)); 
		}
		return strings;
	}
	
//This method will simulate a guitar (uses the key/note the user inputs) and "plucks the 
//guitar string" (play the sound corresponding to that key). If the user input doesn't match
//a character in keyboard, a sound will not be played.
    private static void play(GuitarString[] strings, String KEYBOARD)
    { 
		while (true)
		{
			if (StdDraw.hasNextKeyTyped())
			{
                char input = StdDraw.nextKeyTyped();
				if(KEYBOARD.indexOf(input) >= 0)
				{
					strings[KEYBOARD.indexOf(input)].pluck();
				}
            }
			pluck = 0;
			for(int i = 0; i < strings.length; i++)
			{
				pluck += strings[i].sample();
			}
            StdAudio.play(pluck);
			for(int i = 0; i < strings.length; i++)
			{
				strings[i].tic();
			}
        }
        
    }
}