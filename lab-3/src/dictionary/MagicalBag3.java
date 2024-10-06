/**
 * 
 */
package dictionary;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import dictionary.exceptions.EmptyMagicalBagException;

/**
 * A MagicalBag is a set-like collection (i.e. all elements in the collection must be unique) 
 * modelled by a LinkedList.
 * 
 * @author S H S Wong
 * @version 03-10-2020
 * @param <T>
 */
public class MagicalBag3<T> implements MagicalBag<T> {
	/* A random number generator for helping with the picking and removing
	 * 	of random elements from the bag.
	 */ 
	private static Random randomiser = new Random();
	
	private List<T> contents;
	
	/**
	 * Constructor
	 */
	public MagicalBag3() {
		contents = new LinkedList<>();
	}
	
	/*
	 * (non-Javadoc)
	 * @see dictionary.MagicalBag#add(java.lang.Object)
	 */
	@Override
	public void add(T item) {
		if (!contents.contains(item)) {
			contents.add(item);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see dictionary.MagicalBag#pick()
	 */
	@Override
	public T pick() {
		if (contents.isEmpty()) {
			throw new EmptyMagicalBagException();
		}
		int index = randomiser.nextInt(size());
		return contents.get(index);
	}
	
	/*
	 * (non-Javadoc)
	 * @see dictionary.MagicalBag#remove()
	 */
	@Override
	public T remove() {
		if (contents.isEmpty()) {
			throw new EmptyMagicalBagException();
		}
		else {
			int index = randomiser.nextInt(size());
			T result = contents.remove(index);
			return result;
		}
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
		for (T item : contents) {
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
