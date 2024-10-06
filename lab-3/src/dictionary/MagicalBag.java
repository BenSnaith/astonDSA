/**
 * 
 */
package dictionary;

/**
 * An interface for specifying the behaviour of a MagicalBag.
 * 
 * A MagicalBag object models a collection for keeping a generic type of objects.
 * Each item kept in a MagicalBag must be unique.
 * 
 * @author S H S Wong
 * @version 03-10-2020
 * @param <T>
 */
/* !!!!
 * 1. A MagicalBag object is expected to be used in the context of an enhanced for loop.
 * 2. A MagicalBag is expected to keep a generic type of objects.
 */
public interface MagicalBag<T> extends Iterable<T> {

	/** Adds an item to the bag.
	 * 
	 * @param item - an item to be added to the bag
	 */
	void add(T item);
	
	/** Picks a random item from the bag and returns it. 
	 *  This operation does not alter the contents of the bag.
	 * @return a random item from the bag
	 */
	T pick();
	
	/** Removes a random item from the bag
	 * 		and returns it.
	 * @return a random item from the bag
	 */
	T remove();
	
	/** Returns the number of items that are kept in the bag.
	 * 
	 * @return the number of items kept in the bag
	 */
	int size();
	
}
