import java.io.*;
import java.util.*;

public class ReferenceMonitor{
	private static List<Subjects> subjectsList;
	private static List<SecurityLevel> subjectsLevelList;
	private static List<SecurityLevel> objectsLevelList;
	private static ObjectManager om;
	private static InstructionObject io;
	private static List<String> results;
	private static String fileName;

	ReferenceMonitor(){
		om = new ObjectManager();

		subjectsList = new ArrayList<Subjects>();
		subjectsLevelList = new ArrayList<SecurityLevel>();
		objectsLevelList = new ArrayList<SecurityLevel>();
		results = new ArrayList<String>();

		//initial state
		results.add("");
		results.add("The current state is: ");
		results.add("   lobj: " + 0);
		results.add("   hobj: " + 0);
		results.add("   lyle read: " + 0);
		results.add("   hal read: " + 0);

		fileName = "";
	}

	//store both subject and its security level
	public static void addSubjects(Subjects x, SecurityLevel y){
		subjectsList.add(x);
		subjectsLevelList.add(y);
	}

	//store both subject and its security level
	public static void addObjects(Objects x, SecurityLevel y){
		om.storeObjects(x);
		objectsLevelList.add(y);
	}

	//store each instruction
	public static void addInstructionObject(InstructionObject x){
		io = x;
	}

	public static List<String> checkSecurity(int flag, List<String> parsedWord){
		
		
		if (flag == 0) {		//Get outside instruction mode
			if(io.isValid()){
				String instruction_string = io.getParsedWord().get(0);
				String targetSubject_string = io.getParsedWord().get(1);
				String targetObject_string = io.getParsedWord().get(2);
			
				Subjects targetSubject = findSubject(targetSubject_string);		//substitute(test)
				int indexOfSubject = subjectsList.indexOf(targetSubject);
				SecurityLevel subjectLevel = subjectsLevelList.get(indexOfSubject);

				Objects targetObject = findObject(targetObject_string);
				int indexOfObject = om.objectsList.indexOf(targetObject);
				SecurityLevel objectLevel = objectsLevelList.get(indexOfObject);

				//check read
				if(instruction_string.equalsIgnoreCase("read")){

					if (subjectLevel.getLevel() >= objectLevel.getLevel()) {		//allowing to read
						targetSubject.modifyTemp(om.read(indexOfObject));
						results.set(0, targetSubject_string + " reads " + targetObject_string);
						results.set(4, "   lyle read: " + subjectsList.get(0).getTemp());
						results.set(5, "   hal read: " + subjectsList.get(1).getTemp());
					
					}else{		//read denied
						targetSubject.modifyTemp(0);
						results.set(0, targetSubject_string + " reads " + targetObject_string);
						results.set(4, "   lyle read: " + targetSubject.getTemp());
						results.set(5, "   hal read: " + subjectsList.get(1).getTemp());
					}
				
					return results;
				}
				//check write
				else if(instruction_string.equalsIgnoreCase("write")){
					int targetValue = Integer.parseInt(io.getParsedWord().get(3));

					if (subjectLevel.getLevel() <= objectLevel.getLevel()) {	//allowing to write
						om.write(indexOfObject, targetValue);
						results.set(0, targetSubject_string + " writes " + targetValue + " to " + targetObject_string);
						results.set(2, "   lobj: " + om.objectsList.get(0).getValue());
						results.set(3, "   hobj: " + om.objectsList.get(1).getValue());
					}else{		//write denied
						results.set(0, targetSubject_string + " writes " + targetValue + " to " + targetObject_string);
					}

					return results;
				}
			

			}
			//for bad instruction
			results.set(0, "Bad Instruction");
			return results;

		}else if(flag == 1){		//Covert channel mode
			if (parsedWord.get(0).equals("nothing")) {		//case when bit is 1
				return results;	//does nothing
			}

			String instruction_string = parsedWord.get(0);
			String targetSubject_string = parsedWord.get(1);
			String targetObject_string = "";
			if (!(instruction_string.equalsIgnoreCase("run"))) {
				targetObject_string = parsedWord.get(2);
			}
			

			Subjects targetSubject = findSubject(targetSubject_string);		//substitute(test)
			int indexOfSubject = subjectsList.indexOf(targetSubject);
			SecurityLevel subjectLevel = subjectsLevelList.get(indexOfSubject);


			Objects targetObject = findObject(targetObject_string);
			//CREATE
			if (instruction_string.equalsIgnoreCase("create")) {
				if (targetObject == null) {		//needs to create a new object
					Objects obj = new Objects(targetObject_string);
					addObjects(obj, subjectLevel);
				}else{
					//do nothing
				}
			}
			//WRITE
			else if(instruction_string.equalsIgnoreCase("write")){
				int indexOfObject = om.objectsList.indexOf(targetObject);
				SecurityLevel objectLevel = objectsLevelList.get(indexOfObject);

				int targetValue = Integer.parseInt(parsedWord.get(3));
				if (subjectLevel.getLevel() <= objectLevel.getLevel()) {	//allowing to write
					om.write(indexOfObject, targetValue);
				}
			}
			//READ
			else if(instruction_string.equalsIgnoreCase("read")){
				int indexOfObject = om.objectsList.indexOf(targetObject);
				SecurityLevel objectLevel = objectsLevelList.get(indexOfObject);

				if (subjectLevel.getLevel() >= objectLevel.getLevel()) {	//allowing to read
					targetSubject.modifyTemp(om.read(indexOfObject));
				}else{									//read denied
					targetSubject.modifyTemp(0);
				}
			}
			//DESTROY
			else if(instruction_string.equalsIgnoreCase("destroy")){
				if (targetObject != null) {		//object exists
					int indexOfObject = om.objectsList.indexOf(targetObject);
					SecurityLevel objectLevel = objectsLevelList.get(indexOfObject);

					if (subjectLevel.getLevel() <= objectLevel.getLevel()) {	//subject can write to the object
						om.deleteObject(indexOfObject);	//deleting the actual object
						objectsLevelList.remove(indexOfObject);	//deleting the object's level
					}
					
				}
			}
			//RUN
			else if(instruction_string.equalsIgnoreCase("run")){
				targetSubject.run(fileName);		//might need more stuff
			}

			return results;		//not useful
		}

		return results;		//should never happen
		
	}
	//duplicate this for objects
	//also make a getter method for objectmanager
	public static Subjects findSubject(String targetSubject_string){
		for (int i = 0; i < subjectsList.size(); i++) {
			String tmpName = subjectsList.get(i).getName();
			if(tmpName.equals(targetSubject_string)){
				return subjectsList.get(i);
			}
		}
		return null;
	}

	public static Objects findObject(String targetObject_string){
		for (int i = 0; i < om.objectsList.size(); i++) {
			String tmpName = om.objectsList.get(i).getName();
			if(tmpName.equals(targetObject_string)){
				return om.objectsList.get(i);
			}
		}
		return null;
	}

	public void storeName(String name){
		fileName = name;
		
	}

	/*--------------------------------------------------------------------------------------------------*/
	//local class to store all objects
	public static class ObjectManager{
		private static List<Objects> objectsList;

		ObjectManager(){
			objectsList = new ArrayList<Objects>();
		}

		public static void storeObjects(Objects x){
			objectsList.add(x);
		}

		public static int read(int indexOfObject){
			return objectsList.get(indexOfObject).getValue();
		}

		public static void write(int indexOfObject, int targetValue){
			objectsList.get(indexOfObject).updateValue(targetValue);
		}

		public void deleteObject(int indexOfObject){
			objectsList.remove(indexOfObject);
		}
	}



}