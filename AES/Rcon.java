public class Rcon{
	private String[][] rconTable;

	Rcon(){
		rconTable = new String[][]{
			{"01", "00", "00", "00"},
			{"02", "00", "00", "00"},
			{"04", "00", "00", "00"},
			{"08", "00", "00", "00"},
			{"10", "00", "00", "00"},
			{"20", "00", "00", "00"},
			{"40", "00", "00", "00"},
			{"80", "00", "00", "00"},
			{"1b", "00", "00", "00"},
			{"36", "00", "00", "00"}
		};
	}

	public byte getRconValue(int r, int c){
		//convert from hex string to byte
		byte[][] rconInBytes = new byte[10][4];
		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 4; column++) {
				rconInBytes[row][column] = (byte)(Integer.parseInt(rconTable[row][column], 16) & 0xff);
			}
		}

		return rconInBytes[r - 1][c];
	}
}