import java.util.concurrent.locks.*;

public class ColaConcurrenteLock {
    public static void main(String[] args) {

        // Crear una instancia de ColaLock
        ColaLock runnable = new ColaLock();

        // Crear y ejecutar los hilos
        Thread threadA = new Thread(runnable);
        Thread threadB = new Thread(runnable);
        Thread threadC = new Thread(runnable);
        threadA.start();
        threadB.start();
        threadC.start();

        try {
            Thread.sleep(500); // Pausa para permitir que los hilos trabajen
            threadA.join();
            threadB.join();
            threadC.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        // Operaciones de encolar y desencolar
        runnable.deq(); // Intentar desencolar (posible cola vacía)
        runnable.enq("x"); // Encolar "x"
        runnable.enq("a"); // Encolar "a"
        System.out.println(runnable.deq()); // Desencolar (debe desencolar "x")
        runnable.print(); // Imprimir estado de la cola
    }
}

class ColaLock implements Runnable {
    private Nodo head;
    private Nodo tail;
    private Lock lock = new ReentrantLock();

    // Constructor para inicializar la cola
    public ColaLock() {
        this.head = new Nodo("hnull");
        this.tail = new Nodo("tnull");
        this.head.next = this.tail; // Cola vacía
    }

    // Método para encolar un elemento
    public Boolean enq(String x) {
        try {
            lock.lock(); // Bloqueo antes de modificar la cola
            Nodo new_node = new Nodo(x);
            if (this.head.next == this.tail) {
                new_node.next = this.tail;
                this.head.next = new_node;
            } else {
                Nodo last = this.tail.next;
                new_node.next = this.tail;
                last.next = new_node;
            }
            this.tail.next = new_node;
            System.out.println("Enqueued: " + x);
            return true;
        } finally {
            lock.unlock(); // Desbloqueo después de modificar la cola
        }
    }

    // Método para desencolar un elemento
    public String deq() {
        try {
            lock.lock(); // Bloqueo antes de modificar la cola
            if (this.head.next == this.tail) {
                System.out.println("Queue is empty");
                return "empty";
            }
            Nodo first = this.head.next;
            this.head.next = first.next;
            System.out.println("Dequeued: " + first.item);
            return first.item;
        } finally {
            lock.unlock(); // Desbloqueo después de modificar la cola
        }
    }

    // Método para imprimir los elementos de la cola
    public void print() {
        try {
            lock.lock(); // Bloqueo antes de acceder a la cola
            System.out.println("Printing queue");
            Nodo curr = this.head.next;
            while (curr != null && curr.item != "tnull") {
                System.out.println(curr.item);
                curr = curr.next;
            }
        } finally {
            lock.unlock(); 
        }
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Thread " + threadName + " is running.");
    }
}
