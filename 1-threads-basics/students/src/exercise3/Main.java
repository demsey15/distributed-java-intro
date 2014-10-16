package exercise3;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
    	
    	Thread[] threads = new Thread[4];
    	
    	for(int i = 0; i < 4; i++){
    		threads[i] = new Thread(new MyRunnable(), "Thread-" + i);
    	}
    	/* Drukowana jest ca�y czas nazwa w�tku jako main - w rzeczywisto�ci nowy w�tek nie jest tworzony
    	for(Thread t : threads){
    		t.run();
    	}
    	*/
    	
    	for(Thread t : threads){
    		t.start();
    		/*try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
    	}
    	//Uruchomiono ju� wszystkie w�tki, teraz czekaj, a� wszystkie si� zako�cz�
    	for(Thread t : threads){
    		try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	System.out.println("FINISHED");
    	
    	/*try {
			//Thread.sleep(5000);
    		TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("FINISHED");
		*/
    	
    	//Continuous polling
    	/*
    	while(true){
    		
    		boolean alive = false;
    		for(Thread t : threads){
    			if(t.isAlive()) alive = true;
    		}
    		if(! alive){
    			System.out.println("FINISHED");
    			break;
    		}
    		
    		try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	*/
    	
    }
}
