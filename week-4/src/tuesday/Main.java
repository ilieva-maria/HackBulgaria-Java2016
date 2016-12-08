package tuesday;


public class Main {
	public static void main(String[] args) throws Exception {

		 LinkedList<Integer> l = new LinkedList<>();
		
		 for (int i = 0; i < 10; i++) {
		 l.addFirst(i);
		 }
		
		 l.add(911, 5);
		 l.remove(5);
		
		 l.addFirst(101);
		 l.addLast(909);
		
		
		
		 System.out.println(l + " <- K-th to last: k = 1");
		 System.out.println(l.findKthToLast(1));
		 
		
		 System.out.println(l + " size:"+ l.getSize());
		 System.out.println("Partition around 4:");
		 l.partition(4);
		 System.out.println(l);
		 LinkedList<Integer> l3 = new LinkedList<>();
		 LinkedList<Integer> l4 = new LinkedList<>();
		 l3.addFirst(5);
		 l3.addFirst(6);
		
		 l4.addFirst(1);
		 l4.addFirst(2);
		 l4.addFirst(3);
		 l4.addFirst(6);
		 l4.addLast(8);
		 l4.addLast(9);
		 l3.head.nextNode.nextNode = l4.head.nextNode.nextNode.nextNode.nextNode;
		    
		 System.out.println(l3 + "        " + l4);
		
		 System.out.println(l3.connectionPoint(l4) + "  <- connection point");
		
		 LinkedList<Integer> llist = new LinkedList<>();
		
		 llist.addFirst(20);
		 llist.addFirst(4);
		 llist.addFirst(15);
		 llist.addFirst(10);
		
		 llist.head.nextNode.nextNode.nextNode.nextNode = llist.head;
		
		 System.out.println(llist.hasLoop() + " <- hasLoop");
		 System.out.println(llist.firstElementInTheLoop() + " <- first element in the string");
		 
		
		 l.removeLast();
		 System.out.println(l);
		
		
		
		
		LinkedList<Integer> list = new LinkedList<>();
		list.addLast(1);
		list.addLast(8);
		list.addLast(0);
		list.addLast(8);
		list.addLast(1);
		
		
		System.out.println(list.toString() + " <- Is palindrome?");
		System.out.println(list.isPalindrome(list));


	}
}
