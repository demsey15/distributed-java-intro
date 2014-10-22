package exercise1;

import java.util.concurrent.locks.*;

import common.Counter;

public class SynchronizedCounter implements Counter {

	private long field = 0;
	
	
    public synchronized void increment() {
		field++;
	}

    public long getValue() {
        return field;
    }
}
