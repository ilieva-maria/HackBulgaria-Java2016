package digraph;

import java.util.LinkedList;
import java.util.List;

public class Vertex<T> {
	private T label;
	private int weight;
	private List<Edge<T>> adjacencyList;
	private boolean visited;

	public Vertex(T label, int weight) {
		this.label = label;
		this.weight = weight;
		adjacencyList = new LinkedList<>();
	}

	public Vertex(T label) {
		this.label = label;
		adjacencyList = new LinkedList<>();
	}

	public void addEdge(Vertex<T> destination, int weight) {
		adjacencyList.add(new Edge<T>(this, destination, weight));
	}

	public void addEdge(Vertex<T> destination) {
		adjacencyList.add(new Edge<T>(this, destination, 0));
	}

	public T getLabel() {
		return label;
	}

	public void setLabel(T label) {
		this.label = label;
	}

	public List<Edge<T>> getAdjacencyList() {
		return adjacencyList;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return label.toString();
	}

	public boolean equals(Vertex<T> other) {
		return label.equals(other.getLabel());
	}
}
