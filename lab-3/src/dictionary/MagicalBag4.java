/**
 * 
 */
package dictionary;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import dictionary.exceptions.EmptyMagicalBagException;

/**
 * A MagicalBag is a set-like collection (i.e. all elements in the collection must be unique)
 * modelled by a HashSet.
 * 
 * @author S H S Wong
 * @version 03-10-2020
 * @param <T>
 */
public class MagicalBag4<T> implements MagicalBag<T> {
	/* A random number generator for helping with the picking and removing
	 * 	of random elements from the bag.
	 */ 
	private static Random randomiser = new Random();
	
	private Set<T> contents;
	
	/**
	 * Constructor
	 */
	public MagicalBag4() {
		contents = new HashSet<>(WordPicker.MAX_BAG_CAPACITY);
	}
	
	/*
	 * (non-Javadoc)
	 * @see dictionary.MagicalBag#add(java.lang.Object)
	 */
	@Override
	public void add(T item) {
		contents.add(item);
	}
	
	/*
	 * (non-Javadoc)
	 * @see dictionary.MagicalBag#pick()
	 */
	@Override
	public T pick() {
		/* Checks if the set is empty */
		if(contents.isEmpty()){
			throw new EmptyMagicalBagException();
		}
		int index = randomiser.nextInt(size());
	
		int count = 0;
		for(T element : contents) {
			if(count == index) {
				return element;	// Item found!
			}
			count++;
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see dictionary.MagicalBag#remove()
	 */
	@Override
	public T remove() {
		/* Checks if the set is empty */
		if(contents.isEmpty()) {
			throw new EmptyMagicalBagException();
		}
		T item = null;
		/* Picks the item randomly */
		int index = randomiser.nextInt(size());
	
		int count = 0;
		for(T element : contents) {
			if(count == index) {
				contents.remove(element);
				return element;	// Item found!
			}
			count++;
		}
		return item;
	}
	
	/*
	 * (non-Javadoc)
	 * @see dictionary.MagicalBag#size()
	 */
	@Override
	public int size() {
		return contents.size();
	}
	
	/**
	 * Returns the contents of the bag as a string.
	 * @return
	 */
	@Override
	public String toString() {
		String result = "";
		for(T item : contents) {
			result += item + "  ";
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return contents.iterator();
	}
}
