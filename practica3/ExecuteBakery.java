import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteBakery {
	static public int[] counter_threads = new int[4];

	private static void task(Bakery4 lock, CounterNaive counter) {
		try {
			lock.lock();
			Thread currentThread = Thread.currentThread();
			long id = currentThread.getId();
			int i = (int) (id % 4);
			counter_threads[i]++;
			counter.increment();
			// System.out.println(counter.getValue());
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 4; i++) {
			counter_threads[i] = 0;
		}
		Bakery4 lock = new Bakery4();
		CounterNaive counter = new CounterNaive();
		ExecutorService executor = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 400; i++) {
			executor.execute(() -> task(lock, counter));
		}
		executor.shutdown();

		try {
			Thread.sleep(500);
			System.out.println("Valor final: " + counter.getValue());
			for (int i = 0; i < 4; i++) {
				System.out.println("Hilo " + (i + 1) + "realizo " + counter_threads[i] + " operaciones");
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
}
