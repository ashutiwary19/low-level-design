package concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * 🧠 Interview-Level Follow-Ups
Here’s what you might be asked next:

🔍 Design & Architecture
How would you implement backpressure?

What happens if notifyAll() is called when no thread is waiting?

Why not use BlockingQueue instead of manual wait()/notifyAll()?

🔧 Concurrency Internals
What are the tradeoffs between notify() and notifyAll()?

How do you handle spurious wakeups?

What’s the difference between intrinsic locks (synchronized) and ReentrantLock?

🧪 Advanced Extensions
Can you refactor this using Lock and Condition?

How would you implement priority-based consumers?

How would you make this system resilient to thread crashes or exceptions?

“How would you refactor this using BlockingQueue?”

“What happens if notifyAll() is called but no thread is waiting?”

“How would you implement backpressure in a distributed system like Kafka?”

“Can you make your backpressure adaptive—e.g., slow down producers based on queue depth?”
 * 
 * 
 */

class Producer implements Runnable {
	private String name;
	private Queue<Integer> queue;
	private Integer capacity;

	public Producer(Queue<Integer> queue, Integer capacity, String name) {
		this.queue = queue;
		this.name = name;
		this.capacity = capacity;
		LinkedList<Integer> l ;
	}

	public void produce() {
		while (true) {
			System.err.println("Producer Queue : " + queue.size());
			int data = (int) (Math.random() * 100);
			synchronized (queue) {
				if (queue.size() < capacity) {
					System.out.println(Thread.currentThread().getName() + "-Producing");
					queue.offer(data);
					queue.notifyAll();
				} else {
					try {
						System.out.println(Thread.currentThread().getName() + "-Waiting for consumer to consumer");
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		Thread.currentThread().setName(name);
		produce();
	}

}

class Consumer implements Runnable {
	private Queue<Integer> queue;
	private Integer capacity;
	private String name;

	public Consumer(Queue<Integer> queue, Integer capacity, String name) {
		this.queue = queue;
		this.name = name;
		this.capacity = capacity;
	}

	public void consume() {
		while (true) {
			synchronized (queue) {
				System.err.println("Consumer Queue : " + queue.size());
				if (queue.size() > 0) {
					System.out.println(Thread.currentThread().getName() + " - consuming");
					System.out.println("Consumed : " + queue.poll());
					queue.notifyAll();
				} else {
					System.out.println(Thread.currentThread().getName() + "-Waiting for producer to produce");
					try {
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		Thread.currentThread().setName(name);
		consume();
	}
}

public class ProducerConsumer {
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();
		int capacity = 20;
		ExecutorService service = Executors.newFixedThreadPool(20);

		for (int i = 0; i < 10; i++) {
			Producer producer = new Producer(queue, capacity, "Producer-" + i);
			Consumer consumer = new Consumer(queue, capacity, "Consumer-" + i);
			service.submit(producer);
			service.submit(consumer);
		}
		

		service.shutdown();
		try {
			if (service.awaitTermination(10000, TimeUnit.SECONDS)) {
				service.shutdownNow();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
