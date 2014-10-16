package exercise4;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class Main {

    public static void main(String[] args) {
    	ExecutorService executor = Executors.newFixedThreadPool(4); //pula w¹tków z ustalon¹ liczb¹ w¹tków na 4
    	for(int i = 0; i < 4; i++){
    		executor.execute(new exercise4.MyRunnable() );
    	}
    	executor.shutdown();	//do puli nie bêdzie ju¿ dodanych wiêcej w¹tków - bie¿¹ce zostan¹ zakoñczone, a pula zamkniêta
    	//podejœcie podobne do podejœcia Scheduled invocation z æw. 3 
    	try {
			executor.awaitTermination(100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("FINISHED");
    	
    	//System.out.println("FINISHED");	//drukowane w trakcie dzia³ania w¹tków
    }
    
    
}
