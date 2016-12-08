package myLinkedList;

public class MyQueue<T extends Comparable<T>> implements MyQueueInterface<T> {

	MyStackInterface<T> input = new MyLinkedList<T>();
	MyStackInterface<T> output = new MyLinkedList<T>();

	@Override
	public void enqueue(T element) {
		input.push(element);
	}

	@Override
	public T dequeue() {
		if (output.getSize() == 0) {
			while (input.getSize() != 0) {
				output.push(input.pop());
			}
		}
		T element = null;
		if (output.getSize() != 0) {
			element = output.pop();
		}

		return element;
	}

	@Override
	public T peek() {
		if (output.getSize() == 0) {
			while (input.getSize() != 0) {
				output.push(input.pop());
			}
		}
		T element = null;
		if (output.getSize() != 0) {
			element = output.peek();
		}

		return element;
	}

	@Override
	public int getSize() {
		return input.getSize() + output.getSize();
	}

}