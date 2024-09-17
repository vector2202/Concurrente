import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TestPetersonLock4 {
    private static AtomicInteger contador = new AtomicInteger(0); // Contador compartido entre hilos
    private static PetersonLock4 lock = new PetersonLock4(); // Instancia del candado
    
    // Contadores individuales para cada hilo
    private static AtomicInteger[] contadoresHilo = new AtomicInteger[4];

    public static void main(String[] args) {
        // Inicializamos los contadores individuales de cada hilo
        for (int i = 0; i < 4; i++) {
            contadoresHilo[i] = new AtomicInteger(0);
        }

        ExecutorService executor = Executors.newFixedThreadPool(4); 

        for (int i = 0; i < 400; i++) {
            int threadId = i % 4; // Asigna un id de hilo entre 0 y 3
            executor.submit(() -> {
                lock.lock(threadId); // El hilo adquiere el candado
                try {
                    // Incrementa el contador global y el contador específico del hilo
                    int nuevoValor = contador.incrementAndGet();
                    int contadorHilo = contadoresHilo[threadId].incrementAndGet();
                    
                    System.out.println("Hilo " + threadId + " incrementa el contador. " +
                                       "Contador global: " + nuevoValor + 
                                       ", Contador del hilo " + threadId + ": " + contadorHilo);
                } finally {
                    lock.unlock(threadId); 
                }
            });
        }

        executor.shutdown(); 
        while (!executor.isTerminated()) {
            // Espera a que todos los hilos terminen
        }

        // Imprime el valor final de los contadores
        System.out.println("\n=== Resultados Finales ===");
        System.out.println("Contador global final: " + contador.get());
        for (int i = 0; i < 4; i++) {
            System.out.println("Contador final del hilo " + i + ": " + contadoresHilo[i].get());
        }
    }
}
