import java.io.*;
import java.util.*; 

public class CovertChannel{
	public static void main(String [] args) throws IOException{
		ReferenceMonitor rm = new ReferenceMonitor();		//one reference monitor
		SecurityLevel high = new SecurityLevel("HIGH");		//HIGH security level
		SecurityLevel low = new SecurityLevel("LOW");		//LOW security level

		Subjects lyle = new Subjects("lyle");
		rm.addSubjects(lyle, low);
		Subjects hal = new Subjects("hal");
		rm.addSubjects(hal, high);

		List<String> parsedWord = new ArrayList<String>();	//stores the words on a single line

		List<String> lyleInstruction_1 = new ArrayList<String>();
		lyleInstruction_1.add("create");
		lyleInstruction_1.add("lyle");
		lyleInstruction_1.add("obj");
		List<String> lyleInstruction_2 = new ArrayList<String>();
		lyleInstruction_2.add("write");
		lyleInstruction_2.add("lyle");
		lyleInstruction_2.add("obj");
		lyleInstruction_2.add("1");
		List<String> lyleInstruction_3 = new ArrayList<String>();
		lyleInstruction_3.add("read");
		lyleInstruction_3.add("lyle");
		lyleInstruction_3.add("obj");
		List<String> lyleInstruction_4 = new ArrayList<String>();
		lyleInstruction_4.add("destroy");
		lyleInstruction_4.add("lyle");
		lyleInstruction_4.add("obj");
		List<String> lyleInstruction_5 = new ArrayList<String>();
		lyleInstruction_5.add("run");
		lyleInstruction_5.add("lyle");

		int totalBits = 0;

		try{
			if (args.length == 1) {		//command without 'v'
				long starting = System.currentTimeMillis();		//timing start

				String targetFile = args[0];
				File file = new File(targetFile);
				rm.storeName(file.getName());
				File oldOutput = new File(file.getName() + ".out");
				oldOutput.delete();

				byte[] buffer = new byte[(int)file.length()];
				FileInputStream file_stream = new FileInputStream(file);    
				file_stream.read(buffer);

				for (int i = 0; i < buffer.length; i++) {
					int byteInput = buffer[i];
					for (int index = 7; index >= 0; index--) {

						int bit = (byteInput >> index) & 1;
						totalBits++;
						parsedWord = hal.sendInfo(bit);
						rm.checkSecurity(1, parsedWord);		//run for hal

						rm.checkSecurity(1, lyleInstruction_1);		//run for lyle
						rm.checkSecurity(1, lyleInstruction_2);		//run for lyle
						rm.checkSecurity(1, lyleInstruction_3);		//run for lyle
						rm.checkSecurity(1, lyleInstruction_4);		//run for lyle
						rm.checkSecurity(1, lyleInstruction_5);		//run for lyle
					}
				}
				long ending = System.currentTimeMillis();
				long duration = ending - starting;
				int bandwidth = totalBits / (int)duration;
				System.out.println("Timing: " + duration + " ms" + " Bandwidth: " + bandwidth + " bits/ms");

			}else if(args.length == 2){		//for verbose
				String targetFile = args[1];
				File file = new File(targetFile);
				rm.storeName(file.getName());
				//check if an old output file exists
				File oldOutput = new File(file.getName() + ".out");
				oldOutput.delete();
				//check if an old log file exists
				File oldLog = new File("log");
				oldLog.delete();

				byte[] buffer = new byte[(int)file.length()];
				FileInputStream file_stream = new FileInputStream(file);    
				file_stream.read(buffer);

				for (int i = 0; i < buffer.length; i++) {
					int byteInput = buffer[i];
					for (int index = 7; index >= 0; index--) {

						int bit = (byteInput >> index) & 1;
						parsedWord = hal.sendInfo(bit);
						rm.checkSecurity(1, parsedWord);		//run for hal
						if (!(parsedWord.get(0).equals("nothing"))) {
							printLog(parsedWord);
						}
						
						rm.checkSecurity(1, lyleInstruction_1);		//run for lyle
						printLog(lyleInstruction_1);
						rm.checkSecurity(1, lyleInstruction_2);		//run for lyle
						printLog(lyleInstruction_2);
						rm.checkSecurity(1, lyleInstruction_3);		//run for lyle
						printLog(lyleInstruction_3);
						rm.checkSecurity(1, lyleInstruction_4);		//run for lyle
						printLog(lyleInstruction_4);
						rm.checkSecurity(1, lyleInstruction_5);		//run for lyle
						printLog(lyleInstruction_5);
					}
				}
			}
		}catch(IOException e){
			System.out.println("File not found!!!!!");
		}
	
	}

	public static void printLog(List<String> subjectInstruction){
		try{
			FileWriter fw = new FileWriter("log", true);
            BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < subjectInstruction.size(); i++) {
				String tmp = subjectInstruction.get(i);
				
                bw.write(tmp + " ");
			}
			bw.write("\n");
			bw.close();
		}catch(Exception e){
			System.out.println(e.getMessage());		//test
		}

	}
}