import java.io.*;
import java.util.*;

public class Subjects{
	private String name;
	private int temp;
	private int outputByte;
	private int counter;

	Subjects(String x){
		name = x;
		temp = 0;
		outputByte = 0;
        counter = 7; //test
	}

	public String getName(){
		return name;
	}

	public void modifyTemp(int x){
		temp = x;
	}

	public int getTemp(){
		return temp;
	}

	public List<String> sendInfo(int bit){
		List<String> parsedWord = new ArrayList<String>();
		if (bit == 0) {
			//CREATE HAL OBJ
			parsedWord.add("create");
			parsedWord.add("hal");
			parsedWord.add("obj");
		}else{
			//does nothing
			parsedWord.add("nothing");
		}
		return parsedWord;
	}

	//specific to lyle
	public void run(String fileName){
		//rebuild char from bits
        if (counter >= 0) {
            outputByte |= (temp & 1) << counter;
            counter--;
            if(counter == -1){      //finished appending 8th bit, time to write!
                fileName = fileName + ".out";
                try{
                    FileWriter fw = new FileWriter(fileName, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write((char)outputByte);
                    bw.close();
                }catch(Exception e){
                    System.out.println(e.getMessage());		//test
                }
            }
        }else{
            counter = 7;
            outputByte = 0;
            outputByte |= (temp & 1) << counter;
            counter--;
        }
	}


}