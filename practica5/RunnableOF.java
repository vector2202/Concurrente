import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RunnableOF<T> implements Runnable{
    private int item;
    ConcurrentLinkedQueue<Integer> queue;
    SimpleSnapshot<String> snapshotI;
    SimpleSnapshot<String> snapshotR;
    private int num;
    public RunnableOF(int item, ConcurrentLinkedQueue<Integer> queue, 
    		int num, SimpleSnapshot<String> snapshotI, SimpleSnapshot<String> snapshotR){
        this.item = item;
        this.queue = queue;
        this.snapshotI = snapshotI;
        this.snapshotR = snapshotR;
        this.num = num;
    }
    
    @Override
    public void run(){
        try{
            if (this.num == 0) {//Probabilidad de 50/100 de ejecutar enq
            	String string = String.format("enq( %s )", this.item);
            	snapshotI.update(string);//En el ssI se guardan las invocaciones 
            	
            	Boolean res = this.queue.add(this.item);
            	
            	Object[] result  = (T[]) snapshotI.scan();
            	Stream<Object> stream = Arrays.stream(result); 
            	String scan = stream.map( n -> n.toString() )
                .collect( Collectors.joining( " + " ) );
            	snapshotR.update(scan + String.format(" |  %s || ", res));// En el ssR se guardan las respuestas

            }
            if (this.num == 1) {//Probabilidad de 50/100 de ejecutar deq
            	snapshotI.update("deq()");
            	T res = (T) this.queue.poll();
            	
            	Object[] result  = (T[]) snapshotI.scan();
            	Stream<Object> stream = Arrays.stream(result); 
            	String scan = stream.map( n -> n.toString() )
                .collect( Collectors.joining( " + " ) );
            	snapshotR.update(scan + String.format(" |  %s || ", res));
            }
            

        }
        catch(Exception e){
        }
    }
}
