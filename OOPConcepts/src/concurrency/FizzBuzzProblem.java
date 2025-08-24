package concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class FizzBuzz {
	int i = 0;
	int LIMIT = 50;
	CountDownLatch countDownLatch;
	CyclicBarrier cyclicBarrier;
	Semaphore coordinator = new Semaphore(1);
	Semaphore fizz = new Semaphore(0);
	Semaphore buzz = new Semaphore(0);
	Semaphore fizzBuzz = new Semaphore(0);
	Semaphore number = new Semaphore(0);

	public FizzBuzz(int limit, CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier) {
		this.LIMIT = limit;
		this.countDownLatch = countDownLatch;
		this.cyclicBarrier = cyclicBarrier;
	}

	public void coordinate() {
		while (true) {
			try {
				coordinator.acquire();
				i++;
				if (i > LIMIT) {
					fizzBuzz.release();
					buzz.release();
					fizz.release();
					number.release();
					try {
						cyclicBarrier.await();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				if (i % 5 == 0 && i % 3 == 0) {
					fizzBuzz.release();
				} else if (i % 5 == 0 && i % 3 != 0) {
					buzz.release();
				} else if (i % 5 != 0 && i % 3 == 0) {
					fizz.release();
				} else {
					number.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void fizz() {
		while (true) {
			try {
				fizz.acquire();
				if (i > LIMIT) {
					System.out.println("Terminated fizz");
					countDownLatch.countDown();
					try {
						cyclicBarrier.await();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				System.out.println("Fizz");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				coordinator.release();
			}
		}

	}

	public void buzz() {
		while (true) {
			try {
				buzz.acquire();
				if (i > LIMIT) {
					System.out.println("Terminated buzz");
					countDownLatch.countDown();
					try {
						cyclicBarrier.await();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				System.out.println("Buzz");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				coordinator.release();
			}
		}
	}

	public void fizzBuzz() {
		while (true) {
			try {
				fizzBuzz.acquire();
				if (i > LIMIT) {
					System.out.println("Terminated fizzbuzz");
					countDownLatch.countDown();
					try {
						cyclicBarrier.await();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				System.out.println("Fizzbuzz");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				coordinator.release();
			}
		}
	}

	public void number() {
		while (true) {
			try {
				number.acquire();
				if (i > LIMIT) {
					System.out.println("Terminated number");
					countDownLatch.countDown();
					try {
						cyclicBarrier.await();
					} catch (BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				System.out.println(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				coordinator.release();
			}
		}
	}
}

public class FizzBuzzProblem {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(4);
		CyclicBarrier cyclicBarrier = new CyclicBarrier(6, () -> System.out.println("Barrier breached!!"));
		FizzBuzz fb = new FizzBuzz(50, countDownLatch, cyclicBarrier);
		ExecutorService service = Executors.newFixedThreadPool(5);
		service.submit(() -> {
			fb.coordinate();
		});
		service.submit(() -> {
			fb.fizz();
		});
		service.submit(() -> {
			fb.buzz();
		});
		service.submit(() -> {
			fb.fizzBuzz();
		});
		service.submit(() -> {
			fb.number();
		});
		service.shutdown();
		countDownLatch.await();
		try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Terminated main thread");
	}
}
