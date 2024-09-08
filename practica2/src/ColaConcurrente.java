import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ColaConcurrente {

	private Nodo head;
	private Nodo tail;
	private ExecutorService executor;

	public ColaConcurrente(int n_threads) {
		this.head = new Nodo("hnull");
		this.tail = new Nodo("tnull");
		this.head.next = this.tail;
		this.executor = Executors.newFixedThreadPool(n_threads);

	}

	public Boolean enq(String x) {
		Future<Boolean> result = executor.submit(() -> {
			Nodo new_node = new Nodo(x);
			if (this.head.next == this.tail) {
				new_node.next = this.tail;
				this.head.next = new_node;
			} else {
				Nodo last = this.tail.next;
				new_node.next = tail;
				last.next = new_node;
			}
			tail.next = new_node;
			return true;
		});
		try {
			return result.get();
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public String deq() {
		Future<String> result = executor.submit(() -> {
			if (this.head.next == this.tail) {
				return "empty";
			}
			Nodo first = this.head.next;
			this.head.next = first.next;
			return first.item;
		});
		try {
			return result.get();
		} catch (Exception e) {
			System.out.println(e);
			return "Fallo";
		}
	}

	public void print() {
		executor.execute(() -> {
			System.out.println("Printing queue");
			Nodo pred = this.head;
			Nodo curr = pred.next;
			System.out.println(pred.item);
			while (curr.item != "tnull") {
				pred = curr;
				curr = curr.next;
				System.out.println(pred.item);
			}
			return;

		});
		executor.shutdown();
		return;
	}

	public static void main(String[] args) {
		int n_hilos = 4;
		ColaConcurrente queue = new ColaConcurrente(n_hilos);
		queue.deq();
		queue.enq("x");
		queue.enq("a");
		System.out.println(queue.deq());
		//queue.enq("b");
		//queue.enq("c");
		//System.out.println(queue.deq());
		//queue.deq();
		//queue.deq();
		//queue.deq();
		//queue.enq("x");
		queue.print();
	}
}
