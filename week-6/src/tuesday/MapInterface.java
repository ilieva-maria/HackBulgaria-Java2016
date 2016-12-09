package tuesday;

public interface MapInterface<K, V> {
	public void put(K key, V value);

	public V get(K key);

	public void remove(K key);

	public boolean containsKey(K key);
}
