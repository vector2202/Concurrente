import java.util.Arrays;

public class PetersonLock4 {
    private volatile boolean[] flag = new boolean[4]; 
    private volatile int victim; 

    public PetersonLock4() {
        // Inicializa las banderas en false (ningún hilo quiere entrar a la sección crítica)
        Arrays.fill(flag, false);
        victim = -1; // Inicialmente no hay prioridad
    }

    // Método para adquirir el candado
    public void lock(int threadId) {
        flag[threadId] = true; // El hilo indica que quiere entrar a la sección crítica
        victim = threadId; // El hilo actual se convierte en "víctima"
        
        // Espera mientras otro hilo tenga prioridad y también quiera entrar
        for (int i = 0; i < 4; i++) {
            if (i != threadId) {
                while (flag[i] && victim == threadId) {
                    // Esperanding
                }
            }
        }
    }

    // Método para liberar el candado
    public void unlock(int threadId) {
        flag[threadId] = false; 
    }
}
