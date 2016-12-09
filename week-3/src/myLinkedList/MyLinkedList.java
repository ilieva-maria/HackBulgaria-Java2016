package myLinkedList;

import java.util.EmptyStackException;
import java.util.Iterator;


public class MyLinkedList<T extends Comparable<T>>
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

	private Node getNode(int index) {
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

	@Override
	public int getSize() {
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

	private class Node {
		private T element;
		Node nextNode;

		public Node(T element) {
			this.element = element;
		}

		public Node(T element, Node nextNode) {
			this.element = element;
			this.nextNode = nextNode;
		}
	}

	private Node head;
	private Node tail;
	private int size;

	public MyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

}
