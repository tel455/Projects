import java.io.*;
import java.util.*;

public class InstructionObject{
	private List<String> parsedWord;
	private boolean isGoodInstruction = false;

	InstructionObject(String nextLine){
		parsedWord = new ArrayList<String>();	//stores the words on a single line
		parsedWord = parseSingleLine(nextLine);
				if((parsedWord.get(0).equalsIgnoreCase("read") && (parsedWord.size() == 3)) 
				&& (parsedWord.get(1).equalsIgnoreCase("hal") || parsedWord.get(1).equalsIgnoreCase("lyle")) 
				&& (parsedWord.get(2).equalsIgnoreCase("lobj") || parsedWord.get(2).equalsIgnoreCase("hobj"))){		//test this
						isGoodInstruction = true;
	
				}else if((parsedWord.get(0).equalsIgnoreCase("write") && (parsedWord.size() == 4)) 
				&& (parsedWord.get(1).equalsIgnoreCase("hal") || parsedWord.get(1).equalsIgnoreCase("lyle")) 
				&& (parsedWord.get(2).equalsIgnoreCase("lobj") || parsedWord.get(2).equalsIgnoreCase("hobj")) 
				&& isInteger(parsedWord.get(3))){
						isGoodInstruction = true;
						
				}

				else{		//invalid instruction or length
					isGoodInstruction = false;
					
				}
	}

	public List<String> parseSingleLine(String nextLine){
		List<String> parsedWord = new ArrayList<String>();
		Scanner inputSingleLine = new Scanner(nextLine);
			while(inputSingleLine.hasNext()){
				String nextWord = inputSingleLine.next();
				parsedWord.add(nextWord);
			}
			inputSingleLine.close();
			return parsedWord;
	}

	public boolean isInteger(String token){
		try{
			Integer.parseInt(token);
			return true;
		}catch (NumberFormatException e){
			return false;
		}
	}

	public boolean isValid(){
		return isGoodInstruction;
	}

	public List<String> getParsedWord(){
		return parsedWord;
	}
}