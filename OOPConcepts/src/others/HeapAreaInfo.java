package others;

public class HeapAreaInfo {
	public static void main(String[] args) {
		Runtime r = Runtime.getRuntime();
		System.out.println(r.maxMemory());
		System.out.println(r.totalMemory());
		System.out.println(r.freeMemory());
		System.out.println(r.availableProcessors());
	}
}
