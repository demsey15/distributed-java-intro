package exercise2;

import java.util.concurrent.locks.ReentrantLock;

import common.Counter;

public class LockingCounter implements Counter {

	private long field = 0;
	private final ReentrantLock lock = new ReentrantLock();
	
	public void increment() {
			lock.lock();
			try{
				field++;
			}
			finally{
				lock.unlock();
			}
		}

	    public long getValue() {
	        return field;
	    }
    }

