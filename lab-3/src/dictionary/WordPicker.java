/**
 * 
 */
package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

import dictionary.exceptions.EmptyMagicalBagException;
import dictionary.exceptions.FullMagicalBagException;
import dictionary.exceptions.UnsupportedMagicalBagTypeException;

/**
 * A WordPicker keeps a dictionary of words. 
 * It returns/removes a randomly selected word from its dictionary.
 * 
 * This class contains a static method called simulation. This method
 * 	simulates the process of:
 *  - creating a WordPicker object with a specified dictionary file 
 *  	(i.e. a text file in UTF8 encoding), 
 * 	- picking N words from its dictionary, and
 *  - removing N words.
 * The time taken to execute each operation is tracked during the simulation. 
 * 
 * @author S H S Wong
 * @version 03-10-2020
 */
public class WordPicker {

	// Number of words picked/removed from each bag in the simulation, i.e. N.
	final static int PICKS = 1000;
	
	// Maximum number of types of bags recognised by this WordPicker.
	final static int MAX_BAG_TYPES = 6;
	
	// Maximum bag capacity
	final static int MAX_BAG_CAPACITY = 76000;
	
	// The dictionary of words kept in a bag.
	private MagicalBag<String> words;
	
	/**
	 * Constructor
	 * @throws FileNotFoundException 
	 */
	public WordPicker(String dictionaryFile, int bagType) 
		throws FileNotFoundException 
	{	
		/* Create the type of bag based on the specified bag type. */
		switch (bagType) {
		case 1 : words = new MagicalBag1<>();
				 break;
		case 2 : words = new MagicalBag2<>();
		 		 break;
		case 3 : words = new MagicalBag3<>();
		 		 break;
		case 4 : words = new MagicalBag4<>();
		 		 break;		 		 
		case 5 : words = new MagicalBag5<>();
		 		 break;		 		 
		case 6 : words = new MagicalBag6<>();
		 		 break;		 		 
		default: throw new UnsupportedMagicalBagTypeException();
		}
		
		File wordFile = new File(dictionaryFile);

		// A Scanner object for handling file (in UTF8 encoding) input.
		Scanner scan = new Scanner(wordFile, "utf8");

		/*
		 * Read the dictionary file line by line. 
		 */
		while (scan.hasNextLine()) {
			/* Read a line from the dictionary file
			 * 	1 line = 1 word
			 * and add it to the bag.
			 */ 
			words.add(scan.nextLine().trim());
		}
		scan.close();
	}
	
	/**
	 * Picks a word from the bag of words randomly 
	 * 	and returns it.
	 * @return
	 */
	public String pick() {
		return words.pick();
	}
	
	/**
	 * Removes a word from the bag of words randomly
	 * 	and returns it.
	 * @return
	 */
	public String remove() {
		return words.remove();
	}
	
	/**
	 * Returns the size of the vocabulary that is kept in the bag.
	 * @return
	 */
	public int vocabularySize() {
		return words.size();
	}

	/**
	 * The Simulation includes:
	 * 
	 * 	Creates a WordPicker object which uses a specified type of MagicalBag
	 * 	for keeping the dictionary. 
	 * 
	 * In order to keep track the execution time of each key operation, 
	 * 	this method uses the getTime() method in a Date object to get 
	 *  the current moment represented in milliseconds (ms) 
	 * 	since January 1, 1970, 00:00:00 GMT.
	 * 
	 * @param dictionaryFile	the filename of the dictionary file
	 * @param bagType	the type of bag for storing the dictionary of words
	 * @return	the results of the simulation as a string
	 */
	public static String simulation(String dictionaryFile, int bagType) 
	{
		String timeFormat = "%dms";
		String processingTimeMsg = "\n\nTime taken to %s the dictionary: " + timeFormat + "\n";
		String results = "";
		long timeTaken4Construction;
		long timeTaken4WordPicking;
		long timeTaken4WordRemoval;
		
		try {
			// Start time - Gets the current moment...
			long starts = (new Date()).getTime();
			WordPicker wp = new WordPicker(dictionaryFile, bagType);
			// End time - Gets the current moment...
			long ends = (new Date()).getTime();
			
			timeTaken4Construction = (ends - starts);
					
			// Outputs the time taken to create a WordPicker.
			results += String.format(processingTimeMsg, "build", timeTaken4Construction);
			// Outputs the size of the vocabulary
			results +=  String.format("\nAdded %d words from %s.\n\n", 
									wp.vocabularySize(), 
									dictionaryFile);

			// Picks some words and display them.
			results += String.format("Picking %s words: ", PICKS);
			// Start time - Gets the current moment...
			starts = (new Date()).getTime();
			// Word picking...
			for(int i=0; i < PICKS; i++) {
				results += String.format(wp.pick() + '\t');	
			}
			// End time - Gets the current moment...
			ends = (new Date()).getTime();
			
			timeTaken4WordPicking = (ends - starts);
			
			// Display the time taken to do the word picking.
			results += String.format(processingTimeMsg, "pick " + PICKS + " words from", timeTaken4WordPicking);

			// Picks some words and display them.
			results += String.format("Removing %s words: ", PICKS);
			// Gets the current moment...
			starts = (new Date()).getTime();
			// Removing words...
			for (int i=0; i < PICKS; i++) {
				results += String.format(wp.remove() + '\t');	
			}
			ends = (new Date()).getTime();

			timeTaken4WordRemoval = (ends - starts);
			
			// Outputs the time taken to do the word picking.
			results += String.format(processingTimeMsg, "remove " + PICKS + " words from", timeTaken4WordRemoval);
			
			// Outputs the time taken for all three tasks: constructing the dictionary, picking words and removing words.
			 results += String.format("\n\n%s with Bag %s -- Time taken for dictionary construction, word picking and word removal: " 
					 + timeFormat + ", " + timeFormat + ", " + timeFormat, 
					 dictionaryFile, bagType, timeTaken4Construction, timeTaken4WordPicking, timeTaken4WordRemoval);
		} 
		catch (FileNotFoundException ioe) 
		{
			results += String.format("Error: File %s not found!\n", 
									dictionaryFile);
		}
		catch (UnsupportedMagicalBagTypeException ubte)
		{
			results += String.format("Error: %s\n", ubte.getMessage());
		}
		catch (EmptyMagicalBagException ebe) {
			results += String.format("Error: %s\n", ebe.getMessage());
		}
		catch (FullMagicalBagException fbe) {
			results += String.format("Error: %s\n", fbe.getMessage());
		}
		
		return results;
	}
	
	/**
	 * Main method - 
	 * 	run the simulation with a specified dictionary file and bag type.
	 * 
	 * This application expects at least two input arguments: 
	 * 1. the filename of the dictionary file
	 * 2. the type of bag for storing the dictionary of words
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/* This application expects at least two input arguments
		 * 	i.e. the filename of the dictionary file.
		 */
		if(args.length < 2) {
			System.out.println(
				"Can't run the application. Please specify the name of the dictionary file and the bag type (i.e. 1 - 6 only).");
		}
		else {
			try {
				int bagType = Integer.parseInt(args[1]);
				// run the simulation
				System.out.println(WordPicker.simulation(args[0], bagType));
			}
			catch (NumberFormatException nfe) {
				System.out.printf("Error: Non-numeric bag type: %s!\n", args[1]);
			}
		}
	}

}
