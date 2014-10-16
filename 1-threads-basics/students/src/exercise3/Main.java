package exercise3;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
    	
    	Thread[] threads = new Thread[4];
    	
    	for(int i = 0; i < 4; i++){
    		threads[i] = new Thread(new MyRunnable(), "Thread-" + i);
    	}
    	/* Drukowana jest ca³y czas nazwa w¹tku jako main - w rzeczywistoœci nowy w¹tek nie jest tworzony
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
    	//Uruchomiono ju¿ wszystkie w¹tki, teraz czekaj, a¿ wszystkie siê zakoñcz¹
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
