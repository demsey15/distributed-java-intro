package exercise5;

//s�owo kluczowe volatile jest bardzo rzadko u�ywane
public class Main {

    public static void main(String[] args) throws InterruptedException {
        VolatileTask task = new VolatileTask();
        Thread thread = new Thread(task);
        thread.start();
        Thread.sleep(100);
        task.end();		//end zmienia warto�� isRunning, ale proces obs�uguj�cy thread ma ju� warto�� isRunn  pami�ci podr�cznej jako true
        				//s�owo volatile w deklaracji zmiennej isRunning zabrania przechowywania warto�ci zmiennych w pami�ci podr�cznej procesora
    }
}
