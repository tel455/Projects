import java.io.*;
import java.util.*;

public class AES{
	public static String encryptionOutput = "";
	public static String decryptionOutput = "";
	public static String[] validHax = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", 
										"b", "c", "d", "e", "f", "A", "B", "C", "D", "E", "F"};

	public static void main(String[] args) throws IOException{
		//timing
		long start = System.currentTimeMillis();

		try{
			String option = args[0];
			if (option.equals("e")) {		//for encryption
				String keyName = args[1];
				String plaintextName = args[2];

				File keyFile = new File(keyName);
				File plaintextFile = new File(plaintextName);
				Scanner plaintextScanner = new Scanner(plaintextFile);
				Scanner keyScanner = new Scanner(keyFile);

				Table s_box = new Table();
				Rcon rcon = new Rcon();

				String[][] key = new String[4][4];
				while(keyScanner.hasNext()){
					String nextLine = keyScanner.nextLine();
					key = parseKey(nextLine);
				}
				String[][] keyExpanded = keyExpansion(key, s_box, rcon);

				File oldOutputFile = new File(plaintextName + ".enc");
				oldOutputFile.delete();

				while(plaintextScanner.hasNext()){
					String nextLine = plaintextScanner.nextLine();
					int lineLength = nextLine.length();
					boolean isValid = true;

					if ((lineLength == 32) && isValid) {
						encryption(nextLine, s_box, rcon, keyExpanded, plaintextName);
					}else if((lineLength < 32) && isValid){
						while(lineLength < 32){
							nextLine += "0";
							lineLength++;
						}
						encryption(nextLine, s_box, rcon, keyExpanded, plaintextName);
					}else if((lineLength > 32) && isValid){
						nextLine = nextLine.substring(0, 32);
						encryption(nextLine, s_box, rcon, keyExpanded, plaintextName);
					}
					
				}

			}else if(option.equals("d")){	//for decryption
				String keyName = args[1];
				String encryptedTextName = args[2];

				File keyFile = new File(keyName);
				File encryptedTextFile = new File(encryptedTextName);
				Scanner encryptedTextScanner = new Scanner(encryptedTextFile);
				Scanner keyScanner = new Scanner(keyFile);

				Table s_box = new Table();
				InverseTable s_box_inverse = new InverseTable();
				Rcon rcon = new Rcon();

				String[][] key = new String[4][4];
				while(keyScanner.hasNext()){
					String nextLine = keyScanner.nextLine();
					key = parseKey(nextLine);
				}
				String[][] keyExpanded = keyExpansion(key, s_box, rcon);

				File oldOutputFile = new File(encryptedTextName + ".dec");
				oldOutputFile.delete();

				while(encryptedTextScanner.hasNext()){
					String nextLine = encryptedTextScanner.nextLine();
					int lineLength = nextLine.length();
					boolean isValid = true;

					
					if ((lineLength == 32) && isValid) {
						decryption(nextLine, s_box_inverse, rcon, keyExpanded, encryptedTextName);
					}else if((lineLength < 32) && isValid){
						while(lineLength < 32){
							nextLine += "0";
							lineLength++;
						}
						decryption(nextLine, s_box_inverse, rcon, keyExpanded, encryptedTextName);
					}else if((lineLength > 32) && isValid){
						nextLine = nextLine.substring(0, 32);
						decryption(nextLine, s_box_inverse, rcon, keyExpanded, encryptedTextName);
					}
				}

			}	

			long end = System.currentTimeMillis();
			System.out.println(end - start + " ms");
		}catch(IOException e){
			System.out.println("Check the file names again!!!");
		}
	}

	//does the 4 encrypting steps
	public static void encryption(String plainLine, Table s_box, Rcon rcon, String[][] keyExpanded, String plaintextName){

		encryptionOutput = "";

		//9 main rounds
		String[][] state = new String[4][4];
		for (int round = 1; round < 10; round++) {
			//1st step
			state = subBytes(plainLine, s_box);
			//2nd step
			state = shiftRows(state);	
			//3rd step
			state = mixColumn(state);
			//4th step
			state = addRoundKey(state, keyExpanded, round + round * 3);

			//change the array back to text
			plainLine = "";
			for (int column = 0; column < 4; column++) {
				for (int row = 0; row < 4; row++) {
					plainLine += state[row][column];
				}
			}
		}

		//last round
		state = subBytes(plainLine, s_box);
		state = shiftRows(state);
		state = addRoundKey(state, keyExpanded, 40);

		for (int column = 0; column < 4; column++) {
				for (int row = 0; row < 4; row++) {
					encryptionOutput += state[row][column];
				}
		}


		//writing to the file
		encryptionOutput = encryptionOutput.toUpperCase();
		String outputFileName = plaintextName + ".enc";

		try{
			FileWriter fw = new FileWriter(outputFileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(encryptionOutput + "\n");
            bw.close();
		}catch(Exception e){
			System.out.println(e.getMessage());		
		}
		
	}

	public static void decryption(String plainLine, InverseTable s_box_inverse, Rcon rcon, String[][] keyExpanded, String encryptedTextName){
		decryptionOutput = "";

		String[] tmp = new String[16];
		String[][] state = new String[4][4];
		int endIndex = 2;
		int tmpIndex = 0;
		String twoChars = "";

		//parse the line into bytes
		for (int i = 0; i < plainLine.length(); i+=2) {
			if (i <= 30) {
				twoChars = plainLine.substring(i, endIndex);
			}else{
				twoChars = plainLine.substring(i);
			}
			
			endIndex+=2;
			tmp[tmpIndex] = twoChars;
			tmpIndex++;
		}


		//fill the array in column-major order
		int tmpIndex2 = 0;
		for (int column = 0; column < 4; column++) {
			for (int row = 0; row < 4; row++) {
				state[row][column] = tmp[tmpIndex2];
				tmpIndex2++;
			}
		}


		state = addRoundKey(state, keyExpanded, 40);
		state = shiftRows_inverse(state);
		state = subBytes_inverse(plainLine, s_box_inverse, state);

		for (int round = 9; round >= 1; round--) {
			state = addRoundKey(state, keyExpanded, round + round * 3);
			state = mixColumn_inverse(state);
			state = shiftRows_inverse(state);

			for (int column = 0; column < 4; column++) {
				for (int row = 0; row < 4; row++) {
					plainLine += state[row][column];
				}
			}
			state = subBytes_inverse(plainLine, s_box_inverse, state);
		}

		for (int column = 0; column < 4; column++) {
				for (int row = 0; row < 4; row++) {
					decryptionOutput += state[row][column];
				}
		}

		//writing to the file
		decryptionOutput = decryptionOutput.toUpperCase();
		String outputFileName = encryptedTextName + ".dec";
		try{
			FileWriter fw = new FileWriter(outputFileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(decryptionOutput + "\n");
            bw.close();
		}catch(Exception e){
			System.out.println(e.getMessage());		
		}
	}

	public static String[][] parseKey(String keyLine){
		String[] tmp = new String[16];
		String[][] key = new String[4][4];
		int endIndex = 2;
		int tmpIndex = 0;
		String twoChars = "";

		//parse the line into bytes
		for (int i = 0; i < keyLine.length(); i+=2) {
			if (i <= 30) {
				twoChars = keyLine.substring(i, endIndex);
			}else{
				twoChars = keyLine.substring(i);
			}
			
			endIndex+=2;
			tmp[tmpIndex] = twoChars;
			tmpIndex++;
		}

		//fill the array in column-major order
		int tmpIndex2 = 0;
		for (int column = 0; column < 4; column++) {
			for (int row = 0; row < 4; row++) {
				key[row][column] = tmp[tmpIndex2];
				tmpIndex2++;
			}
		}

		return key;
	}


	public static String[][] subBytes(String plainLine, Table s_box){
		String[] tmp = new String[16];
		String[][] state = new String[4][4];
		int endIndex = 2;
		int tmpIndex = 0;
		String twoChars = "";

		//parse the line into bytes
		for (int i = 0; i < plainLine.length(); i+=2) {
			if (i <= 30) {
				twoChars = plainLine.substring(i, endIndex);
			}else{
				twoChars = plainLine.substring(i);
			}
			
			endIndex+=2;
			tmp[tmpIndex] = twoChars;
			tmpIndex++;
		}


		//fill the array in column-major order
		int tmpIndex2 = 0;
		for (int column = 0; column < 4; column++) {
			for (int row = 0; row < 4; row++) {
				state[row][column] = tmp[tmpIndex2];
				tmpIndex2++;
			}
		}

		//replacement
		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				String originalValue = state[row][column];
				state[row][column] = s_box.getValue(originalValue);
			}
		}

		return state;
	}

	public static String[][] subBytes_inverse(String plainLine, InverseTable s_box_inverse, String[][] state){
		//replacement
		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				String originalValue = state[row][column];
				state[row][column] = s_box_inverse.getValue(originalValue);
			}
		}


		return state;
	}


	public static String[][] shiftRows(String[][] state){
		String[][] stateShifted = new String[state.length][state[0].length];	//always 4*4
		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				stateShifted[row][column] = state[row][(column + row) % state[row].length];
			}
		}
		return stateShifted;
	}

	public static String[][] shiftRows_inverse(String[][] state){
		String[][] stateShifted = new String[state.length][state[0].length];	//always 4*4
		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				if (row == 1) {
					stateShifted[row][column] = state[row][(column + 3) % 4];
				}else if (row == 2){
					stateShifted[row][column] = state[row][(column + 2) % 4];
				}else if(row == 3){
					stateShifted[row][column] = state[row][(column + 1) % 4];
				}else if(row == 0){
					stateShifted[row][column] = state[row][column];
				}
				
			}
		}
		return stateShifted;
	}


	public static String[][] mixColumn(String[][] state){
		//convert from hex string to byte
		byte[][] stateInBytes = new byte[4][4];
		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				stateInBytes[row][column] = (byte)(Integer.parseInt(state[row][column], 16) & 0xff);
			}
		}

		for (int column = 0; column < 4; column++) {
			stateInBytes = mixColumn2(column, stateInBytes);
		}

		//convert from byte to hex string
		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				state[row][column] = String.format("%02x", stateInBytes[row][column]);
			}
		}

		return state;
	}

	public static String[][] mixColumn_inverse(String[][] state){

		//convert from hex string to byte
		byte[][] stateInBytes = new byte[4][4];
		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				stateInBytes[row][column] = (byte)(Integer.parseInt(state[row][column], 16) & 0xff);
			}
		}

		for (int column = 0; column < 4; column++) {
			stateInBytes = invMixColumn2(column, stateInBytes);
		}

		//convert from byte to hex string
		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				state[row][column] = String.format("%02x", stateInBytes[row][column]);
			}
		}

		return state;
	}


	public static String[][] keyExpansion(String[][] key, Table s_box, Rcon rcon){
		String[][] w_str = new String[4][4 * (10+1)];
		byte[][] expandedKey = keyExpansion_helper(key, w_str, s_box, rcon);

		//convert from byte to hex string
		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				w_str[row][column] = String.format("%02x", expandedKey[row][column]);
			}
		}

		return w_str;
	}


	public static byte[][] keyExpansion_helper(String[][] k_str, String[][] w_str, 
		Table s_box, Rcon rcon){
		int i;
		int j;
		byte k[][] = new byte[4][4];
		byte w[][] = new byte[4][4 * (10+1)];

		for (j = 0; j < 4; j++) {
			for (i = 0; i < 4; i++) {
				w_str[i][j] = k_str[i][j];
			}
		}

		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				k[row][column] = (byte)(Integer.parseInt(k_str[row][column], 16) & 0xff);
				w[row][column] = (byte)(Integer.parseInt(w_str[row][column], 16) & 0xff);
			}
		}

		for (j = 4; j < (4 * (10+1)); j++) {
			if ((j % 4) == 0) {
				w[0][j] = (byte)(w[0][j - 4] ^ (byte)(Integer.parseInt(s_box.getValue("" + w_str[1][j - 1]), 16) & 0xff) ^ rcon.getRconValue(j/4, 0));
				w_str[0][j] = String.format("%02x", w[0][j]);

				for (i = 1; i < 4; i++) {
					w[i][j] = (byte)(w[i][j - 4] ^ (byte)(Integer.parseInt(s_box.getValue("" + w_str[(i + 1) % 4][j - 1]), 16) & 0xff));
					w_str[i][j] = String.format("%02x", w[i][j]);
				}
			}else{
				for (i = 0; i < 4; i++) {
					w[i][j] = (byte)(w[i][j - 4] ^ w[i][j - 1]);
					w_str[i][j] = String.format("%02x", w[i][j]);
				}
			}
		}

		return w;
	}


	public static String[][] addRoundKey(String[][] state, String[][] keyExpanded, int startingColumn){
		//convert from hex string to byte
		byte[][] stateInBytes = new byte[4][4];
		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				stateInBytes[row][column] = (byte)(Integer.parseInt(state[row][column], 16) & 0xff);
			}
		}
		byte[][] keyExpandedInbytes = new byte[keyExpanded.length][keyExpanded[0].length];
		for (int row = 0; row < keyExpandedInbytes.length; row++) {
			for (int column = 0; column < keyExpandedInbytes[0].length; column++) {
				keyExpandedInbytes[row][column] = (byte)(Integer.parseInt(keyExpanded[row][column], 16) & 0xff);
			}
		}

		for (int column = 0; column < 4; column++) {
			for (int row = 0; row < 4; row++) {
				stateInBytes[row][column] = (byte)(stateInBytes[row][column] ^ keyExpandedInbytes[row][startingColumn]);
			}
			startingColumn++;
		}

		//convert from byte to hex string
		for (int row = 0; row < 4; row++) {
			for (int column = 0; column < 4; column++) {
				state[row][column] = String.format("%02x", stateInBytes[row][column]);
			}
		}

		return state;
	}

	public static void writeOutput(String outputString, int flag){

	}


	//testing method to print the 2d array of string
	public static void printGrid(String[][] grid){
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[0].length; column++) {
				System.out.print(grid[row][column] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	//testing method to print the 2d array of bytes
	public static void printBytesGrid(byte[][] grid){
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[0].length; column++) {
				System.out.print(grid[row][column] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}


	/*------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------*/
	/*------------------------------------------------------------------------------------*/


    ////////////////////////  the mixColumns Tranformation ////////////////////////


    final static int[] LogTable = {
	0,   0,  25,   1,  50,   2,  26, 198,  75, 199,  27, 104,  51, 238, 223,   3, 
	100,   4, 224,  14,  52, 141, 129, 239,  76, 113,   8, 200, 248, 105,  28, 193, 
	125, 194,  29, 181, 249, 185,  39, 106,  77, 228, 166, 114, 154, 201,   9, 120, 
	101,  47, 138,   5,  33,  15, 225,  36,  18, 240, 130,  69,  53, 147, 218, 142, 
	150, 143, 219, 189,  54, 208, 206, 148,  19,  92, 210, 241,  64,  70, 131,  56, 
	102, 221, 253,  48, 191,   6, 139,  98, 179,  37, 226, 152,  34, 136, 145,  16, 
	126, 110,  72, 195, 163, 182,  30,  66,  58, 107,  40,  84, 250, 133,  61, 186, 
	43, 121,  10,  21, 155, 159,  94, 202,  78, 212, 172, 229, 243, 115, 167,  87, 
	175,  88, 168,  80, 244, 234, 214, 116,  79, 174, 233, 213, 231, 230, 173, 232, 
	44, 215, 117, 122, 235,  22,  11, 245,  89, 203,  95, 176, 156, 169,  81, 160, 
	127,  12, 246, 111,  23, 196,  73, 236, 216,  67,  31,  45, 164, 118, 123, 183, 
	204, 187,  62,  90, 251,  96, 177, 134,  59,  82, 161, 108, 170,  85,  41, 157, 
	151, 178, 135, 144,  97, 190, 220, 252, 188, 149, 207, 205,  55,  63,  91, 209, 
	83,  57, 132,  60,  65, 162, 109,  71,  20,  42, 158,  93,  86, 242, 211, 171, 
	68,  17, 146, 217,  35,  32,  46, 137, 180, 124, 184,  38, 119, 153, 227, 165, 
	103,  74, 237, 222, 197,  49, 254,  24,  13,  99, 140, 128, 192, 247, 112,   7};

    final static int[] AlogTable = {
	1,   3,   5,  15,  17,  51,  85, 255,  26,  46, 114, 150, 161, 248,  19,  53, 
	95, 225,  56,  72, 216, 115, 149, 164, 247,   2,   6,  10,  30,  34, 102, 170, 
	229,  52,  92, 228,  55,  89, 235,  38, 106, 190, 217, 112, 144, 171, 230,  49, 
	83, 245,   4,  12,  20,  60,  68, 204,  79, 209, 104, 184, 211, 110, 178, 205, 
	76, 212, 103, 169, 224,  59,  77, 215,  98, 166, 241,   8,  24,  40, 120, 136, 
	131, 158, 185, 208, 107, 189, 220, 127, 129, 152, 179, 206,  73, 219, 118, 154, 
	181, 196,  87, 249,  16,  48,  80, 240,  11,  29,  39, 105, 187, 214,  97, 163, 
	254,  25,  43, 125, 135, 146, 173, 236,  47, 113, 147, 174, 233,  32,  96, 160, 
	251,  22,  58,  78, 210, 109, 183, 194,  93, 231,  50,  86, 250,  21,  63,  65, 
	195,  94, 226,  61,  71, 201,  64, 192,  91, 237,  44, 116, 156, 191, 218, 117, 
	159, 186, 213, 100, 172, 239,  42, 126, 130, 157, 188, 223, 122, 142, 137, 128, 
	155, 182, 193,  88, 232,  35, 101, 175, 234,  37, 111, 177, 200,  67, 197,  84, 
	252,  31,  33,  99, 165, 244,   7,   9,  27,  45, 119, 153, 176, 203,  70, 202, 
	69, 207,  74, 222, 121, 139, 134, 145, 168, 227,  62,  66, 198,  81, 243,  14, 
	18,  54,  90, 238,  41, 123, 141, 140, 143, 138, 133, 148, 167, 242,  13,  23, 
	57,  75, 221, 124, 132, 151, 162, 253,  28,  36, 108, 180, 199,  82, 246,   1};

    private static byte mul (int a, byte b) {
	int inda = (a < 0) ? (a + 256) : a;
	int indb = (b < 0) ? (b + 256) : b;

	if ( (a != 0) && (b != 0) ) {
	    int index = (LogTable[inda] + LogTable[indb]);
	    byte val = (byte)(AlogTable[ index % 255 ] );
	    return val;
	}
	else 
	    return 0;
    } // mul

    // In the following two methods, the input c is the column number in
    // your evolving state matrix st (which originally contained 
    // the plaintext input but is being modified).  Notice that the state here is defined as an
    // array of bytes.  If your state is an array of integers, you'll have
    // to make adjustments. 

    public static byte[][] mixColumn2 (int c, byte[][] stateInBytes) {
		// This is another alternate version of mixColumn, using the 
		// logtables to do the computation.
	
		byte a[] = new byte[4];
	
		// note that a is just a copy of st[.][c]
		for (int i = 0; i < 4; i++) 
	    	a[i] = stateInBytes[i][c];
	
		// This is exactly the same as mixColumns1, if 
		// the mul columns somehow match the b columns there.
		stateInBytes[0][c] = (byte)(mul(2,a[0]) ^ a[2] ^ a[3] ^ mul(3,a[1]));
		stateInBytes[1][c] = (byte)(mul(2,a[1]) ^ a[3] ^ a[0] ^ mul(3,a[2]));
		stateInBytes[2][c] = (byte)(mul(2,a[2]) ^ a[0] ^ a[1] ^ mul(3,a[3]));
		stateInBytes[3][c] = (byte)(mul(2,a[3]) ^ a[1] ^ a[2] ^ mul(3,a[0]));

		return stateInBytes;
    } // mixColumn2

    public static byte[][] invMixColumn2 (int c, byte[][] stateInBytes) {
		byte a[] = new byte[4];
	
		// note that a is just a copy of st[.][c]
		for (int i = 0; i < 4; i++) 
	    	a[i] = stateInBytes[i][c];
	
		stateInBytes[0][c] = (byte)(mul(0xE,a[0]) ^ mul(0xB,a[1]) ^ mul(0xD, a[2]) ^ mul(0x9,a[3]));
		stateInBytes[1][c] = (byte)(mul(0xE,a[1]) ^ mul(0xB,a[2]) ^ mul(0xD, a[3]) ^ mul(0x9,a[0]));
		stateInBytes[2][c] = (byte)(mul(0xE,a[2]) ^ mul(0xB,a[3]) ^ mul(0xD, a[0]) ^ mul(0x9,a[1]));
		stateInBytes[3][c] = (byte)(mul(0xE,a[3]) ^ mul(0xB,a[0]) ^ mul(0xD, a[1]) ^ mul(0x9,a[2]));
     
     	return stateInBytes;
     } // invMixColumn2



}