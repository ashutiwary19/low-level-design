import java.util.*;

class LFUCache {
	class CacheNode {
		int key;
		int val;
		int freq;
		CacheNode prev;
		CacheNode next;

		public CacheNode() {
		}

		public CacheNode(int key, int val) {
			this.key = key;
			this.val = val;
			this.freq = 1;
		}
	}

	class DQueue {
		CacheNode head;
		CacheNode tail;
		int size;

		public DQueue() {
			head = tail = new CacheNode();
			size = 0;
		}

		public void add(CacheNode node) {
			tail.next = node;
			node.prev = tail;
			tail = tail.next;
			size++;
		}

		public void remove(CacheNode node) {
			node.prev.next = node.next;
			if (node.next != null)
				node.next.prev = node.prev;
			else {
				tail = tail.prev;
			}
//			node.next = null;
//			node.prev = null;
			size--;
		}

		public CacheNode getFirst() {
			return head.next;
		}

		public CacheNode getLast() {
			return tail == head ? null : tail;
		}

		public int getSize() {
			return size;
		}
	}

	int capacity;
	TreeMap<Integer, DQueue> freqMap = new TreeMap<>();
	Map<Integer, CacheNode> valMap = new HashMap<>();

	public LFUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		if (key == 12) {
			System.out.println();
		}
		int val = -1;
		if (valMap.get(key) != null) {
			val = valMap.get(key).val;
			updateKey(key, val);
		}
		return val;
	}

	public void updateKey(int key, int val) {
		if (key == 2) {
			System.out.println();
			freqMap.forEach((key1, val1) -> {
				System.out.println(key1);
				CacheNode node = val1.getFirst();
				while (node != null) {
					System.out.print("[" + node.key + " : " + node.val + " : " + node.freq + "] -> ");
					node = node.next;
				}
				System.out.println();

			});
			System.out.println();

		}
		CacheNode cache = valMap.get(key);
		DQueue que = freqMap.get(cache.freq);
		if (que != null) {
			que.remove(cache);
			if (que.getSize() == 0) {
				freqMap.remove(cache.freq);
			}
		}
		cache.freq += 1;
		cache.val = val;
		freqMap.putIfAbsent(cache.freq, new DQueue());
		freqMap.get(cache.freq).add(cache);
	}

	public void evict() {
		Map.Entry<Integer, DQueue> entry = freqMap.firstEntry();
		DQueue que = entry.getValue();
		CacheNode evict = que.getFirst();
		if (evict != null) {
			valMap.remove(evict.key);
			que.remove(evict);
			if (que.getSize() == 0) {
				freqMap.remove(evict.freq);
			}
		}
	}

	public void put(int key, int value) {
		if (key == 12) {
			System.out.println();
		}
		if (valMap.get(key) == null) {
			CacheNode cache = new CacheNode(key, value);
			if (capacity <= 0) {
				evict();
			} else {
				capacity--;
			}
			freqMap.putIfAbsent(1, new DQueue());
			freqMap.get(1).add(cache);
			valMap.put(key, cache);
		} else {
			updateKey(key, value);
		}
	}

	/**
	 * Your LFUCache object will be instantiated and called as such: LFUCache obj =
	 * new LFUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
	 */

	public static void main(String[] args) {
		String[] commands = { "LFUCache", "put", "put", "put", "put", "put", "get", "put", "get", "get", "put", "get",
				"put", "put", "put", "get", "put", "get", "get", "get", "get", "put", "put", "get", "get", "get", "put",
				"put", "get", "put", "get", "put", "get", "get", "get", "put", "put", "put", "get", "put", "get", "get",
				"put", "put", "get", "put", "put", "put", "put", "get", "put", "put", "get", "put", "put", "get", "put",
				"put", "put", "put", "put", "get", "put", "put", "get", "put", "get", "get", "get", "put", "get", "get",
				"put", "put", "put", "put", "get", "put", "put", "put", "put", "get", "get", "get", "put", "put", "put",
				"get", "put", "put", "put", "get", "put", "put", "put", "get", "get", "get", "put", "put", "put", "put",
				"get", "put", "put", "put", "put", "put", "put", "put" };
		int[][] values = { { 10 }, { 10, 13 }, { 3, 17 }, { 6, 11 }, { 10, 5 }, { 9, 10 }, { 13 }, { 2, 19 }, { 2 },
				{ 3 }, { 5, 25 }, { 8 }, { 9, 22 }, { 5, 5 }, { 1, 30 }, { 11 }, { 9, 12 }, { 7 }, { 5 }, { 8 }, { 9 },
				{ 4, 30 }, { 9, 3 }, { 9 }, { 10 }, { 10 }, { 6, 14 }, { 3, 1 }, { 3 }, { 10, 11 }, { 8 }, { 2, 14 },
				{ 1 }, { 5 }, { 4 }, { 11, 4 }, { 12, 24 }, { 5, 18 }, { 13 }, { 7, 23 }, { 8 }, { 12 }, { 3, 27 },
				{ 2, 12 }, { 5 }, { 2, 9 }, { 13, 4 }, { 8, 18 }, { 1, 7 }, { 6 }, { 9, 29 }, { 8, 21 }, { 5 },
				{ 6, 30 }, { 1, 12 }, { 10 }, { 4, 15 }, { 7, 22 }, { 11, 26 }, { 8, 17 }, { 9, 29 }, { 5 }, { 3, 4 },
				{ 11, 30 }, { 12 }, { 4, 29 }, { 3 }, { 9 }, { 6 }, { 3, 4 }, { 1 }, { 10 }, { 3, 29 }, { 10, 28 },
				{ 1, 20 }, { 11, 13 }, { 3 }, { 3, 12 }, { 3, 8 }, { 10, 9 }, { 3, 26 }, { 8 }, { 7 }, { 5 },
				{ 13, 17 }, { 2, 27 }, { 11, 15 }, { 12 }, { 9, 19 }, { 2, 15 }, { 3, 16 }, { 1 }, { 12, 17 }, { 9, 1 },
				{ 6, 19 }, { 4 }, { 5 }, { 5 }, { 8, 1 }, { 11, 7 }, { 5, 2 }, { 9, 28 }, { 1 }, { 2, 2 }, { 7, 4 },
				{ 4, 22 }, { 7, 24 }, { 9, 26 }, { 13, 28 }, { 11, 26 } };
		LFUCache cache = null;

		for (int i = 0; i < commands.length; i++) {
			switch (commands[i]) {
			case "LFUCache":
				cache = new LFUCache(values[i][0]);
				System.out.println("LRUCache initialized with capacity: " + values[i][0]);
				break;
			case "put":
				assert cache != null;
				cache.put(values[i][0], values[i][1]);
				System.out.println("Put: " + values[i][0] + " -> " + values[i][1]);
				break;
			case "get":
				assert cache != null;
				int result = cache.get(values[i][0]);
				System.out.println("Get: " + values[i][0] + " -> " + result);
				break;
			}
		}
	}
}