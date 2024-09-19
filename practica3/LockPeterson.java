import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class LockPeterson {
	static public int[] counter_threads = new int[4];

	private static void task(DoublePeterson lock, CounterNaive counter) {
		try {
			lock.lock();
			//Thread currentThread = Thread.currentThread();
			//long id = currentThread.getId() - 20;
			//int i = (int) (id % 4);
			//counter_threads[i]++;
			counter.increment();
			//if (id > 24 || id < 20) {
			//	System.out.println("IDDDD:" + id);
			//}
			//System.out.println(counter.getValue());
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DoublePeterson lock = new DoublePeterson();
		CounterNaive counter = new CounterNaive();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		for (int i = 0; i < 4; i++) {
			counter_threads[i] = 0;
		}

		for (int i = 0; i < 400; i++) {
			executor.execute(() -> task(lock, counter));
		}
		executor.shutdown();

		try {
			Thread.sleep(500);
			System.out.println("Valor final: " + counter.getValue());
			for (int i = 0; i < 4; i++) {
			    //System.out.println("Hilo " + (i + 1) + " realizo " + counter_threads[i] + " operaciones");
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
}
