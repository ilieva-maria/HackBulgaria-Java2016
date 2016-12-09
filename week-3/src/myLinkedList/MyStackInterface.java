package myLinkedList;

public interface MyStackInterface<T extends Comparable<T>> {

	/**
	 * Stack Interface
	 * 
	 * @param <T>
	 *            should implement the Comparable Interface.
	 */
	/**
	 * Adds the element on top of the Stack.
	 */
	public void push(T element);

	/**
	 * Returns the element on top of the Stack and removes it.
	 */
	public T pop();

	/**
	 * Returns the element on top of the Stack without removing it.
	 * 
	 */
	public T peek();

	/**
	 * Returns the number of elements in the Stack.
	 */
	public int getSize();

}
