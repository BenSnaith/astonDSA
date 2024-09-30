/**
 * 
 */
package raffle;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.Random;

/**
 * This is a generic class which works like a collection. 
 * 
 * A box can keep a collection of items. 
 * The items within the box are not ordered in any logic order.
 * Each box has an unlimited capacity.
 * 
 * @author S H S Wong
 * @version 27-09-2020
 */

public class Box<T> implements Iterable<T> {
	
	private ArrayList<T> contents; // Models the contents of the box
	
	/**
	 * Constructor
	 */
	public Box() {
		contents = new ArrayList<>();
	}
	
	// Private BoxIterator class so we can use the enhanced for loop.
	class BoxIterator implements Iterator<T> {
		int curr = 0;
		
		@Override
		public boolean hasNext() {
			if(curr < Box.this.contents.size()) {
				return true;
			}
			else {
				return false;
			}
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new IndexOutOfBoundsException();
			}
			return Box.this.contents.get(curr++);
		}
		
	}
	
	/**
	 * Draws an item from the box. 
	 * When the item is drawn, it is physically removed from the box. Its
	 * 	object reference is returned as a result.
	 * 
	 * If the box is empty, this method will simply return null.
	 * 
	 * @return
	 */
	public T draw() {
		
		T drawnItem = null;
		
		if (!contents.isEmpty()) {
			/* Use a random number generator to determine which item is to
			 * be drawn from the box.
			 */ 
			Random randomizer = new Random();
			int which = randomizer.nextInt(contents.size());
			drawnItem = contents.remove(which);
		}
		
		return drawnItem;
	}
	
	/**
	 * Puts an item into the box.
	 * @param item
	 */
	public void put(T item) {
		contents.add(item);
	}

	/**
	 * Returns true if the specified item is currently in the box.
	 * Returns false otherwise.
	 * @param item
	 * @return
	 */
	public boolean find(T item) {
		return contents.contains(item);
	}

	
	@Override
	public Iterator<T> iterator() {
		return new BoxIterator();
	}
	
	
	/**
	 * Returns true if the box is empty.
	 * Returns false otherwise. 
	 * @return
	 */
	public boolean isEmpty() {
		return (contents.isEmpty());
	}
}
