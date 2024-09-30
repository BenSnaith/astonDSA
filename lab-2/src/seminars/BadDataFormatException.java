/**
 * 
 */
package seminars;

/**
 * An exception object that is to be thrown when the data format
 * does not meet the requirement.  
 * 
 * @author Sylvia Wong
 * @version 29-09-2020
 */
public class BadDataFormatException extends Exception {


	/**
	 * As class Exception implements interface Serializable,
	 * specifying a serialVersionUID explicitly helps to avoid 
	 * potential serialization issues.  
	 */	
	private static final long serialVersionUID = 7657533591005463881L;

	/**
	 * Constructor
	 */
	public BadDataFormatException() {
		super("Incompatible data format.");
	}

	/**
	 * Constructor
	 * @param arg0	message carried by this exception 
	 */
	public BadDataFormatException(String arg0) {
		super(arg0);
	}


}
