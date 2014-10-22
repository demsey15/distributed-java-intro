package exercise5;

//s³owo kluczowe volatile jest bardzo rzadko u¿ywane
public class Main {

    public static void main(String[] args) throws InterruptedException {
        VolatileTask task = new VolatileTask();
        Thread thread = new Thread(task);
        thread.start();
        Thread.sleep(100);
        task.end();		//end zmienia wartoœæ isRunning, ale proces obs³uguj¹cy thread ma ju¿ wartoœæ isRunn  pamiêci podrêcznej jako true
        				//s³owo volatile w deklaracji zmiennej isRunning zabrania przechowywania wartoœci zmiennych w pamiêci podrêcznej procesora
    }
}
