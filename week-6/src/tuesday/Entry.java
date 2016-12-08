package tuesday;

public class Entry<K, V> {
	private K key;
	private V value;

	public Entry(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public K getKey() {
		return key;
	}

}