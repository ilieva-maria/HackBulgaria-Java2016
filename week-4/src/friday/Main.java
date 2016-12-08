package friday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
	public static void main(String[] args) {
//		BinarySearchTree<Integer> t = new BinarySearchTree<>();
//		t.insert(5);
//		t.insert(2);
//		t.insert(10);
//		t.print();
//		
//		BinarySearchTree<Integer> t1 = new BinarySearchTree<>();
//		t1.insert(10);
//		t1.insert(2);
//		t1.insert(5);
//		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
//		System.out.println(tree.identicalTrees(t, t1));
//		BinarySearchTreeInterface<Integer> tree = new BST<>();
//		tree.insert(5);
//		tree.insert(2);
//		tree.insert(10);
//		tree.print();
//		System.out.println(t.find(10));
//		System.out.println(t.find(5));
//		System.out.println(t.find(2));
//		System.out.println(t.find(101));
//		t.remove(2);
//		t.print();
//		t.remove(5);
//		t.print();
//		t.remove(10);
//		t.print();
		
		List<Integer> arr = new ArrayList<>(Arrays.asList(1, 5, 8, 2, 0, 0, 13, 11, 7));
//		System.out.println(sortArrayUsingBST(arr).toString());
		BinarySearchTree<Integer> test = new BinarySearchTree<>();
		test.buildTree(arr);
		test.print();
		System.out.println(test.getSize());
		System.out.println(test.depth());
		
	}
	
	
	
	public static <T extends Comparable<T>> List<T> sortArrayUsingBST (List<T> arr) {
		BinarySearchTree<T> tree = new BinarySearchTree<>();
		List<T> list = new ArrayList<>();
		for (int i = 0; i < arr.size(); i++) {
			tree.insert(arr.get(i));
		}
		return tree.toSortedArray(list);
	}
	
	
	
	

}
