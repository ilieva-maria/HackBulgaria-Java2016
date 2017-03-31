package com.hackbulgaria.binary_search_tree;

import java.util.Collections;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> implements BinarySearchTreeInterface<T> {
	private Node root;

	private class Node {
		T element;
		Node leftChild;
		Node rightChild;

		public Node(T element) {
			this.element = element;
			leftChild = null;
			rightChild = null;
		}

		public T getValue() {
			return element;
		}

		public void setValue(T element) {
			this.element = element;
		}

		public Node(T element, Node left, Node right) {
			this.element = element;
			this.leftChild = left;
			this.rightChild = right;
		}
	}

	public void insert(T element) {
		root = insertNode(element, root);
	}

	private Node insertNode(T element, Node currentNode) {
		if (currentNode == null) {
			return new Node(element);
		} else if (element.compareTo(currentNode.getValue()) < 0) {
			currentNode.leftChild = insertNode(element, currentNode.leftChild);
		} else {
			currentNode.rightChild = insertNode(element, currentNode.rightChild);
		}
		return currentNode;
	}

	public void print() {
		printNode(root);
		System.out.println();
	}

	private void printNode(Node currentNode) {
		if (currentNode != null) {
			printNode(currentNode.leftChild);
			System.out.print(currentNode.getValue() + " ");
			printNode(currentNode.rightChild);
		}
	}

	@Override
	public void remove(T element) {
		root = removeNode(element, root);

	}

	private Node removeNode(T element, Node currentNode) {

		if (element.compareTo(currentNode.element) == 0) {
			if (currentNode.leftChild == null && currentNode.rightChild == null) {
				return null;
			} else if (currentNode.leftChild != null && currentNode.rightChild == null) {
				currentNode = currentNode.leftChild;
			} else if (currentNode.leftChild == null && currentNode.rightChild != null) {
				currentNode = currentNode.rightChild;
			} else {
				T maxLeftElement = getMaxElement(currentNode.leftChild);
				currentNode.setValue(maxLeftElement);
				removeNode(maxLeftElement, currentNode.leftChild);
			}
		} else if (element.compareTo(currentNode.getValue()) < 0) {
			currentNode.leftChild = removeNode(element, currentNode.leftChild);
		} else {
			currentNode.rightChild = removeNode(element, currentNode.rightChild);
		}

		return currentNode;
	}

	private T getMaxElement(Node currentNode) {
		if (currentNode != null) {
			if (currentNode.rightChild == null) {
				return currentNode.getValue();
			} else {
				return getMaxElement(currentNode.rightChild);
			}
		} else {
			throw new RuntimeException("Cannot find max element in the left subtree. Null pointer.");
		}
	}

	@Override
	public boolean find(T element) {
		return findNode(element, root);
	}

	private boolean findNode(T element, Node currentNode) {
		if (currentNode == null) {
			return false;
		} else if (currentNode.getValue().compareTo(element) == 0) {
			return true;
		} else if (element.compareTo(currentNode.getValue()) < 0) {
			return findNode(element, currentNode.leftChild);
		} else {
			return findNode(element, currentNode.rightChild);
		}
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	public boolean identicalTrees(BinarySearchTree<T> treeA, BinarySearchTree<T> treeB) {
		return identicalTreesRecursive(treeA.root, treeB.root);
	}

	private boolean identicalTreesRecursive(Node nodeA, Node nodeB) {
		if (nodeA == null && nodeB == null) {
			return true;
		} else if (nodeA != null && nodeB != null) {
			return (nodeA.getValue() == nodeB.getValue() && identicalTreesRecursive(nodeA.leftChild, nodeB.leftChild)
					&& identicalTreesRecursive(nodeA.rightChild, nodeB.rightChild));
		}
		return false;
	}

	private List<T> toSortedArray(Node currentNode, List<T> list) {
		if (currentNode != null) {
			toSortedArray(currentNode.leftChild, list);
			list.add(currentNode.getValue());
			toSortedArray(currentNode.rightChild, list);
		}
		return list;
	}

	public List<T> toSortedArray(List<T> list) {
		return toSortedArray(root, list);
	}

	private Node build(List<T> list) {
		if (list.isEmpty()) {
			return null;
		} else {
			int middle = list.size() / 2;
			T element = list.get(middle);
			Node left = build(list.subList(0, middle));
			Node right = build(list.subList(middle + 1, list.size()));
			return new Node(element, left, right);
		}

	}

	public void buildTree(List<T> list) {
		Collections.sort(list);
		root = build(list);
	}

	private int size(Node node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + size(node.leftChild) + size(node.rightChild);
		}
	}

	public int getSize() {
		return size(root);
	}

	private int depth(Node node) {
		if (node == null) {
			return -1;
		} else {
			return 1 + Math.max(depth(node.leftChild), depth(node.rightChild));
		}
	}

	public int depth() {
		return depth(root);
	}

}
