import java.util.Scanner;
public class DeniseConnectFour
{
    public static final int ROWS = 6;
    public static final int COLS = 7;
    public static void main(String[] args)
    {
    	intro();
        Scanner key = new Scanner(System.in);
        System.out.print("Player 1 enter your name: ");
        String player1 = key.nextLine();
        System.out.print("Player 2 enter your name: ");
        String player2 = key.nextLine();
        currentBoard(key, player1, player2);
    }

// show the intro
    public static void intro() {
        System.out.println("This program allows two people to play the");
        System.out.println("game of Connect four. Each player takes turns");
        System.out.println("dropping a checker in one of the open columns");
        System.out.println("on the board. The columns are numbered 1 to 7.");
        System.out.println("The first player to get four checkers in a row");
        System.out.println("horizontally, vertically, or diagonally wins");
        System.out.println("the game. If no player gets fours in a row and");
        System.out.println("and all spots are taken the game is a draw.");
        System.out.println("Player one's checkers will appear as r's and");
        System.out.println("player two's checkers will appear as b's.");
        System.out.println("Open spaces on the board will appear as .'s.\n");
    }
    
//display initial board
//get user input, alternating from p1 to p2 and r's to b's
//update and display board
    public static void currentBoard(Scanner key, String player1, String player2)
    {
        char[][] board = new char[6][7];
        int count = 0;
        int[] placement = {7, 7, 7, 7, 7, 7, 7};
        int[] inColumns = new int[7];
        char[][] updatedBoard = new char[6][7];
        char piece = 'r';
        boolean winner = false;
        if (count == 0){
            board = initialBoard(board);
            System.out.println();
            System.out.println("Current Board");
            System.out.println("1 2 3 4 5 6 7  column numbers");
            printBoard(board);
            count++;
        } 
        while (winner == false)
        {
        	String player = playerTurn(count, player1, player2);
            piece = getPiece(count);
            System.out.println(player + " it is your turn.");
            System.out.println("Your pieces are the " + piece + "'s.");
            System.out.print(player + ", enter the column to drop your checker: ");
            
            int inputCol = input(key, player);
            inputCol = check(inputCol, key, player);
            int[] checkColumn = notInColumns(inColumns, inputCol);
            inputCol = checkFull(key, inputCol, checkColumn, player);
            updatedBoard = updateBoard(board, inputCol, count, placement, piece);

            count++;

            if(getWinnerInColumns(updatedBoard) == true || getWinnerInRows(updatedBoard) == true || southEastWin(updatedBoard) == true || southWestWin(updatedBoard) == true)
            {
                winner = true;
            	System.out.println(player + " wins!!");
                System.out.println();
                System.out.println("Final Board");
                System.out.println("1 2 3 4 5 6 7  column numbers");
                printBoard(updatedBoard);
            } 
            else 
            {
                System.out.println();
                System.out.println("Current Board");
                System.out.println("1 2 3 4 5 6 7  column numbers");
                printBoard(updatedBoard);
            }
        } 

    }
    
//This method decides the which player's turn it is.
    public static String playerTurn(int count, String player1, String player2)
    {
        String player = "";
        if (count%2 == 1)
        {
            player = player1;
        } 
        else
        	player = player2;
        return player;
    }

//This method decides which piece, r or b, will be used for the turn.
    public static char getPiece(int count)
    {
        if (count%2 == 1)
        {
            return 'r';
        } else 
        	return 'b';
    }

//This method initializes an empty board for a new game of connect four.
    public static char[][] initialBoard(char[][] board)
    {
        for ( int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++) 
            {
                board[i][j] = '.';
            }
        }
        return board;
    }

