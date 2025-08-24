package lldInterviewProblems;

import java.util.Arrays;
import java.util.Objects;

// requirements
// Methods it should support
// put, get and remove
// Collisions
// Growable on loadfactor=0.75

// Extra how to make it thread safe
// Make all methods synchronized
// Use an array of reentrant locks for each operations on bucket level, add globallock StampedLock for resize 
// Use readWritelock if the map is read extensive
// Segmented locks
// Immutable snapshot, use COWS (copy on write swap) for read heavy map

public class CustomHashMap<T, V> {
	final static class CustomEntry<T, V> {
		private T key;
		private V value;
		CustomEntry<T, V> next;

		public CustomEntry(T t, V v) {
			this.key = t;
			this.value = v;
		}

		@Override
		public String toString() {
			return "CustomEntry [key=" + key + ", value=" + value + "]";
		}

	}

	private double loadFactor = 0.75;
	CustomEntry<T, V>[] buckets;
	private int capacity = 10;
	private int size = 0;

	public CustomHashMap() {
		buckets = new CustomEntry[10];
		size = 0;
	}

	private int hash(T key) {
		if (key == null)
			return 0;
		int index = Math.abs(key.hashCode()) % capacity;
		return index;
	}

	private boolean hasCollision(int index) {
		return buckets[index] != null;
	}

	private boolean isOverloaded() {
		double currLoad = size * 1.0 / capacity;
		return currLoad >= loadFactor;
	}

	// put value
	public void put(T key, V value) {
		resize();
		boolean isUpdate = false;
		int index = hash(key);
		if (hasCollision(index)) {
			CustomEntry<T, V> customEntry = buckets[index];
			while (customEntry != null) {
				if (Objects.equals(customEntry.key, key)) {
					customEntry.value = value;
					isUpdate = true;
					break;
				}
				if (customEntry.next == null) {
					customEntry.next = new CustomEntry<>(key, value);
					break;
				}
				customEntry = customEntry.next;
			}
		} else {
			buckets[index] = new CustomEntry<>(key, value);
		}
		if (!isUpdate)
			size++;
	}

	// get value
	public V get(T key) {
		int index = hash(key);
		if (buckets[index] != null) {
			CustomEntry<T, V> entry = buckets[index];
			while (entry != null) {
				if (Objects.equals(entry.key, key)) {
					return entry.value;
				}
				entry = entry.next;
			}
		}
		return null;
	}

	// remove value
	public V remove(T key) {
		int index = hash(key);
		CustomEntry<T, V> prev = null;
		CustomEntry<T, V> curr = buckets[index];
		while (curr != null) {
			if (Objects.equals(curr.key, key)) {
				if (prev == null)
					buckets[index] = curr.next;
				else
					prev.next = curr.next;
				size--;
				return curr.value;
			}
			curr = curr.next;
		}

		return null;
	}

	public int size() {
		return size;
	}

	// resize
	private void resize() {
		if (isOverloaded()) {
			int oldCapacity = capacity;
			capacity *= 2;
			CustomEntry<T, V>[] oldBuckets = buckets;
			buckets = new CustomEntry[capacity];
			for (int i = 0; i < oldCapacity; i++) {
				CustomEntry<T, V> entry = oldBuckets[i];
				while (entry != null) {
					put(entry.key, entry.value);
					entry = entry.next;
				}
			}
		}
	}

	private void clear() {
		capacity = 10;
		buckets = new CustomEntry[capacity];
		size = 0;
	}

	private boolean containsKey(T key) {
		CustomEntry<T, V> head = buckets[hash(key)];
		while (head != null) {
			if (Objects.equals(head.key, key))
				return true;
			head = head.next;
		}
		return false;
	}

	@Override
	public String toString() {
		return Arrays.toString(buckets);
	}

	public static void main(String[] args) {
		CustomHashMap<String, String> map = new CustomHashMap<String, String>();
		map.put("Hello", "Hello");
		map.put("Hello", "Hello");
		map.put("Hello2", "Hello2");
		map.put("Name", "Name");
		map.put("Somerandomkargekey", "Somerandomkargevalue");
		map.put("Somerandomkargekey2", "Somerandomkargevalue2");
		map.put("Somerandomkargekey3", "Somerandomkargevalue3");
		map.put("Somerandomkargekey4", "Somerandomkargevalue5");

		System.out.println(map);
	}
}
