import java.io.*;
import java.util.*;

public class Passwords{
	public static int[][] followerTable = new int[26][26];
	public static int[] counts = new int[26];
	public static int[] starters = new int[26];

	public static void main(String[] args) throws IOException{
		try{
			String referenceFileName = args[0];
			String str_numberofPasswords = args[1];
			String str_passwordLength = args[2];
			int numberOfPasswords = Integer.parseInt(str_numberofPasswords);
			int passwordLength = Integer.parseInt(str_passwordLength);

			File referenceFile = new File(referenceFileName);
			Scanner referenceFileScanner = new Scanner(referenceFile);

			while(referenceFileScanner.hasNext()){
				String nextLine = referenceFileScanner.nextLine();
				nextLine = nextLine.toLowerCase();

				Scanner nextLineScanner = new Scanner(nextLine);
				while(nextLineScanner.hasNext()){
					String word = nextLineScanner.next();

					for (int i = 0; i < word.length(); i++) {
						char c = word.charAt(i);


						if ((((int)c) >= 97) && ((int)c <= 122)) {
							//store starting letters
							if (i == 0) {
								starters[((int)c) - 97]++;
							}

							if (i < (word.length() - 1)) {
								char follower = word.charAt(i + 1);
								if ((((int)follower) >= 97) && ((int)follower <= 122)) {
									int row = (int)c - 97;
									int column = (int)follower - 97;
									followerTable[row][column]++;
								}
							
							
							}
						}

						
					}
				}

			}
			printArray(followerTable);		//print the array

			//storing the counts
			for (int i = 0; i < followerTable.length; i++) {
				int total = 0;
				for (int j = 0; j < followerTable[0].length; j++) {
					total += followerTable[i][j];
					
				}
				counts[i] = total;
			}
			// System.out.println(Arrays.toString(counts));		//testing
			// System.out.println(Arrays.toString(starters));		//testing

			/*------------------------generate password------------------------------------*/
			//generate first letter
			System.out.println("Passwords are: ");
			for (int round = 0; round < numberOfPasswords; round++) {
				List<String> startingLetterPool = getPool(starters);
				Random rm = new Random();			//*******************put seed if necessary
				int indexForStartingLetterPool = rm.nextInt(startingLetterPool.size());
				String firstLetter = startingLetterPool.get(indexForStartingLetterPool);

				//generate the inner letters
				char currentLetter = firstLetter.charAt(0);
				for (int i = 0; i < (passwordLength - 1); i++) {
					int[] row = followerTable[((int)currentLetter) - 97];
					List<String> middleLetterPool = getPool(row);
					int indexForMiddleLetterPool = rm.nextInt(middleLetterPool.size());
					String middleLetter = middleLetterPool.get(indexForMiddleLetterPool);
					firstLetter += middleLetter;
					currentLetter = middleLetter.charAt(0);

				}
				System.out.println(" " + firstLetter);
			}

		}catch(IOException e){
			System.out.println("Check the file names again!!!");
		}





	}

	public static void printArray(int[][] grid){
		System.out.print("  ");
		for (int i = 97; i <= 122; i++) {
			System.out.print(String.format("%5s ", (char)i));
		}
		System.out.println();

		for (int row = 0; row < grid.length; row++) {
			System.out.print((char)(row + 97) + " ");

			for (int column = 0; column < grid[0].length; column++) {
				System.out.print(String.format("%5s ", grid[row][column]));
			}
			System.out.println();
		}
	}

	//for 1d array
	public static int totalValue_1(int[] tmp){
		int total = 0;
		for (int i = 0; i < tmp.length; i++) {
			total += tmp[i];
		}

		return total;
	}

	public static List<String> getPool(int[] frequency){
		List<String> newPool = new ArrayList<String>();
		for (int i = 0; i < frequency.length; i++) {
			int numOccurs = frequency[i];
			for (int j = 0; j < numOccurs; j++) {
				newPool.add("" + (char)(i + 97));
			}
		}

		return newPool;
	}
}