/**
 * 
 */
package seminars;

import java.util.Set;
import java.util.HashSet;

/**
 * Class Speakers contains a range of class methods (i.e. methods defined as 'static') 
 * which model the quirks of a range of speakers:
 * - Some speakers like to 'shout' (modelled by capitalising all letters in their speech).
 * - Some speakers like to 'shout' out long words only (i.e. words with >6 letters).
 * - Some speakers like to emphasise the words that have occurred more than once in their speech using an exclamation mark.
 * Given a message to be uttered by a speaker, this class contains
 * a range of methods to modified the output speech with the required quirk.
 * 
 * @author Andrew Kay and Sylvia Wong
 * @version 29-09-2020
 */
public class Speakers {
	
	private static final String[] messages = 
		{
				"Hello", 
				"Hello friends",
				"Hello friends Hello",
				"I like playing badminton",
				"I played badminton on Saturdays",
				"I often participate in badminton tournaments"
		};
	
	/**
	 * To 'shout' out each word in the speech 
	 * (modelled by capitalising all letters in their speech).
	 * 
	 * @param message
	 * @return	the speech with the required quirk
	 */
    public static String capitaliseAll(String message) {
        String[] words = message.split(" ");
        String result = "";
        
        for (String word : words) {
            char firstLetter = word.charAt(0);
            result += Character.toUpperCase(firstLetter);
            result += word.substring(1);
            result += " ";
        }
        
        return result;
    }
    
    /**
     * To 'shout' out long words only (i.e. words with >6 letters).
     * 
     * @param message
     * @return	the speech with the required quirk
     */
    public static String shoutLongWords(String message) {
        String[] words = message.split(" ");
        String result = "";
        
        for (String word : words) {
            if (word.length() > 6) {
                word = word.toUpperCase();
            }
            result += word;
            result += " ";
        }
        
        return result;
    }
    
    /**
     * To emphasise the words that have occurred more than once 
     * in their speech using an exclamation mark.
     * 
     * To check whether a given word has already appeared in the 
     * given message earlier, check through the words in the message 
     * that has been processed so far.
     * 
     * @param message
     * @return	the speech with the required quirk
     */
    public static String exclamationMarkOnRepeat(String message) {
        String[] words = message.split(" ");
        String result = "";
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            result += word;
            for (int j = 0; j < i; j++) {
                if (word.equals(words[j])) {
                    result += "!";
                    break;
                }
            }
            result += " ";
        }
        
        return result;
    }
    
    /**
     * To emphasise the words that have occurred more than once 
     * in their speech using an exclamation mark.
     * 
     * Every time when a word in the given message is processed, 
     * add it to a set. This set is then used to check whether 
     * a given word has already appeared in the given message 
     * earlier.
     * 
     * @param message
     * @return	the speech with the required quirk
     */
    public static String exclamationMarkOnRepeatUsingSet(String message) {
        String[] words = message.split(" ");
        Set<String> wordsUsed = new HashSet<>();
        String result = "";
        
        for (String word : words) {
            result += word;
            if (wordsUsed.contains(word)) {
                result += "!";
            } else {
                wordsUsed.add(word);
            }
            result += " ";
        }
        
        return result;
    }
    
    /**
     * A main method which enables this class to be run.
     * 
     * This method contains a single loop which cycle through the given 6 messages 
     * and processed them with the required quirk in turn. 
     * 
     * Simply uncomment the required "quirk" to analyse the number of steps required
     * to process the data in each case.
     * 
     * @param args
     */
    public static void main(String[] args) {
    	for (int i = 0; i < messages.length; i++) {
    		Speakers.capitaliseAll(Speakers.messages[i]);
//    		Speakers.shoutLongWords(Speakers.messages[i]);
//    		Speakers.exclamationMarkOnRepeat(Speakers.messages[i]);
//    		Speakers.exclamationMarkOnRepeatUsingSet(Speakers.messages[i]);
    	}
    }
}
