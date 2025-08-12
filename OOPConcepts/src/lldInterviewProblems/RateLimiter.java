package lldInterviewProblems;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {
	private static final Map<String, RateLimiter> tokenMap = new ConcurrentHashMap<String, RateLimiter>();
	private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();;
	private int bucketSize;
	private int restoreRate;
	private int restorePeriodInSec;
	private final AtomicInteger tokens;
	private long lastRefilTimeStamp;

	public static RateLimiter getInstance(String ip, int bucketSize, int restoreRate, int restorePeriodInSec) {
		return tokenMap.computeIfAbsent(ip, key -> new RateLimiter(bucketSize, restoreRate, restorePeriodInSec));
	}

	private RateLimiter(int bucketSize, int restoreRate, int restorePeriodInSec) {
		this.tokens = new AtomicInteger(bucketSize);
		this.restoreRate = restoreRate;
		this.bucketSize = bucketSize;
		this.restorePeriodInSec = restorePeriodInSec;

		startTokenRestoreScheduler();
	}

	private void refill() {
		System.out.println("Restoring Tokens");
		/*
		 * int capacity = this.tokens.intValue() + this.restoreRate; if (capacity >
		 * bucketSize) { this.tokens.set(bucketSize); } else {
		 * this.tokens.set(capacity); }
		 */
		long now = System.currentTimeMillis();
		int token = (int) Math.round((now - lastRefilTimeStamp) / 1000.0 * restoreRate);
		tokens.updateAndGet(curr -> Math.min(curr + token, bucketSize));
		this.lastRefilTimeStamp = now;
	}

	private void consumeToken() {
		this.tokens.updateAndGet(curr -> Math.max(0, curr - 1));
	}

	public boolean filter() {
		if (this.tokens.intValue() > 0) {
			consumeToken();
			return true;
		} else {
			return false;
		}
	}

	private void startTokenRestoreScheduler() {
		/*
		 * new Thread(() -> { while (true) { try { Thread.sleep(restorePeriodInSec *
		 * 1000l); } catch (InterruptedException e) { e.printStackTrace(); }
		 * this.restoreToken(); } }).start();
		 */
		scheduler.scheduleAtFixedRate(this::refill, restorePeriodInSec, restorePeriodInSec, TimeUnit.SECONDS);

	}

	public static void shutdownTokenRestore() {
		scheduler.shutdown(); // Initiates shutdown

		try {
			if (!scheduler.awaitTermination(10, TimeUnit.SECONDS)) {
				System.err.println("Scheduler did not terminate in time. Forcing shutdown...");
				scheduler.shutdownNow(); // Force shutdown if not terminated
			}
		} catch (InterruptedException e) {
			System.err.println("Shutdown interrupted. Forcing shutdown...");
			scheduler.shutdownNow();
			Thread.currentThread().interrupt(); // Restore interrupt status
		}
	}
}
