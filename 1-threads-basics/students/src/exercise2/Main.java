package exercise2;

public class Main {

    public static void main(String[] args) {
    	
    	Thread[] threads = new Thread[4];
    	
    	for(int i = 0; i < 4; i++){
    		threads[i] = new MyThread("Thread-" + i);
    	}
    	
    	/*  Bezpoœrednie wywo³anie metody run nie tworzy nowego w¹tku, metoda run wykonywana jest w w¹tku bie¿¹cym
    	for(Thread t : threads){
    		t.run();
    	}
    	*/
    	
    	for(Thread t : threads){
    		t.start();
    	}
    }
}
