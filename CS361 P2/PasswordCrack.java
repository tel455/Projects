import java.io.*;
import java.util.*;



public class PasswordCrack{
	public static String[] charTable = new String[95];

	public static void main(String [] args) throws IOException{
		//create char table
		int counter = 0;
		for (int i = 32; i <= 126; i++) {
			charTable[counter] = Character.toString((char)i);
			counter++;
		}


		List<String> dictionary = new ArrayList<String>();
		List<List<String>> passwordList = new ArrayList<List<String>>();

		try{
			String dictionaryFileName = args[0];
			String passwordListFileName = args[1];

			File dictionaryFile = new File(dictionaryFileName);
			File passwordListFile = new File(passwordListFileName);
			Scanner dictionaryScanner = new Scanner(dictionaryFile);
			Scanner passwordListScanner = new Scanner(passwordListFile);

			//reading from wordlists and store all words in the dictionary
			while(dictionaryScanner.hasNext()){
				String nextLine = dictionaryScanner.nextLine();
				dictionary.add(nextLine);
			}


			//reading from password list and store everything into passwordList
			while(passwordListScanner.hasNext()){
				String nextLine = passwordListScanner.nextLine();

				Scanner innerInfo = new Scanner(nextLine);
				innerInfo.useDelimiter(":");
				List<String> information = new ArrayList<String>();
				while(innerInfo.hasNext()){
					information.add(innerInfo.next());
				}
				passwordList.add(information);
			}
			
			crack(dictionary, passwordList);

		}catch(IOException e){
			System.out.println("Check your file name again!");
		}
	}

