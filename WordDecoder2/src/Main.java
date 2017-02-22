import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Main {
	
	static char[] alphabet = new char[26];
	static int[] numberOfLetters = new int[26];
	static String[] caesarCipher;
	static String[] commonWords = new String[100];
	static char[] tester;
	static String[] bestMatch;

	
	//Fills Arrays with each letter of the alphabet and zeros
	public static void fillArrays(){
		for (char c = 'a'; c <= 'z'; c++) {
		    alphabet[c - 'a'] = c;
		}
		for(int i = 0; i < numberOfLetters.length; i++){
			numberOfLetters[i] = 0;
		}
	}
	
	
	public static void main(String[] args) {
		fillArrays();
		
		System.out.println("Please input a sentence");
		Scanner s = new Scanner(System.in);
		String input = s.nextLine().toLowerCase();
		System.out.println(input);
		char[] charArray = input.toCharArray();
		for(int i = 0; i < charArray.length; i++){
			for(int j = 0; j < alphabet.length; j++){
				if(charArray[i] == alphabet[j])
					numberOfLetters[j]++;
				
			}
		}
		for(int i = 0; i < alphabet.length; i++){
			if(numberOfLetters[i] != 0){
				System.out.println(alphabet[i] + " " + numberOfLetters[i]);
			}
		}
		s.close();
		
		try {
			File file =  new File("commonWords.txt");
			Scanner sc = new Scanner(file);
			int index = 0;
			while(sc.hasNext()){
				commonWords[index] = sc.nextLine();
				index++;
			}
			sc.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("Common Words File Error");
		}
		
		tester = charArray;
		int bestGuess = 0;
		for(int i = 26; i >0; i--){
			int wordMatches = 0;
			for(int j = 0; j < charArray.length; j++){
				if((int)tester[j] == 97)
					tester[j] = 'z';
				else if((int)tester[j] > 97 && (int)tester[j] < 123 )
					tester[j] -= 1;
			}
			String phrase = new String(charArray);
			caesarCipher = phrase.split(" ");
			for(int j = 0; j < caesarCipher.length; j++){
				for(int k = 0; k < commonWords.length; k++){
					if(caesarCipher[j].equals(commonWords[k])){
							wordMatches++;
							break;
					}
				}
			}
			if(wordMatches > bestGuess){
				bestMatch = caesarCipher;
				bestGuess = wordMatches;
			}
			
		}
		if(bestGuess > 0){
			String match = "";
			for(int ind = 0; ind < bestMatch.length; ind++){
				match += bestMatch[ind];
				match += " ";
			}
			System.out.println("The original message was: " + input);
			System.out.println("The message decoded is: " + match);
		}
		else{
			System.out.println("The message could not be determined by the current dictionary.");
		}

	}

}

