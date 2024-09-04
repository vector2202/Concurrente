import java.util.concurrent.locks.*;

public class ColaConcurrenteLock {
	public static void main(String[] args) {
		
	    ColaLock runnable = new ColaLock();
	    	Thread threadA = new Thread(myRunnable);
		Thread threadB = new Thread(myRunnable);
		Thread threadC = new Thread(myRunnable);
		threadA.start();
		threadB.start();
		threadC.start();

		try{
		    Thread.sleep(500);
		    threadA.join();
			threadB.join();
			threadC.join();
		    
		}catch(InterruptedException e) {
			System.out.println(e);
		}
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

class ColaLock implements Runnable {
    private Nodo head;
	private Nodo tail;
	private Lock lock = new ReentrantLock();
    public Boolean enq(String x){
	try{
		lock.lock();
		
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
	}finally{lock.unlock();}
	
    }
    public String deq(){
	try{
	    lock.lock();
	    if (this.head.next == this.tail) {
				return "empty";
			}
			Nodo first = this.head.next;
			this.head.next = first.next;
			return first.item;
	}finally{
	    lock.unlock();
	}
    }
    public void print(){
	try{
	    lock.lock();
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
			
	}finally{lock.unlock();}
    }
    @Override
    public void run(){
	String threadName = Thread.currentThread().getName(); //Obtenemos el nombre del hilo
		
	this.head = new Nodo("hnull");
		this.tail = new Nodo("tnull");
		this.head.next = this.tail;
    }
}
