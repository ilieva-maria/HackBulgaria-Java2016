package linkedList;

import java.util.Iterator;

public class Main {
	public static void main(String[] args) {

		LinkedList<Integer> l = new LinkedList<>();

		for (int i = 0; i < 10; i++) {
			l.add(i);
		}
		System.out.println(l);

		Iterator<Integer> it = l.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}

		System.out.println();
		for (Integer i : l) {
			System.out.print(i + " ");
		}
	}
}
