package com.hackbulgaria.map;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedBucket<K, V> implements BucketInterface<K, V>, Iterable<Entry<K, V>> {

	private LinkedList<Entry<K, V>> list;

	public LinkedBucket() {
		list = new LinkedList<>();
	}

	@Override
	public boolean add(K key, V value) {
		if (get(key) == null) {
			list.add(new Entry<K, V>(key, value));
			return true;
		} else {
			Entry<K, V> entry = getEntry(key);
			entry.setValue(value);
			return false;
		}
	}

	public Entry<K, V> getEntry(K key) {
		for (Entry<K, V> entry : list) {
			if (entry.getKey().equals(key)) {
				return entry;
			}
		}
		return null;
	}

	@Override
	public boolean remove(K key) {
		return list.remove(getEntry(key));
	}

	@Override
	public V get(K key) {
		Entry<K, V> entry = getEntry(key);
		V value = null;
		if (entry != null) {
			value = entry.getValue();
		}
		return value;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new LBIterator();
	}

	public class LBIterator implements Iterator<Entry<K, V>> {

		private Iterator<Entry<K, V>> iter = list.iterator();

		public boolean hasNext() {
			return iter.hasNext();
		}

		public Entry<K, V> next() {
			return (Entry<K, V>) iter.next();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public boolean add(Entry<K, V> entry) {
		return add(entry.getKey(), entry.getValue());
	}

}
