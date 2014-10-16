package exercise4;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class Main {

    public static void main(String[] args) {
    	ExecutorService executor = Executors.newFixedThreadPool(4); //pula w�tk�w z ustalon� liczb� w�tk�w na 4
    	for(int i = 0; i < 4; i++){
    		executor.execute(new exercise4.MyRunnable() );
    	}
    	executor.shutdown();	//do puli nie b�dzie ju� dodanych wi�cej w�tk�w - bie��ce zostan� zako�czone, a pula zamkni�ta
    	//podej�cie podobne do podej�cia Scheduled invocation z �w. 3 
    	try {
			executor.awaitTermination(100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("FINISHED");
    	
    	//System.out.println("FINISHED");	//drukowane w trakcie dzia�ania w�tk�w
    }
    
    
}
