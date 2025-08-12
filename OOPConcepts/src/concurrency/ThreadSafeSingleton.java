package concurrency;

public class ThreadSafeSingleton {

	// Private constructor prevents instantiation from other classes
	private ThreadSafeSingleton() {
	}

	// Static inner class - loaded only when getInstance() is called
	private static class SingletonHelper {
		private static final ThreadSafeSingleton INSTANCE = new ThreadSafeSingleton();
	}

	public static ThreadSafeSingleton getInstance() {
		return SingletonHelper.INSTANCE;
	}
}
