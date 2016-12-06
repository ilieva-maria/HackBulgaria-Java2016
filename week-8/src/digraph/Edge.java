package digraph;

public class Edge<T> implements Comparable<Edge<T>> {
	private Vertex<T> source;
	private Vertex<T> destination;
	private int weight;

	public Edge(Vertex<T> source, Vertex<T> destination, int weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public Edge(Vertex<T> source, Vertex<T> destination) {
		this(source, destination, 0);
	}

	public Vertex<T> getSource() {
		return source;
	}

	public Vertex<T> getDestination() {
		return destination;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge<T> o) {
		return Integer.compare(getWeight(), o.getWeight());
	}

	@Override
	public String toString() {
		return "[" + getSource().toString() + " - " + getWeight() + " -> " + getDestination().toString() + "]";
	}

}
