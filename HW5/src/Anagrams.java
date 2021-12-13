
/**
 * 
 * @author Eleni Rotsides
 * @section A
 * @pledge I pledge my honor that I have abided by the Stevens honor system.
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Homework 5: Anagrams
 */
public class Anagrams {

	// data fields
	private final Integer[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
			79, 83, 89, 97, 101 };
	private Map<Character, Integer> letterTable;
	private Map<Long, ArrayList<String>> anagramTable;

	// constructor

	/**
	 * Initializes letterTable and anagramTable Builds the letter table using
	 * buildLetterTable()
	 */
	public Anagrams() {
		letterTable = new HashMap<Character, Integer>();
		anagramTable = new HashMap<Long, ArrayList<String>>();
		buildLetterTable();
	}

	// methods

	/**
	 * Builds the hash table letterTable; this function is called in the constructor
	 */
	public void buildLetterTable() {
		Character[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		// iterates 26 times to create the letterTable
		for (int i = 0; i < alphabet.length; i++) {
			letterTable.put(alphabet[i], primes[i]);
		}
	}

	/**
	 * Computes the hash code and adds the word to the hash table named anagramTable
	 * 
	 * @param s
	 * @throws IllegalArgumentException
	 */
	public void addWord(String s) {
		if (s == null || s == "") {
			throw new IllegalArgumentException("s cannot be empty");
		}

		// myHashCode will throw an error if an invalid string is provided
		long hashCode = myHashCode(s);

		if (anagramTable.containsKey(hashCode)) {
			ArrayList<String> anagrams = anagramTable.get(hashCode);
			
			// first check if the word already exists; throw an error if it does
			if(anagrams.contains(s)) {
				throw new IllegalArgumentException("Cannot add duplicate word");
			}

			// if the word doesn't already exist, then proceed with adding it
			anagrams.add(s);
			anagramTable.replace(hashCode, anagrams);
		} else {
			ArrayList<String> str = new ArrayList<String>();

			str.add(s);
			anagramTable.put(hashCode, str);
		}
	}

	/**
	 * Computes the hash code; all the anagrams of a word should receive the same
	 * hash code. Uses the Fundamental Theorem of Arithmetic for the formula.
	 * 
	 * @param s
	 * @throws IllegalArgumentException
	 * @return
	 */
	public long myHashCode(String s) {
		if (s == null || s == "") {
			throw new IllegalArgumentException("s cannot be empty");
		}

		long hashCode = 1;

		for (int i = 0; i < s.length(); i++) {
			if (!letterTable.containsKey(s.charAt(i))) {
				throw new IllegalArgumentException(
						"There is an illegal character in the string provided; only letters of the alphabet are allowed");
			}
			hashCode *= letterTable.get(s.charAt(i));
		}

		return hashCode;
	}

	/**
	 * Receives the name of a text file containing words, one per line, and builds
	 * the hash table called anagramTable
	 * 
	 * @param s
	 * @throws IOException
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;

		while ((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}

	/**
	 * Returns the entries in the anagramTable that have the largest number of
	 * anagrams (Returns a list of them since there may be more than one list of
	 * anagrams with a maximal size)
	 * 
	 * @return
	 */
	public ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {

		ArrayList<Map.Entry<Long, ArrayList<String>>> listOfMaxEntries = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
		int numOfEntries = 0;

		for (Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) {
			ArrayList<String> anagrams = entry.getValue();

			if (anagrams.size() > numOfEntries) {
				numOfEntries = anagrams.size();
				listOfMaxEntries.clear();
				listOfMaxEntries.add(entry);
			} else if(anagrams.size() == numOfEntries) {
				listOfMaxEntries.add(entry);
			}
		}

		return listOfMaxEntries;
	}

	/**
	 * Main method with some code that will let me check if everything is working as
	 * it should. Here is the expected output of your solution (the elapsed time may
	 * vary): Time: 0.261896991 List of max anagrams: [236204078=[alerts, alters,
	 * artels, estral, laster, lastre, rastle, ratels ...
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		
		final long startTime = System.nanoTime();

		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime / 1000000000);

		System.out.println("Time: " + seconds);
		System.out.println("List of max anagrams: " + maxEntries);
	}
}
