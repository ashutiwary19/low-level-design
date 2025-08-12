package concurrency;

public class DoubleCheckedSingleton {

	// Volatile ensures visibility and prevents instruction reordering
	private static volatile DoubleCheckedSingleton instance;

	// Private constructor prevents instantiation
	private DoubleCheckedSingleton() {
	}

	public static DoubleCheckedSingleton getInstance() {
		if (instance == null) { // First check (no locking)
			synchronized (DoubleCheckedSingleton.class) {
				if (instance == null) { // Second check (with locking)
					instance = new DoubleCheckedSingleton();
				}
			}
		}
		return instance;
	}
}