//This method prints out the playing board.
    public static void printBoard(char[][] board)
    {
        for(int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++) 
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

//This method updates the board with the user's input.
    public static char[][] updateBoard(char[][] board, int inputCol, int count, int[] placement, char piece)
    {
        if (count > 0)
        {
            board[placement[inputCol - 1] - 2][inputCol - 1] = piece;
            placement[inputCol - 1]--;
        }
        return board;
    }

    public static int[] notInColumns(int[] inColumns, int inputCol)
    {
        inColumns[inputCol-1]++;
        return inColumns;
    }

//This method checks if the user's input is an integer.
    public static int input(Scanner key, String player)
    {
        while(!(key.hasNextInt()))
        {
            System.out.println(key.next() + " is not an integer.");
            System.out.print(player + ", enter the column to drop your checker: ");
        }
        return key.nextInt();
    }

//This method checks if the user's input is within bounds of the 7 columns.
    public static int check(int inputCol, Scanner key, String player)
    {
        while(inputCol < 1 || inputCol > 7)
        {
            System.out.println(inputCol + " is not a valid column.");
            System.out.print(player + ", enter the column to drop your checker: ");
            inputCol = input(key, player);
        }
        return inputCol;
    } 

//This method will check the columns to see if they are already full.
    public static int checkFull(Scanner key, int inputCol, int[] checkColumn, String player)
    {
        while (checkColumn[inputCol - 1] == COLS)
        {
            System.out.println(inputCol + " is not a legal column. That column is full");
            System.out.print(player + ", enter the column to drop your checker: ");
            inputCol = input(key,player);
            inputCol = check(inputCol, key, player);
        }
        return inputCol;
    }
    
//This method checks the board to see if the there are 4 connected pieces in a vertical line to win.
    public static boolean getWinnerInColumns(char[][] updatedBoard) 
    {
        for (int column = 0; column < COLS; column++) 
        {
        	int count = 0;
            for (int row = 1; row < ROWS; row++) 
            {
                if (updatedBoard[row][column] != '.' && updatedBoard[row][column] == updatedBoard[row - 1][column])
                {
                    count++;
                }
                else
                {
                    count = 1;
                }
                if (count >= 4) 
                {
                    return true;
                }
            }
        }
        return false;
    }

//This method checks the board to see if the there are 4 connected pieces in horizontal line to win.
    public static boolean getWinnerInRows(char[][] updatedBoard) 
    {
        for (int row = 0; row < ROWS; row++) 
        {
            int count = 0;
            for (int column = 1; column < COLS; column++) 
            {
                if (updatedBoard[row][column] != '.' && updatedBoard[row][column] == updatedBoard[row][column - 1])
                {
                    count++;
                }
                else
                {
                    count = 1;
                }
                if (count >= 4) 
                {
                    return true;
                }
            }
        }
        return false;
    }
    
//This method checks the board to see if the there are 4 connected pieces in diagonal line going South-East to win.
    public static boolean southEastWin(char[][] updatedBoard) 
    {
    	//For South-East diagonals that start at the top of each column
	    for (int column = 0; column < COLS; column++) 
	    {
	        int count = 0;
	        for (int row = 1; row < ROWS; ++row) 
	        {
	            if (column + row < 6)
	            {
	            	if (updatedBoard[row][column + row] != '.' && updatedBoard[row - 1][column + row - 1] == updatedBoard[row][column + row])
	            	{
	            		count++;
	            	}
	            	else
	            		count = 1;
	            }
	            if (count >= 4) 
	            {
	            	return true;
	            }
	        }
	    }
	    
	    //For South-East diagonals that start on the left of each row
        for (int row = 0; row < ROWS; row++) 
        {
            int count = 0;
            for (int column = 1; column < COLS; column++) 
            {
                if (column + row < 6)
                {
	                if (updatedBoard[row + column][column] != '.' && updatedBoard[row+column - 1][column - 1] == updatedBoard[row + column][column])
	                {
	                    count++;
	                }
	                else
	                    count = 1;
                }
                if (count >= 4)
                { 
                	return true;
                }
            }
        }
        return false;
    }

//This method checks the board to see if the there are 4 connected pieces in diagonal line going South-West to win.
    public static boolean southWestWin(char[][] updatedBoard) 
    {
    	//For South-West diagonals that start at the top of each column
    	for (int column = 0; column < COLS; column++) 
    	{
            int count = 0;
            for (int row = 1; row < ROWS; row++) 
            {
                if (column - row >= 0)
                {
	                if (updatedBoard[row][column-row] != '.' && updatedBoard[row - 1][column - row + 1] == updatedBoard[row][column-row])
	                {
	                    count++;
	                }
	                else
	                    count = 1;
                }
                if (count >= 4) 
                {
                	return true;
                }
            }
        }

    	//For South-West diagonals that start on the right of each row
        for (int row = 0; row < ROWS; row++) {
            int count = 0;
            for (int column = COLS - 2; column >= 0; column--) 
            {
                if (column - row >= 0)
                {
	                if (updatedBoard[column - row][column] != '.' && updatedBoard[column - row - 1][column + 1] == updatedBoard[column - row][column])
	                {
	                    count++;
	                }
	                else
	                    count = 1;
                }
                if (count >= 4)
                {
                	return true;
                }
            }
        }
        return false;
    }
}