import java.io.*;
import java.util.*;

public class SecureSystem{
	public static void main(String [] args) throws IOException{
		ReferenceMonitor rm = new ReferenceMonitor();		//one reference monitor
		SecurityLevel high = new SecurityLevel("HIGH");		//HIGH security level
		SecurityLevel low = new SecurityLevel("LOW");		//LOW security level

		Subjects lyle = new Subjects("lyle");
		rm.addSubjects(lyle, low);
		Subjects hal = new Subjects("hal");
		rm.addSubjects(hal, high);

		Objects lobj = new Objects("lobj");
		rm.addObjects(lobj, low);
		Objects hobj = new Objects("hobj");
		rm.addObjects(hobj, high);


		List<String> parsedWord = new ArrayList<String>();	//stores the words on a single line
		List<String> results = new ArrayList<String>();
		try{
			String targetFile = args[0];
			File file = new File(targetFile);
			Scanner input = new Scanner(file);

			while(input.hasNext()){
				String nextLine = input.nextLine();
				InstructionObject io = new InstructionObject(nextLine);
				rm.addInstructionObject(io);
				results = rm.checkSecurity(0, null);
				printState(results);
			}

			input.close();
		}catch(IOException e){
			System.out.println("File not found!!!!!");
		}

	}


	public static void printState(List<String> results){
		for (int i = 0; i < 6; i++) {
			System.out.println(results.get(i));	

		}	
		System.out.println();
	}
}