/**
 * @authors: Student 1: Thi Le
 *           Student 2: No partner
 * CS312 Assignment 6.
 *
 *  On <MY|OUR> honor, Thi Edison Le and <NAME2), this programming assignment is <MY|OUR> own work
 *  and <I|WE> have not provided this code to any other student.
 *
 *
 * A program to simulate the dice game of Pig and the effectiveness of
 * various player strategies.
 *
 * Student 1: Thi Le
 * UTEID: TEL455
 * email address: thi_le@utexas.edu
 * Section 5 digit ID: 52827
 * Grader name: Donghyuk
 * Number of slip days used on this assignment: 1
 *
 * Student 2:
 * UTEID:
 * email address:
 * 
 */

import java.util.Random;

public class PigSimulation
{

// number of sides on the die
    public static final int SIDES = 6;

// constants for strategy based on stopping when a certain number
// of points reached for the turn
    public static final int SCORE_STOPS_TURN = 1;
    public static final int SCORE_LIMIT_FOR_TURN = 15;

// constants for strategy based on stopping when a certain number
// of rolls are made for the turn
    public static final int ROLLS_STOPS_TURN = 2;
    public static final int ROLLS_LIMIT_FOR_TURN = 5;

// pig 1 output uses 2000316, pig 2 uses 20141015, pig 3 uses 615853
    public static final int SEED_VALUE = 615853;
    
