package friday;

public interface BinarySearchTreeInterface<T extends Comparable<T>> {
	public void insert(T element);
	public void remove(T element);
	public boolean find(T element);
	public void print();
	public boolean isEmpty();
}
