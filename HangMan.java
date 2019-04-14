package graphicU;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class HangMan {
	private WordList w = new WordList();
	private WordList listOfWords = new WordList();
	
	
public HangMan() throws FileNotFoundException, IllegalArgumentException, IOException {
	WordList dictionary = new WordList().readFromFile("dictionary.txt");
this.listOfWords = dictionary;
}

	public String mysteryWord(String difficulty) throws FileNotFoundException, IllegalArgumentException, IOException {
		int maxLetters = 4;
		int minLetters = 0;
		if (difficulty.equals("Easy")) {maxLetters =4; minLetters = 0;}
		else if (difficulty.equals("Medium")) {maxLetters =8; minLetters = 4;}
		else {maxLetters = 20; minLetters = 8;}
		
ArrayList<Word> possibleWords = new ArrayList<Word>();

int i = 0;
for (Word wor : listOfWords) {if(wor.length() < maxLetters && wor.length()>minLetters) {possibleWords.add(wor);}
		}
		Random rand = new Random();
		int index = rand.nextInt(50);
		return possibleWords.get(index).toString();
		}


	public static void main(String[] args){
		window hangWindow = new window();
		}
	
	public boolean anyMatches(String guess, String word) {
		if (word.contains(guess.toLowerCase()) || word.contains(guess.toUpperCase())){return true; }
		else return false;
	}
	
	public String changeDisplayWord(String displayWord, String guess, String word) {
		int i = 0;
		char[] displayList = displayWord.toCharArray();
		char guessCharLow = guess.toLowerCase().charAt(0);
		char guessCharUpper = guess.toUpperCase().charAt(0);
		while( i<= word.length() &&(word.indexOf(guess.toLowerCase(),i)!=-1)) {
	int index = word.indexOf(guess.toLowerCase(),i); i++; displayList[index] = guessCharLow; }
		
		
		while( i<= word.length() &&(word.indexOf(guess.toUpperCase(),i)!=-1)) {
			int index = word.indexOf(guess.toUpperCase(),i); i++; displayList[index] = guessCharUpper; }
				
				return String.valueOf(displayList);
	}
	
}