    // CS312 students, add your constants here

// Show the intro. Then play a game of Pig with 
// the two given strategies and show the output.
// Then run various experiments playing 40,000 games of Pig.
    public static void main(String[] args) 
    {
        intro();
        Random randNumGen = createRandomObject(args);
        runSampleGame(randNumGen);
        runExperiments(randNumGen);
    }
    
//This method is the random number generator
    public static Random createRandomObject(String[] args) 
    {
        if(args == null || args.length == 0)
            return new Random(SEED_VALUE);
        else
            return new Random(Integer.parseInt(args[0]));
    }
    
    
// run sample games with output and the given random number generator
    public static void runSampleGame(Random randNumGen) 
    {
        System.out.println("RUNNING SAMPLE GAMES WITH OUTPUT");
        playGame(randNumGen, SCORE_STOPS_TURN, ROLLS_STOPS_TURN, true);
        System.out.println();
    }
    
    
// run various strategies against each other
    public static void runExperiments(Random randNumGen) 
    {
        System.out.println("RUNNING EXPERIMENTS (10,000 GAMES EACH) WITH NO OUTPUT");
        System.out.println();
        System.out.println("player 1 and player 2 stop at " 
                + SCORE_LIMIT_FOR_TURN + " points for turn");
        performExperiment(randNumGen, SCORE_STOPS_TURN , SCORE_STOPS_TURN);
        System.out.println("player 1 and player 2 stop at " 
                + ROLLS_LIMIT_FOR_TURN + " rolls for turn");
        performExperiment(randNumGen, ROLLS_STOPS_TURN , ROLLS_STOPS_TURN);
        System.out.println("player 1 stops at " + SCORE_LIMIT_FOR_TURN  + 
                " points and player 2 stops at " + ROLLS_LIMIT_FOR_TURN + " rolls");
        performExperiment(randNumGen, SCORE_STOPS_TURN , ROLLS_STOPS_TURN);
        System.out.println("player 1 stops at " + ROLLS_LIMIT_FOR_TURN 
                + " rolls and player 2 stops at " + SCORE_LIMIT_FOR_TURN + " points");
        performExperiment(randNumGen, ROLLS_STOPS_TURN , SCORE_STOPS_TURN );  
    }
    
// Run 10,000 games of pig using the given strategies.
// After running the games print the number of wins for player 1 and player 2.
// There is no output for individual games or turns.
    public static void performExperiment(Random randNumGen, int strategy1, int strategy2) 
    {
    	int player1Wins = 0;
    	int player2Wins = 0;
        // CS312 students, complete this method
        for (int repeatGames = 1; repeatGames <= 10000; repeatGames++)
        {
        	//player 1 and 2 both use strategy 1
        	//boolean false parameter will not print out statements and just increment wins
        	boolean winner = playGame(randNumGen, strategy1, strategy2, false);
        	if (winner == true)
        	{
        		player1Wins++;
        	}
        	else
        	{
        		player2Wins++;
        	}
        }
        System.out.println("player 1 wins: " + player1Wins);
        System.out.println("player 2 wins: " + player2Wins);
        System.out.println();
    }


// play one game.
// The method returns true if player 1 wins the game, false if player 2 wins the game.
// alternate player's taking turns 
// play until one player exceeds the goal
// Simply swaps players back and forth after a turn.
    public static boolean playGame(Random randNumGen, int strategy1, int strategy2, boolean showOutput) 
    {
        // CS312 students, complete this method
    	if (showOutput == true)
    	{
    		System.out.println("***** START OF GAME *****");
    		System.out.println();
    	}
    	int score1 = 0;
    	int score2 = 0;
    	int turnNumber = 1;
    	int playerNumber = 1;
    	boolean player1 = false;
    	boolean player2 = true;
    	boolean game = false;
    	while (game == false)
    	{
    		if (showOutput == true)
        	{
    			System.out.println("START OF TURN NUMBER " + turnNumber);
    			System.out.println("It is player " + playerNumber + "'s turn.");
        	}
    		int turnTotal = 0;
    		int roll = 0;
    		int playerRollNumber = 1;
    		boolean player1HadTurn = false;
	    	while (player1 == false)
	    	{
	    		player1HadTurn = true;
	    		roll = rollTheDice(randNumGen, playerNumber, playerRollNumber, showOutput);
	    		int turnScore = 0;
	    		if (roll == 1)
	    		{
	    			pig(showOutput);
	    			playerNumber++;
	    			player1 = true;
	    			player2 = false;
	    		}
	    		else if (roll != 1)
	    		{
	    	    	turnScore += roll;
	    	    	turnTotal += turnScore;
	    	    	int scoreTotal = score1 + turnTotal;
	    	    	if (showOutput == true)
	    	    	{
	    	    		currentResults(playerRollNumber, turnTotal, playerNumber, score1, score2, scoreTotal);
	    	    	}
	    	    	if (strategy1 == SCORE_STOPS_TURN)
	    	    	{
	    	    		if ((turnTotal < SCORE_LIMIT_FOR_TURN) && (scoreTotal < 100))
		            	{
	    	    			rollAgain(showOutput);
		            	}
		            	else
		            	{
		            		score1 = endTurn(showOutput, score1, turnTotal);
		            		playerNumber++;
		        			player1 = true;
		        			player2 = false;
		        			game = checkForWinner(game, score1, score2);
		            	}
	    	    	}
	    	    	else if (strategy1 == ROLLS_STOPS_TURN)
	    	    	{
	    	    		if ((playerRollNumber < ROLLS_LIMIT_FOR_TURN) && (scoreTotal < 100))
		            	{
	    	    			rollAgain(showOutput);
		            	}
		            	else
		            	{
		            		score1 = endTurn(showOutput, score1, turnTotal);
		            		playerNumber++;
		        			player1 = true;
		        			player2 = false;
		        			game = checkForWinner(game, score1, score2);
		            	}
	    	    	}
	    	    	if (showOutput == true)
                	{
	    	    		System.out.println();
                	}
	            	playerRollNumber++;
	    		}
	    	}
	    	
	    	while (player2 == false && player1HadTurn == false)
	    	{
	    		roll = rollTheDice(randNumGen, playerNumber, playerRollNumber, showOutput);
	    		int turnScore = 0;
	    		if (roll == 1)
	    		{
	    			pig(showOutput);
	    			playerNumber--;
	    			player1 = false;
	    			player2 = true;
	    		}
	    		else if (roll != 1)
	    		{
	    			turnScore += roll;
	    	    	turnTotal += turnScore;
	            	int scoreTotal = score2 + turnTotal;
	            	if (showOutput == true)
	            	{
	            		currentResults(playerRollNumber, turnTotal, playerNumber, score1, score2, scoreTotal);
	            	}
	            	if (strategy2 == SCORE_STOPS_TURN)
	    	    	{
	    	    		if ((turnTotal < SCORE_LIMIT_FOR_TURN) && (scoreTotal < 100))
		            	{
	    	    			rollAgain(showOutput);
		            	}
		            	else
		            	{
		            		score2 = endTurn(showOutput, score2, turnTotal);
		            		playerNumber--;
		        			player1 = false;
		        			player2 = true;
		        			game = checkForWinner(game, score1, score2);
		            	}
	    	    	}
	    	    	else if (strategy2 == ROLLS_STOPS_TURN)
	    	    	{
	    	    		if ((playerRollNumber < ROLLS_LIMIT_FOR_TURN) && (scoreTotal < 100))
		            	{
	    	    			rollAgain(showOutput);
		            	}
		            	else
		            	{
		            		score2 = endTurn(showOutput, score2, turnTotal);
		            		playerNumber--;
		        			player1 = false;
		        			player2 = true;
		        			game = checkForWinner(game, score1, score2);
		            	}
	    	    	}
	            	if (showOutput == true)
	            	{
	            		System.out.println();
	            	}
	            	playerRollNumber++;
	    		}
	    	}
	    	if (game == false)
	    	{
    			turnNumber++;
	    	}
    	}
    	if (score1 >= 100)
    	{
    		if (showOutput == true)
        	{
    			winner(turnNumber, score1, score2);
    			System.out.println("player 1 won the game!");
        	}
    		return true;
    	}
    	else
    	{
    		if (showOutput == true)
        	{
    			winner(turnNumber, score1, score2);
    			System.out.println("player 2 won the game!");
        	}
    		return false;
    	}
    }

