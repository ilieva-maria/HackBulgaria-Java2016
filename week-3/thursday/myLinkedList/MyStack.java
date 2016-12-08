package myLinkedList;

public class MyStack<T extends Comparable<T>> implements MyStackInterface<T> {
	private MyStackInterface<T> stack = new MyLinkedList<>();
	private MyStackInterface<T> minStack = new MyLinkedList<>();

	public T getMin() {
		return minStack.peek();
	}

	@Override
	public void push(T element) {
		if (minStack.getSize() != 0) {
			T min = minStack.peek();
			if (min.compareTo(element) <= 0) {
				minStack.push(min);
			} else {
				minStack.push(element);
			}
		} else {
			minStack.push(element);
		}
		stack.push(element);
	}

	@Override
	public T pop() {
		minStack.pop();
		return stack.pop();

	}

	@Override
	public T peek() {
		return stack.peek();
	}

	@Override
	public int getSize() {
		return stack.getSize();
	}

	public void sort() {
		MyStackInterface<T> temp = new MyLinkedList<>();

		while (stack.getSize() != 0) {
			T element = stack.pop();
			if (temp.getSize() == 0 || temp.peek().compareTo(element) < 0) {
				temp.push(element);
			} else {
				while (temp.getSize() != 0 && temp.peek().compareTo(element) >= 0) {
					stack.push(temp.pop());
				}
				temp.push(element);
			}
		}

		while (temp.getSize() != 0) {
			stack.push(temp.pop());
		}
	}

	public void print() {
		MyStackInterface<T> temp = new MyLinkedList<>();
		System.out.print("stack: ");
		while (stack.getSize() != 0) {
			System.out.print(stack.peek() + " ");
			temp.push(stack.pop());
		}
		System.out.println();
		while (temp.getSize() != 0) {
			stack.push(temp.pop());
		}
	}

}