	public static void crack(List<String> dictionary, List<List<String>> passwordList){
		//iterate through each user
		for (int userIterator = 0; userIterator < passwordList.size(); userIterator++) {
			List<String> information = passwordList.get(userIterator);
			String passwordWithSalt = information.get(1);
			String salt = passwordWithSalt.substring(0, 2);
			String encryptedPassword = passwordWithSalt.substring(2);
			String userName = information.get(0);

			String[] fullName = information.get(4).split("\\s+");
			String firstName = fullName[0];
			String lastName = fullName[1];
			
			dictionary.add(0, firstName);	//adding first name to the dictionary without modifying the file
			dictionary.add(1, lastName);	//adding last name to the dictionary without modifying the file
			dictionary.add(2, userName);	//adding user name to the dictionary without modifying the file
			boolean finished = false;
			for (int wordIterator = 0; wordIterator < dictionary.size(); wordIterator++) {
				String word = dictionary.get(wordIterator);
				
				//simple cases without mangle
				if (jcrypt.crypt(salt, word).equals(passwordWithSalt)) {
					System.out.println("The password for user " + information.get(4) + " is " + word);
					finished = true;
					break;		//to prevent the case where appending or prepending chars makes a new word that is same as the existing one in the dictionary, e.g. "amazing" and "a" + mazing
				}


				/*--------------------------1 mangled case--------------------------------------*/
				//delete the first char
				String deletedFirstChar = word.substring(1);
				if (jcrypt.crypt(salt, deletedFirstChar).equals(passwordWithSalt)) {
					System.out.println("The password for user " + information.get(4) + " is " + deletedFirstChar);
					finished = true;
					break;
				}

				//delete the last char
				String deletedLastChar = word.substring(0, (word.length()) - 1);
				if (jcrypt.crypt(salt, deletedLastChar).equals(passwordWithSalt)) {
					System.out.println("The password for user " + information.get(4) + " is " + deletedLastChar);
					finished = true;
					break;
				}

				//reverse
				StringBuilder sb = new StringBuilder(word);
				sb.reverse();
				String reversed = sb.toString();
				if (jcrypt.crypt(salt, reversed).equals(passwordWithSalt)) {
					System.out.println("The password for user " + information.get(4) + " is " + reversed);
					finished = true;
					break;
				}

				//duplicate
				String duplicated = word + word;
				if (jcrypt.crypt(salt, duplicated).equals(passwordWithSalt)) {
					System.out.println("The password for user " + information.get(4) + " is " + duplicated);
					finished = true;
					break;
				}

				//reflect
				String reflected1 = word + reversed;	//original + reversed
				String reflected2 = reversed + word;	//reversed + original
				if (jcrypt.crypt(salt, reflected1).equals(passwordWithSalt)) {
					System.out.println("The password for user " + information.get(4) + " is " + reflected1);
					finished = true;
					break;
				}
				if (jcrypt.crypt(salt, reflected2).equals(passwordWithSalt)) {
					System.out.println("The password for user " + information.get(4) + " is " + reflected2);
					finished = true;
					break;
				}

				//uppercase
				String uppercased = word.toUpperCase();
				if (jcrypt.crypt(salt, uppercased).equals(passwordWithSalt)) {
					System.out.println("The password for user " + information.get(4) + " is " + uppercased);
					finished = true;
					break;
				}

				//lowercase
				String lowercased = word.toLowerCase();
				if (jcrypt.crypt(salt, lowercased).equals(passwordWithSalt)) {
					System.out.println("The password for user " + information.get(4) + " is " + lowercased);
					finished = true;
					break;
				}

				//capitalize
				String capitalized = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
				if (jcrypt.crypt(salt, capitalized).equals(passwordWithSalt)) {
					System.out.println("The password for user " + information.get(4) + " is " + capitalized);
					finished = true;
					break;
				}

				//ncapitalize
				String ncapitalized = word.substring(0, 1).toLowerCase() + word.substring(1).toUpperCase();
				if (jcrypt.crypt(salt, ncapitalized).equals(passwordWithSalt)) {
					System.out.println("The password for user " + information.get(4) + " is " + ncapitalized);
					finished = true;
					break;
				}

				//toggle
				String toggled1 = toggle(word, 0);	//case 1
				String toggled2 = toggle(word, 1);	//case 2
				if (jcrypt.crypt(salt, toggled1).equals(passwordWithSalt)) {
					System.out.println("The password for user " + information.get(4) + " is " + toggled1);
					finished = true;
					break;
				}
				if (jcrypt.crypt(salt, toggled2).equals(passwordWithSalt)) {
					System.out.println("The password for user " + information.get(4) + " is " + toggled2);
					finished = true;
					break;
				}

			}


			//prepend and append
			if (!finished) {
				for (int wordIterator = 0; wordIterator < dictionary.size(); wordIterator++) {
					String word = dictionary.get(wordIterator);
					for (int preAppIterator = 0; preAppIterator < 95; preAppIterator++) {
						String prependedWord = charTable[preAppIterator] + word;
						String appendedWord = word + charTable[preAppIterator];
						if (jcrypt.crypt(salt, prependedWord).equals(passwordWithSalt)) {	//check prepend
							System.out.println("The password for user " + information.get(4) + " is " + prependedWord);
							finished = true;
							break;
						}
						if (jcrypt.crypt(salt, appendedWord).equals(passwordWithSalt)) {	//check append
							System.out.println("The password for user " + information.get(4) + " is " + appendedWord);
							finished = true;
							break;
						}
					}
					if (finished) {
						break;
					}
				}
			}

			/*--------------------------2 or more mangled case--------------------------------------*/
			if (!finished) {
				for (int wordIterator = 0; wordIterator < dictionary.size(); wordIterator++) {
					String word = dictionary.get(wordIterator);
					
					//capitalize and then reverse
					String capitalized = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase(); 
					StringBuilder sb = new StringBuilder(capitalized);
					sb.reverse();
					String reversed = sb.toString();
					if (jcrypt.crypt(salt, reversed).equals(passwordWithSalt)) {
						System.out.println("The password for user " + information.get(4) + " is " + reversed);
						finished = true;
						break;
					}

					//capitalize the first char, delete last char then reverse
					String deletedLastChar = capitalized.substring(0, (capitalized.length()) - 1);
					sb = new StringBuilder(deletedLastChar);
					sb.reverse();
					reversed = sb.toString();
					if (jcrypt.crypt(salt, reversed).equals(passwordWithSalt)) {
						System.out.println("The password for user " + information.get(4) + " is " + reversed);
						finished = true;
						break;
					}


					//ncapitalize and then append
					String ncapitalized = word.substring(0, 1).toLowerCase() + word.substring(1).toUpperCase();
					deletedLastChar = word.substring(0, (word.length()) - 1);
					for (int preAppIterator = 0; preAppIterator < 95; preAppIterator++) {
						String ncapitalize_appendedWord = ncapitalized + charTable[preAppIterator];
						if (jcrypt.crypt(salt, ncapitalize_appendedWord).equals(passwordWithSalt)) {
							System.out.println("The password for user " + information.get(4) + " is " + ncapitalize_appendedWord);
							finished = true;
							break;
						}
						String deleteLastChar_appendedWord = deletedLastChar + charTable[preAppIterator];
						if (jcrypt.crypt(salt, deleteLastChar_appendedWord).equals(passwordWithSalt)) {
							System.out.println("The password for user " + information.get(4) + " is " + deleteLastChar_appendedWord);
							finished = true;
							break;
						}
					}
					if (finished) {
						break;
					}
				}
				
			}
			
			dictionary.remove(2);	//remove user name for optimization
			dictionary.remove(1);	//remove last name for optimization
			dictionary.remove(0);	//remove first name for optimization
		}
		
	}

	public static String toggle(String original, int mode){
		char[] tmp =  original.toCharArray();
		if (mode == 0) {		//even index is upper case
			for (int i = 0; i < tmp.length; i++) {
				if ((i % 2) == 0) {		//even
					tmp[i] = Character.toUpperCase(tmp[i]);
				}else{					//odd
					tmp[i] = Character.toLowerCase(tmp[i]);
				}
			}
		}else{
			for (int i = 0; i < tmp.length; i++) {
				if ((i % 2) == 0) {		//even
					tmp[i] = Character.toLowerCase(tmp[i]);
				}else{					//odd
					tmp[i] = Character.toUpperCase(tmp[i]);
				}
			}
		}
		return new String(tmp);
	}

	//after optimization, not really used
	public static String getUserName(String line){
		String result = "";
		for (int i = 0; i < line.length(); i++) {
			char tmp = line.charAt(i);
			if (tmp == ':') {
				result = line.substring(0, i);
				break;	
			}
		}
		return result;
	}
}