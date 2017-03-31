package com.hackbulgaria.map;

import java.lang.reflect.Array;

public class Map<K, V> implements MapInterface<K, V> {
	private BucketInterface<K, V>[] buckets;
	private final static double loadFactor = 5;
	private int size;
	private int capacity;

	@SuppressWarnings("unchecked")
	public Map() {
		size = 0;
		capacity = 8;

		buckets = (LinkedBucket<K, V>[]) Array.newInstance(LinkedBucket.class, capacity);
	}

	@Override
	public void put(K key, V value) {
		int hashValue = calculateBucketIndex(key);
		if (size / capacity >= loadFactor) {
			resize();
		}
		if (buckets[hashValue] == null) {
			buckets[hashValue] = new LinkedBucket<>();
		}

		if (buckets[hashValue].add(key, value)) {
			size++;
		}

	}

	@Override
	public V get(K key) {
		int hashValue = calculateBucketIndex(key);
		return buckets[hashValue].get(key);
	}

	private int calculateBucketIndex(K key) {
		if (key != null) {
			int hashValue = Math.abs(key.hashCode()) % capacity;
			return hashValue;
		}
		throw new RuntimeException("Key is null!");
	}

	@Override
	public void remove(K key) {
		int hashValue = calculateBucketIndex(key);
		if (buckets[hashValue].remove(key)) {
			size--;
		}

	}

	@Override
	public boolean containsKey(K key) {
		return (get(key) != null);
	}

	private void resize() {
		capacity *= 2;
		@SuppressWarnings("unchecked")
		BucketInterface<K, V>[] newBuckets = (LinkedBucket<K, V>[]) Array.newInstance(LinkedBucket.class, capacity);
		for (BucketInterface<K, V> bucket : buckets) {
			if (bucket != null) {
				for (Entry<K, V> entry : bucket) {
					int hashValue = calculateBucketIndex(entry.getKey());
					if (newBuckets[hashValue] == null) {
						newBuckets[hashValue] = new LinkedBucket<>();
					}

					newBuckets[hashValue].add(entry);
				}
			}

		}
		buckets = newBuckets;
	}

	public void print() {
		for (BucketInterface<K, V> bucket : buckets) {
			if (bucket != null) {
				for (Entry<K, V> entry : bucket) {
					System.out.print(entry.getKey() + ":" + entry.getValue() + " ; ");
				}
				// System.out.println("");
			}

		}
		System.out.println("");
	}
}
