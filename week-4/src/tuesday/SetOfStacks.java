package tuesday;

import java.util.ArrayList;

import myLinkedList.MyStack;
import myLinkedList.MyStackInterface;

public class SetOfStacks<T extends Comparable<T>> implements MyStackInterface<T> {
	public static void main(String[] args) {
		SetOfStacks<Integer> stacks = new SetOfStacks<>(3);
		stacks.push(1);
		stacks.push(3);
		stacks.push(6);
		stacks.push(2);
		stacks.push(4);
		stacks.push(0);
		System.out.println(stacks.peek());
		stacks.pop();
		System.out.println(stacks.peek());
		System.out.println(stacks.getSize());
		stacks.pushAt(1, 900);
		System.out.println(stacks.popAt(1));
		System.out.println(stacks.popAt(0));
		System.out.println(stacks.popAt(1));
		System.out.println(stacks.getSize());
		System.out.println(stacks.popAt(0));
	}

	private ArrayList<MyStackInterface<T>> stacks;
	int capacity;

	public SetOfStacks(int capacity) {
		stacks = new ArrayList<>();
		this.capacity = capacity;
	}

	@Override
	public void push(T element) {
		if (stacks.isEmpty()) {
			if (capacity > 0) {
				pushNewStack(element);
			} else {
				throw new RuntimeException("Capacity is 0. The element cannot be added.");
			}
		} else {
			MyStackInterface<T> stack = stacks.get(stacks.size() - 1);
			if (stack.getSize() == capacity) {
				pushNewStack(element);
			} else {
				stack.push(element);
			}
		}

	}

	private void pushNewStack(T element) {
		MyStackInterface<T> newStack = new MyStack<T>();
		newStack.push(element);
		stacks.add(newStack);
	}

	@Override
	public T pop() {
		T element = null;
		if (!stacks.isEmpty()) {
			MyStackInterface<T> stack = stacks.get(stacks.size() - 1);
			element = stack.pop();
			if(stack.getSize() == 0) {
				stacks.remove(stacks.size() - 1);
			}
		}
		return element;
	}

	@Override
	public T peek() {
		T element = null;
		if (!stacks.isEmpty()) {
			element = stacks.get(stacks.size() - 1).peek();
		}
		return element;
	}

	@Override
	public int getSize() {
		int size = 0;
		int numberOfStacks = stacks.size();
		if (numberOfStacks > 0) {
			size = (numberOfStacks - 1) * capacity + stacks.get(numberOfStacks - 1).getSize();
		}
		return size;
	}
	
	private void validateIndex(int index) {
		if (index < 0 || index > stacks.size()) {
			throw new IndexOutOfBoundsException("The provided index is out of range: " + index);
		}
	}
	
	public T popAt(int index) {
		validateIndex(index);
		T element = null;
		if (index == stacks.size() - 1){
			return pop();
		} else {
			MyStackInterface<T> stack = stacks.get(index);
			element = stack.pop();
			for (int i = index + 1; i < stacks.size(); i++) {
				pushAt(i - 1, stacks.get(i).pop());
			}
			if (stacks.get(stacks.size() - 1).getSize() == 0) {
				stacks.remove(stacks.size() - 1);
			}
		}
		return element;
	}
	
	public void pushAt(int index, T element) {
		validateIndex(index);
		MyStackInterface<T> stack = stacks.get(index);
		if (stack.getSize() < capacity) {
			stack.push(element);
		} else if (index == stacks.size() - 1){
			pushNewStack(element);
		} else {
			throw new RuntimeException("Exceeded capacity. Cannot push at index: " + index);
		}
	}
}
