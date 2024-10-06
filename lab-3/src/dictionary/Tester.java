/**
 * 
 */
package dictionary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * A Tester to invoke all the required simulations.
 * 
 * @author S H S Wong
 * @version 03-10-2020
 *
 */
public class Tester {
	
	final static String DEFAULT_OUTPUT_FILE = "output.txt";
	final static String DICTIONARY_FOLDER = "dictionary_files/";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			 BufferedWriter out = Files.newBufferedWriter(Paths.get(DEFAULT_OUTPUT_FILE), 
					 						StandardCharsets.UTF_8, 
					 						StandardOpenOption.CREATE,
					 						StandardOpenOption.TRUNCATE_EXISTING,
					 						StandardOpenOption.WRITE);
			
			// Creates a File object for accessing the specified file folder.
			File dir = new File(DICTIONARY_FOLDER);
			
			/* For each file within the specified folder,
			 * 	run the simulation in WordPicker using different types of bags.
			 * 
			 * The results of the simulations are written to 
			 * 	the specified text file (see above).  
			 */ 
			for (String dictionaryFile : dir.list()) 
			{
				for (int i = 1; i <= WordPicker.MAX_BAG_TYPES; i++) 
				{
					out.write("\n\nRunning simulation...with MagicalBag"+ i + "\n");

					out.write("=====================================\n");
					// Runs the simulation and output its result to file.
					out.write(WordPicker.simulation(
							DICTIONARY_FOLDER+dictionaryFile, 
							i)
							);
					out.newLine();
					// Flushes the data within the PrintStream.
					out.flush();	
				}
			}
			// Closes the PrintStream
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
