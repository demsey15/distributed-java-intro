package exercise1.equipment;

import java.util.concurrent.locks.*;

public class Brushes {
    private int available = 3;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void takeBrush() throws InterruptedException {
       lock.lock();
       while(available <= 0){
       	condition.await();
       }
        try{
    	if (available == 0) {
            throw new IllegalStateException("There are no more brushes!");
        }
        available -= 1;
        }
        finally{
        	lock.unlock();
        }
    }

    public void returnBrush() {
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
