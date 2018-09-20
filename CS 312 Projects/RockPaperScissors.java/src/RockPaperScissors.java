import java.util.Scanner;

/**
 * CS312 Assignment 4.
 * 
 * On my honor, Thi Edison Le, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play Rock Paper Scissors
 *
 *  email address: thi_le@utexas.edu
 *  UTEID: TEL455
 *  Unique 5 digit course ID: 52827
 *  Grader name: Donghyuk
 *  Number of slip days used on this assignment:0
 */

public class RockPaperScissors 
{   
/* A program to allow a human player to play rock - paper - scissors
 * against the computer. If args.length != 0 then we assume
 * the first element of args can be converted to an int
 */
    public static void main(String[] args) 
    {
        RandomPlayer computerPlayer = buildRandomPlayer(args);
        Scanner console = new Scanner(System.in);
        String userName = inputName(console);
        int roundsOfPlay = inputRounds(console, userName);
        playTheGame(computerPlayer, console, userName, roundsOfPlay);
    }

/*
 * Build the random player. If args is length 0 then the 
 * default RandomPlayer is built that follows a predictable 
 * sequence. If args.length > 0 then we assume we can
 * convert the first element to an int and build the 
 * RandomPlayer with that initial value.
 */
    public static RandomPlayer buildRandomPlayer(String[] args) {
        if(args.length == 0) {
            return new RandomPlayer();
        } else {
            int seed = Integer.parseInt(args[0]);
            return new RandomPlayer(seed);
        }
    }
    
/*
 * This method will prompt the user (by using scanner) for his or her name.
 */
    public static String inputName(Scanner console)
    {
    	System.out.println("Welcome to ROCK PAPER SCISSORS. I, Computer, will be your opponent.");
    	System.out.print("Please type in your name and press return: ");
    	String name = console.next();
    	System.out.println();
    	System.out.println("Welcome " + name + ".");
    	System.out.println();
    	return name;
    }
    
/*
 * This method will ask the user how many rounds of rock, paper, scissors they
 * want to play.
 */
    public static int inputRounds(Scanner console, String userName)
    {
    	System.out.println("All right " + userName + ". How many rounds would you like to play?");
    	System.out.print("Enter the number of rounds you want to play and press return: ");
    	int rounds = console.nextInt();
    	console.nextLine();
    	System.out.println();
    	return rounds;
    }
    
/*
 * This method will put the game into action, pitting the computer against the user as they input 
 * their chosen sign in a battle of rock, paper, scissors. The number of victories will be tallied
 * and calculated later to determine the ultimate master of the game in the results method.
 */
    public static void playTheGame(RandomPlayer computerPlayer, Scanner console, String userName, int roundsOfPlay)
    {
    	int userScore = 0;
    	int compScore = 0;
    	int draws = 0;
    	for (int i = 1; i <= roundsOfPlay; i++)
    	{
    		System.out.println("Round " + i + ".");
    		System.out.println(userName + ", please enter your choice for this round.");
    		System.out.print("1 for ROCK, 2 for PAPER, and 3 for SCISSORS: ");
    		
//Get Computer's and user's chosen choice
    		int userChoice = console.nextInt();
    		console.nextLine();
    		int computerChoice = computerPlayer.getComputerChoice();
    		
//Computer's and user's chosen choice are passed through the method called "choice" below.
    		System.out.println("Computer picked " + choice(computerChoice) + ", " + userName + " picked " + choice(userChoice) + ".");
    		System.out.println();
    		
//Conditional statements to total up the score from the amount of rounds played.
    		int tally = conditions(computerChoice, userChoice);
    		if (tally == 0)
    		{
    			draws++;
    		}
    		else if (tally == 1)
    		{
    			compScore++;
    		}
    		else if (tally == 2)
    		{
    			userScore++;
    		}
    	}
    	results(roundsOfPlay, userName, userScore, compScore, draws);
    }
    
/*
 * This method is called by the playTheGame method and will return a string
 * of the chosen sign depending on the number 1-3 which corresponds to the
 * symbol. Remember that ROCK = 1, PAPER = 2, and SCISSORS = 3. The parameter that is passed through this
 * method will be either userChoice or computerChoice.
 */
    public static String choice(int chosenChoice)
    {
    	if (chosenChoice == 1)
    	{
    		return "ROCK";
    	}
    	else if (chosenChoice == 2)
    	{
    		return "PAPER";
    	}
    	else
    	{
    		return "SCISSORS";
    	}
    }
    
/*
 * This method is also called by the playTheGame method and will determine
 * the result of each round depending on the computer's and user's chosen sign
 * which are passed as int parameters. The computer's and user's choice are passed as parameters to 
 * differentiate between the various results of a round played in the game.
 */
    public static int conditions(int computerChoice, int userChoice)
    {	
//Other than a tie, there are three different results depending on
//what the user and computer chose. Their chosen symbols, represented
//by the numbers 1-3, are multiplied together to get the conditions below.
//EXAMPLE: ROCK(1) * SCISSORS(3) = 1 * 3 = 3
    	int results = userChoice * computerChoice;
    	if (results == 3)
    	{
    		System.out.print("ROCK breaks SCISSORS. ");
    	}
    	else if (results == 2)
    	{
    		System.out.print("PAPER covers ROCK. ");
    	}
    	else if (results == 6)
    	{
    		System.out.print("SCISSORS cut PAPER. ");
    	}
    	
//Conditions below determine the winner of the round.
//Score of 0 is a tie, score of 1 is the computer's win, score of 2 is the user's win.
//The difference between userChoice and computerChoice
//are calculated and used in the conditions below.
//EXAMPLE: user chose PAPER(2) - computer chose SCISSORS(3) = 2 - 3 = -1
    	int score = 0;
    	int winner = userChoice - computerChoice;
    	if (winner == -1 || winner == 2)
    	{
    		score = 1;
    		System.out.println("I win.");
    	}
    	else if (winner == 0)
    	{
    		score = 0;
    		System.out.println("We picked the same thing! This round is a draw.");
    	}
    	else if (winner == 1 || winner == -2)
    	{
    		score = 2;
    		System.out.println("You win.");
    	}
    	System.out.println();
    	return score;
    }
    
/*
 * This method is called by the playTheGame method and will produce the final 
 * results of playing rock, paper, scissors and determine who is the 
 * master of the game (unless there's a tie which will end with the method 
 * outputting a draw between the players).
 */
    public static void results(int roundsOfPlay, String userName, int userScore, int compScore, int draws)
    {
    	System.out.println();
    	System.out.println("Number of games of ROCK PAPER SCISSORS: " + roundsOfPlay);
    	System.out.println("Number of times Computer won: " + compScore);
    	System.out.println("Number of times " + userName + " won: " + userScore);
    	System.out.println("Number of draws: " + draws); 
    	
//Conditional statements below are for the final results of an even match, the computer is the ultimate master, 
//or the user is the ultimate master. This depends on the total tallied scores from above.
    	if (compScore != userScore && compScore > userScore)
    	{
    		System.out.println("I, Computer, am a master at ROCK, PAPER, SCISSORS.");
    	}
    	else if (compScore != userScore && compScore < userScore)
    	{
    		System.out.println("You, " + userName + ", are a master at ROCK, PAPER, SCISSORS.");
    	}
    	else if (compScore == userScore)
    	{
    		System.out.println("We are evenly matched.");
    	}
    }
}