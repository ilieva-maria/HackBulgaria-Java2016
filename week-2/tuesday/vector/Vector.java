package vector;

import java.util.Iterator;

public class Vector<T> implements Iterable<T> {
	int size = 0;
	int capacity = 10;
	T[] arr;

	@SuppressWarnings("unchecked")
	public Vector() {
		arr = (T[]) new Object[10];
	}

	@SuppressWarnings("unchecked")
	public Vector(int capacity) {
		this.capacity = capacity;
		arr = (T[]) new Object[capacity];
	}

	public Vector(T[] arr) {
		for (int i = 0; i < arr.length; i++) {
			this.add(arr[i]);
		}
	}

	public T get(int index) {
		return arr[index];
	}

	public void set(int index, T data) {
		arr[index] = data;
	}

	public void add(T data) {
		if (size == capacity) {
			resize();
		}
		arr[size] = data;
		size++;
	}

	public void resize() {
		capacity *= 2;
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			temp[i] = arr[i];
		}
		arr = temp;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "";
		} else {
			StringBuilder result = new StringBuilder("[");
			for (int i = 0; i < size - 1; i++) {
				result.append(arr[i] + ", ");
			}
			result.append(arr[size - 1]).append("]");
			return result.toString();
		}

	}

	public class VecIterator implements Iterator<T> {

		private int index = 0;

		public VecIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			System.out.println(index + " ");
			return index != size - 1;
		}

		@Override
		public T next() {
			T result = arr[index];
			index++;
			return result;
		}

	}

	@Override
	public Iterator<T> iterator() {
		return new VecIterator();
	}

}
