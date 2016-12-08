package linkedList;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {

	Node head;

	public LinkedList() {
		head = null;
	}

	public void add(T data) {
		if (head == null) {
			head = new Node(data);
		} else {
			Node temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = new Node(data);
		}
	}

	public void insertAfter(T after, T data) {
		Node temp = head;
		while (temp.next.data != after) {
			temp = temp.next;
		}
		Node n = new Node(data);

		n.next = temp.next;
		temp.next = n;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node temp = head;
		while (temp != null) {
			result.append(temp.data + " ");
			temp = temp.next;
		}
		return result.toString();
	}

	private class Node {
		T data;
		Node next;

		public Node(T data) {
			this.data = data;
			next = null;
		}
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
			T result = cursor.data;
			cursor = cursor.next;
			return result;
		}

	}

	@Override
	public Iterator<T> iterator() {
		return new LLIterator();
	}
}
