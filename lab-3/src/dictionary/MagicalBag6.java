/**
 * 
 */
package dictionary;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import dictionary.exceptions.EmptyMagicalBagException;

/**
 * A MagicalBag is a set-like collection (i.e. all elements in the collection must be unique)
 * modelled by a TreeSet.
 * 
 * @author S H S Wong
 * @version 03-10-2020
 * @param <T>
 */
public class MagicalBag6<T> implements MagicalBag<T> {
	/* A random number generator for helping with the picking and removing
	 * 	of random elements from the bag.
	 */ 
	private static Random randomiser = new Random();
	
	private Set<T> contents;
	
	/**
	 * Constructor
	 */
	public MagicalBag6() {
		contents = new TreeSet<T>();
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
		if (contents.isEmpty()) {
			throw new EmptyMagicalBagException();
		}
		
		int index = randomiser.nextInt(size());
		/* The implementation of TreeSet within JCF doesn't have 
		 * 	a method for removing a random item.
		 * 
		 * To implement this operation, we use an enhanced for loop to 
		 * go through the elements one by one. When we have gone through
		 * sufficient number of elements as indicated by 
		 * the generated random number to pick, we note down that
		 * object reference. 
		 * 
		 * (As you can tell, this way of implementing remove is
		 * 	rather clumsy. Hence, for our intended usage of a MagicalBag object,
		 * 	TreeSet is not a suitable collection.
		 */
		int count = 0;
		for (T element : contents) {
			if(count == index) {
				return element;
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
		if (contents.isEmpty()) {
			throw new EmptyMagicalBagException();
		}
		
		T item = null;
		
		/* Picks the item randomly */
		int index = randomiser.nextInt(size());
		/* The implementation of TreeSet within JCF doesn't have 
		 * 	a method for removing a random item.
		 * 
		 * To implement this operation, we use an enhanced for loop to 
		 * go through the elements one by one. When we have gone through
		 * sufficient number of elements as indicated by 
		 * the generated random number to remove, we note down that
		 * object reference and remove it from the collection afterwards. 
		 * 
		 * (As you can tell, this way of implementing remove is
		 * 	rather clumsy. Hence, for our intended usage of a MagicalBag object,
		 * 	TreeSet is not a suitable collection.
		 */
		int count = 0;
		for (T element : contents) {
			if(count == index) {
				item = element;
				break;
			}
			count++;
		}
		
		if (item != null) {
			contents.remove(item);
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
