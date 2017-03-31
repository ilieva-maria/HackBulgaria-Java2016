package com.hackbulgaria.binary_search_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		List<Integer> arr = new ArrayList<>(Arrays.asList(1, 5, 8, 2, 0, 0, 13, 11, 7));
		BinarySearchTree<Integer> test = new BinarySearchTree<>();
		test.buildTree(arr);
		test.print();
		System.out.println(test.getSize());
		System.out.println(test.depth());

	}

	public static <T extends Comparable<T>> List<T> sortArrayUsingBST(List<T> arr) {
		BinarySearchTree<T> tree = new BinarySearchTree<>();
		List<T> list = new ArrayList<>();
		for (int i = 0; i < arr.size(); i++) {
			tree.insert(arr.get(i));
		}
		return tree.toSortedArray(list);
	}
	
	

}
