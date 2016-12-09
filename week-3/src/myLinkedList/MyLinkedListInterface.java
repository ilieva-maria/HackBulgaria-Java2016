package myLinkedList;

import java.util.Iterator;


/**
 * Linked List Interface
 * 
 * @author Maria
 *
 * @param <T> should implement the Comparable interface
 */

public interface MyLinkedListInterface<T extends Comparable<T>> {
	
	Iterator<T> iterator();
	
	/**
	 * Adds an element to the beginning of the list. Takes O(1).
	 * 
	 * @param newElement
	 */
	
	public void addFirst(T newElement);
	
	/**
	 * Adds the provided element to the end of the list. Takes O(n).
	 * 
	 * @param newElement
	 */

	public void addLast(T newElement);
	
	
	/**
	 * Adds the provided element at the specified index. Takes O(n).
	 *
	 * @param newElement
	 * @param index
	 */

	public void add(T newElement, int index);
	
	/**
	 * Returns first element of the list. Takes O(1).
	 * 
	 * @return
	 */

	public T getFirst();
	
	/**
	 * Returns the last element of the list. Takes O(n).
	 * @return
	 */

	public T getLast();
	
	/**
	 * Returns the element at the specified index. Takes O(n).
	 * @param index
	 * @return
	 */

	public T get(int index);
	
	/**
	 * Returns size of the list. Takes O(1).
	 * @return
	 */

	public int getSize();
	
	/**
	 * Removes the element at the specified index. Takes O(n).
	 * @param index
	 */

	public void remove(int index);
	
	/**
	 * Removes the first element in the list. Takes O(1).
	 */
	
	public void removeFirst();
	
	/**
	 * Removes last element of the list. Takes O(n).
	 */
	
	public void removeLast();
	
	/**
	 * Appends another list. Takes O(n).
	 * @param list
	 */

	public void addList(MyLinkedListInterface<T> list);
	
	/**
	 * Returns true if there are no elements in the list. False otherwise.
	 * @return
	 */
	
	public boolean isEmpty();
	
	
}
