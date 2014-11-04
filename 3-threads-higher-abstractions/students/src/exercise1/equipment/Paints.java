package exercise1.equipment;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Paints {
    private int available = 3;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();


    public void takePaint() throws InterruptedException {
    	lock.lock();
    	while(available <= 0){
        	condition.await();
        }
        try{
    	if (available == 0) {
            throw new IllegalStateException("There are no more paints!");
        }
        available -= 1;
        }
        finally{
        	lock.unlock();
        }
    }

    public void returnPaint() {
    	lock.lock();
    	try{
    		available += 1;
    	}
    	finally{
    		condition.signal();
    		lock.unlock();
    		
    	}
       }
 }

