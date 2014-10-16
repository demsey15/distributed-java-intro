package exercise2;

public class MyThread extends Thread{
	
	MyThread(String threadName){
		super(threadName);
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			System.out.println(i + ") " + this.getName());
			try {
				Thread.sleep(10);			//po dodaniu sleep w¹tki czêœciej siê wymieniaj¹
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
