package myLinkedList;

public class Main {

	public static void main(String[] args) {
		MyLinkedList<Integer> l3 = new MyLinkedList<>();
		MyLinkedList<Integer> l4 = new MyLinkedList<>();
		l3.addFirst(5);
		l3.addFirst(6);

		l4.addFirst(1);
		l4.addFirst(2);
		l4.addFirst(3);
		l4.addFirst(6);
		l4.addLast(8);
		l4.addLast(9);

		System.out.println(l3.toString());
		System.out.println(l4.toString());

		l3.addList(l4);
		System.out.println(l3);

		MyQueueInterface<Integer> q = new MyLinkedList<>();
		q.enqueue(5);
		System.out.println(q);

		MyStackInterface<Integer> s = new MyLinkedList<>();
		s.push(6);
		System.out.println(s);

		MyQueueInterface<Integer> queue = new MyQueue<>();
		queue.enqueue(1);
		System.out.println(queue.peek());

		MyStack<Integer> stack = new MyStack<>();

		stack.push(6);
		stack.push(3);
		stack.push(4);
		stack.print();
		stack.sort();
		stack.print();

		System.out.println(stack.getMin());
		stack.pop();
		System.out.println(stack.getMin());
		stack.pop();
		System.out.println(stack.getMin());

	}
}
