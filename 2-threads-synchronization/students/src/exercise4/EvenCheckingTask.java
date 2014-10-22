package exercise4;

import java.util.concurrent.locks.ReentrantLock;

import common.Counter;

public class EvenCheckingTask implements Runnable {

    private final  Counter counter;
    private final int numberOfIterations;
    private final static ReentrantLock lock = new ReentrantLock();

    public EvenCheckingTask(Counter counter, int numberOfIterations) {
        this.counter = counter;
        this.numberOfIterations = numberOfIterations;
    }

   
    public void run() {
        for (int i = 0; i < numberOfIterations; ++i) {
        	//synchronized(counter) {counter.increment();		//sekcja krytyczna - blokuje obiekt counter - mo¿e byæ jednoczeœnie wykorzystywany tylko przez jeden w¹tek
        	lock.lock();
        	try{
        	counter.increment();
            if (counter.getValue() % 2 != 0) {
                System.out.println("Value is not even!");
                break;
            //	}
            	}
        	}
            finally{
            	lock.unlock();
            }
        }
       }
}

