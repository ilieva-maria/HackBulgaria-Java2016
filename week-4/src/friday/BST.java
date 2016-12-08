package friday;

public class BST<T extends Comparable<T>> implements BinarySearchTreeInterface<T> {
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
	}

	@Override
	public void insert(T element) {
		if (root == null) {
			root = new Node(element);
		} else {
			insertRecursive(element, root);
		}
	}

	public void insertRecursive(T element, Node currentNode) {
		if (element.compareTo(currentNode.element) < 0) {
			if (currentNode.leftChild == null) {
				currentNode.leftChild = new Node(element);
			} else {
				insertRecursive(element, currentNode.leftChild);
			}
		} else {
			if (currentNode.rightChild == null) {
				currentNode.rightChild = new Node(element);
			} else {
				insertRecursive(element, currentNode.rightChild);
			}
		}
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean find(T element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void print() {
		printRecursive(root);

	}

	public void printRecursive(Node currentNode) {
		if (currentNode != null) {
			printRecursive(currentNode.leftChild);
			System.out.println(currentNode.element);
			printRecursive(currentNode.rightChild);
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}