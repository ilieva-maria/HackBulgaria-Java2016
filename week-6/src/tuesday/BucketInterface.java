package tuesday;

public interface BucketInterface<K, V> extends Iterable<Entry<K, V>> {
	public boolean add(K key, V value);

	public boolean add(Entry<K, V> entry);

	public boolean remove(K key);

	public V get(K key);

}