    // CS312 Students, add your methods here
    
/*
 * This method prints the pig line if a 1 is rolled.
 */
    public static void pig(boolean showOutput)
    {
    	if (showOutput == true)
    	{
			System.out.println("PIG! Score for turn is 0. TURN IS OVER");
			System.out.println();
    	}
    }
    
/*
 * This method increments the player's score (score1 and score2 are passed as parameters) by the turn's total
 * score if the player decides to hold and end his or her turn.
 */
    public static int endTurn(boolean showOutput, int score1, int turnTotal)
    {
    	if (showOutput == true)
    	{
			System.out.println("DECISION - HOLD - TURN IS OVER");
    	}
		score1 += turnTotal;
		return score1;
    }
    
/*
 * This method will print the player's decision to roll again if the player decides to.
 */
    public static void rollAgain(boolean showOutput)
    {
    	if (showOutput == true)
    	{
			System.out.println("DECISION - ROLL AGAIN");
    	}
    }
    
/*
 * This method will be called to check if the current player has enough points to win the game.
 */
    public static boolean checkForWinner(boolean game, int score1, int score2)
    {
    	if (score1 >= 100 || score2 >= 100)
		{
			game = true;
		}
    	return game;
    }
    
/*
 * This method will "roll the die" (randomly generate a number from 1-6)
 */
    public static int rollTheDice(Random randNumGen, int playerNumber, int playerRollNumber, boolean showOutput)
    {
    	int roll = randNumGen.nextInt(SIDES) + 1;
    	if (showOutput == true)
    	{
    		System.out.println("player " + playerNumber + " rolled a " + roll + " on roll number " + playerRollNumber);
    		System.out.println();
    	}
		return roll;
    }
    
/*
 * This method will update the current scores of the game after each roll.
 */
    public static void currentResults(int playerRollNumber, int turnTotal, int playerNumber, int score1, int score2, int scoreTotal)
    {
		System.out.println("Number of rolls this turn: " + playerRollNumber);
		System.out.println("Score for turn so far: " + turnTotal);
		System.out.println("If you HOLD now scores will be: ");
		if (playerNumber == 2)
		{
    	System.out.println("player 1: " + score1);
    	System.out.println("player 2: " + scoreTotal);
		}
		else
		{
			System.out.println("player 1: " + scoreTotal);
        	System.out.println("player 2: " + score2);
		}
    	System.out.println();
    }
    
/*
 * This method will output the final results of the game after one of the players win.
 */
    public static void winner(int turnNumber, int score1, int score2)
    {
    	System.out.println("FINAL RESULTS: ");
    	System.out.println("Number of turns: " + turnNumber);
    	System.out.println("player 1 score: " + score1);
    	System.out.println("player 2 score: " + score2);
    }

// print out the intro to the game
    public static void intro() 
    {
        System.out.println("Welcome to the dice game Pig.");
        System.out.println("The game is played between two players.");
        System.out.println("Players alternate taking turns.");
        System.out.println();
        System.out.println("During a turn a player rolls a six sided die.");
        System.out.println("If the result of the roll is a 1 the player's score");
        System.out.println("for the turn is 0 and it is the next player's turn.");
        System.out.println("If the result of the die is not a 1 the result is added");
        System.out.println("to the player's score for the turn.");
        System.out.println();
        System.out.println("After the first roll of a turn a player must decide to");
        System.out.println("HOLD or ROLL AGAIN. If a player HOLDs their score");
        System.out.println("for the turn is added to their total score.");
        System.out.println("if a player ROLLs AGAIN they roll the die again.");
        System.out.println();
        System.out.println("The same rules apply to the second and subsequent");
        System.out.println("rolls in the turn: a 1 reduces the player's");
        System.out.println("score for the turn to 0 and the turn is over.");
        System.out.println("If the die is not 1 the result is added to the");
        System.out.println("player's score for the turn and they must decide");
        System.out.println("to HOLD or ROLL AGAIN.");
        System.out.println();
        System.out.println("The first player to reach a total of 100 wins the game.");
        System.out.println();
        System.out.println("This program simulates the results of different");
        System.out.println("strategies for the game.");
        System.out.println();
    }
    
// CS312 Students, questions to answer:

/*
1. Change the initial seed for the random number generator. 
Are the new results of the experiments similar to the results with the given 
seed of 2000316?

	Yes, the new results are similar to the given seed of 2000316 (the three given seeds for example,
	counted player wins that were all within a range of around 100 wins with each other).

2. When the player's use the same strategy, is going first an advantage?

	Yes, going first is an advantage because the player that goes first always seems to win when the same 
	strategies are used regardless of the seed value.

3. Which is better, the stop at 15 points or stop at 5 rolls strategy?

	The stop at 5 rolls strategy was better because in the experiments, when the first player used the stop
	at 15 points strategy, the difference between the two players' wins were small (around a 100) but when
	the first player used the stop at 5 rolls strategy, the first player won by a fairly large amount than the
	second player did (at least more than a 500 win difference).

4. Try different values for the number of points required to stop for a turn and 
the number of rolls necessary to stop for a turn. Are 15 points and 5 rolls the
most effective values for those strategies?

	No, those values are not the most effective because the chances to win with the first player rolling first are
	increased when the value of the score limit for points was around 20-25 and when the roll limit for consecutive rolls was 7-9.
	The game is still biased towards the player rolling first no matter the change in strategy limits, though.

5. Describe an alternative strategy. Something different than stopping at a fixed score
or number of rolls for a turn.

	An alternative strategy would be to roll until the score was divisible by the number of the roll which gives a 
	integer (EXAMPLE: player rolled a total score of 15 after 3 rolls. Does 15/3 give an integer? Yes. Decision - HOLD).
*/

}