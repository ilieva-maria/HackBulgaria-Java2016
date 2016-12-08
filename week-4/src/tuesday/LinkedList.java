package tuesday;

import java.util.EmptyStackException;
import java.util.Iterator;

import myLinkedList.MyLinkedListInterface;
import myLinkedList.MyQueueInterface;
import myLinkedList.MyStackInterface;

public class LinkedList<T extends Comparable<T>>
		implements MyLinkedListInterface<T>, MyQueueInterface<T>, MyStackInterface<T>, Iterable<T> {

	@Override
	public void addFirst(T newElement) {
		Node newNode = new Node(newElement, head);
		head = newNode;
		if (size == 0) {
			tail = head;
		}
		size++;
	}

	@Override
	public void addLast(T newElement) {
		if (size == 0) {
			addFirst(newElement);
		} else {
			tail.nextNode = new Node(newElement);
			tail = tail.nextNode;
			size++;
		}
	}

	private void validateIndex(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("The provided index is out of range: " + index);
		}
	}

	@Override
	public void add(T newElement, int index) {
		validateIndex(index);
		if (index == 0) {
			addFirst(newElement);
		} else if (index == size - 1) {
			addLast(newElement);
		} else {
			Node previousNode = getNode(index - 1);
			Node newNode = new Node(newElement, previousNode.nextNode);
			previousNode.nextNode = newNode;
			size++;
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public T getFirst() {
		if (isEmpty()) {
			return null;
		}
		return head.element;
	}

	@Override
	public T getLast() {
		if (isEmpty()) {
			return null;
		}
		return tail.element;
	}

	Node getNode(int index) {
		validateIndex(index);
		Node node = head;
		if (index == size - 1) {
			node = tail;
		} else {
			for (int i = 0; i < index; i++) {
				node = node.nextNode;
			}
		}
		return node;
	}

	@Override
	public T get(int index) {
		validateIndex(index);
		return getNode(index).element;
	}
	
	/**
	 * Returns size of the list.
	 * Takes O(n)
	 */
	@Override
	public int getSize() {
		int size = 0;
		Iterator<T> iter = iterator();
		while(iter.hasNext()){
			iter.next();
			size++;
		}
		return size;
	}

	@Override
	public void remove(int index) {
		validateIndex(index);

		if (index == 0) {
			removeFirst();
		} else if (index == size - 1) {
			removeLast();
		} else {
			Node previousNode = getNode(index - 1);
			previousNode.nextNode = previousNode.nextNode.nextNode;
			size--;
		}

	}

	@Override
	public void removeFirst() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else if (size == 1) {
			head = null;
			tail = null;
		} else {
			head = head.nextNode;
		}
		size--;

	}

	@Override
	public void removeLast() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else if (size == 1) {
			removeFirst();
		} else {
			tail = getNode(size - 2);
			tail.nextNode = null;
			size--;
		}

	}

	@Override
	public void addList(MyLinkedListInterface<T> list) {
		if (list == null) {
			throw new IllegalArgumentException("The provided list cannot be null.");
		}

		Iterator<T> iter = list.iterator();
		while (iter.hasNext()) {
			T element = iter.next();
			addLast(element);
		}
	}

	@Override
	public void enqueue(T element) {
		addLast(element);
	}

	@Override
	public T dequeue() {
		return removeHead();
	}

	@Override
	public T peek() {
		return getFirst();
	}

	@Override
	public void push(T element) {
		addFirst(element);
	}

	@Override
	public T pop() {
		return removeHead();
	}

	private T removeHead() {
		T firstElement = null;
		if (getSize() != 0) {
			firstElement = getFirst();
			remove(0);
		}
		return firstElement;
	}

	/**
	 * Finds the k-th to last element in the linked list. Takes O(n).
	 * 
	 * @param k
	 * @return
	 */

	public T findKthToLast(int k) {
		validateIndex(k);
		Node currentPointer = head;
		Node currentPointerPlusK = head;
		for (int i = 0; i < k; i++) {
			currentPointerPlusK = currentPointerPlusK.nextNode;
		}
		while (currentPointerPlusK.nextNode != null) {
			currentPointer = currentPointer.nextNode;
			currentPointerPlusK = currentPointerPlusK.nextNode;
		}
		return currentPointer.element;

	}

	/**
	 * Deletes a node in the middle of a singly linked list, given only access
	 * to that node.
	 * 
	 * @param currentNode
	 */

	public void deleteNode(Node currentNode) {
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		if (currentNode.nextNode != null) {
			currentNode.element = currentNode.nextNode.element;
			currentNode.nextNode = currentNode.nextNode.nextNode;
		} else {
			throw new IndexOutOfBoundsException("Use removeLast.");
		}
		size--;
	}

	/**
	 * Partition a linked list around a value x, such that all nodes less than x
	 * come before all nodes greater than or equal to x.
	 * 
	 * @param pivot
	 */

	public void partition(T pivot) {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		Node turtle = head, rabbit, equalToPivot = null;
		while (turtle != null) {
			if (turtle.element.compareTo(pivot) >= 0) {
				rabbit = turtle.nextNode;
				equalToPivot = null;
				while (rabbit != null && rabbit.element.compareTo(pivot) >= 0) {
					if (rabbit.element.compareTo(pivot) == 0) {
						equalToPivot = rabbit;
					}
					rabbit = rabbit.nextNode;
				}
				if (rabbit == null) {
					if (equalToPivot == null) {
						return;
					} else {
						T temp = equalToPivot.element;
						equalToPivot.element = turtle.element;
						turtle.element = temp;
					}
				} else {
					T temp = rabbit.element;
					rabbit.element = turtle.element;
					turtle.element = temp;
				}
			}
			turtle = turtle.nextNode;
		}
	}
	
	/**
	 * If our list and the given list have at least one common node. It returns the first common node. Null otherwise.
	 * @param list
	 * @return
	 */

	public T connectionPoint(LinkedList<T> list) {
		int length1 = getSize(), length2 = list.getSize();
		if (length1 == 0 || length2 == 0) {
			throw new EmptyStackException();
		}
		Node pointer1 = head, pointer2 = list.head;
		
		if (length1 > length2) {
			int lengthDifference = length1 - length2;
			for (int i = 0; i < lengthDifference; i++) {
				if (pointer1 == null) {
					return null;
				}
				pointer1 = pointer1.nextNode;
			}
		} else {
			int lengthDifference = length2 - length1;
			for (int i = 0; i < lengthDifference; i++) {
				if (pointer2 == null) {
					return null;
				}
				pointer2 = pointer2.nextNode;
			}
		}
		while (pointer1 != null && pointer2 != null) {
			if (pointer1 == pointer2) {
				return pointer1.element;
			}
			pointer1 = pointer1.nextNode;
			pointer2 = pointer2.nextNode;

		}
		return null;
//		throw new RuntimeException("No merging point.");

	}
	
	/**
	 * Returns true if a given linked list has a loop. False otherwise.
	 * @return
	 */

	public boolean hasLoop() {
		if (head == null) {
			return false;
		}

		Node turtle = head, rabbit = head;
		while (turtle != null && rabbit != null && rabbit.nextNode != null) {
			rabbit = rabbit.nextNode.nextNode;
			turtle = turtle.nextNode;
			if (turtle == rabbit) {
				return true;
			}
		}

		return false;
	}

	public Node nodeInTheLoop() {
		if (head == null) {
			return null;
		}

		Node turtle = head, rabbit = head;
		while (turtle != null && rabbit != null && rabbit.nextNode != null) {
			rabbit = rabbit.nextNode.nextNode;
			turtle = turtle.nextNode;
			if (turtle == rabbit) {
				return turtle;
			}
		}

		return null;
	}
	
	/**
	 * Returns the node at the beginning of the loop.
	 * @return
	 */
	public Node firstElementInTheLoop() {
		if (head == null) {
			return null;
		}

		Node turtle = head, rabbit = head;
		while (turtle != null && rabbit != null && rabbit.nextNode != null) {
			rabbit = rabbit.nextNode.nextNode;
			turtle = turtle.nextNode;
			if (turtle == rabbit) {
				break;
			}
		}

		rabbit = head;
		do {
			rabbit = rabbit.nextNode;
			turtle = turtle.nextNode;
		} while (rabbit != turtle);

		return turtle;

	}

	public LinkedList<T> reverseHalf(LinkedList<T> list) {
		int size = list.getSize();
		Node pointer = list.head;
		LinkedList<T> newList = new LinkedList<>();
		MyStackInterface<T> stack = new LinkedList<T>();
		for (int i = 0; i < size / 2; i++) {
			stack.push(pointer.element);
			pointer = pointer.nextNode;
		}
		while (stack.getSize() != 0) {
			newList.addLast(stack.pop());
		}
		return newList;

	}
	
	/**
	 * Check if the given linked list is a palindrome.
	 * @param list
	 * @return
	 */

	public boolean isPalindrome(LinkedList<T> list) {
		int listSize = list.getSize();
		if (listSize == 0) {
			return true;
		}
		int halfSize = listSize % 2 == 0 ? listSize / 2 : (listSize / 2 + 1);
		Node pointer1 = list.getNode(halfSize);
		LinkedList<T> reversedHalf = reverseHalf(list);
		Node r = reversedHalf.head;
		for (int i = 0; i < reversedHalf.size; i++) {
			r = r.nextNode;
		}

		Node pointer2 = reversedHalf.head;
		while (pointer1 != null && pointer2 != null) {

			if (pointer1.element != pointer2.element) {
				return false;
			}
			pointer1 = pointer1.nextNode;
			pointer2 = pointer2.nextNode;
		}
		return true;

	}

	public class LLIterator implements Iterator<T> {

		private Node cursor;

		public LLIterator() {
			cursor = head;
		}

		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		@Override
		public T next() {
			T result = cursor.element;
			cursor = cursor.nextNode;
			return result;
		}

	}

	@Override
	public Iterator<T> iterator() {
		return new LLIterator();
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Iterator<T> iter = iterator();
		while (iter.hasNext()) {
			T element = iter.next();
			result.append(element + " ");
		}
		return result.toString();
	}

	class Node {
		private T element;
		Node nextNode;

		public Node(T element) {
			this.element = element;
		}

		public Node(T element, Node nextNode) {
			this.element = element;
			this.nextNode = nextNode;
		}

		@Override
		public String toString() {
			return element.toString();
		}
		
	}

	Node head;
	Node tail;
	private int size;

	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

}