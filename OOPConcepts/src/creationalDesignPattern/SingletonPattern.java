package creationalDesignPattern;

class Singleton1 {
	private static volatile Singleton1 instance;

	private Singleton1() {

	}

	@SuppressWarnings({ "static-access", "unused" })
	public static Singleton1 getInstance() {
		if (instance == null) {
			synchronized (instance) {
				if (instance == null) {
					return instance = new Singleton1();
				}
			}
		}
		return instance;
		/*
		 * if (instance == null) { return instance = new Singleton1(); } else { return
		 * this.instance; }
		 */
	}
}

// Thread safe
// Lazy without synchronization
// JVM guarantees safe class loading

class Singleton2 {
	private static class Singleton3 {
		private static final Singleton2 INSTANCE = new Singleton2();
	}

	public Singleton2 getInstance() {
		return Singleton3.INSTANCE;
	}
}

public class SingletonPattern {

}
