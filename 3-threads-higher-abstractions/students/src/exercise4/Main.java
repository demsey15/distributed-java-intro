package exercise4;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        String rootUrl = "http://wiadomosci.gazeta.pl/";
        String wordToFound = "kopacz";
        Integer numberOfWords = 0;

        // TODO: Create new ForkJoinPool object
        ForkJoinPool pool = new ForkJoinPool();
        // TODO: Create new WebCrawlingTask for rootUrl and wordToFound
        WebCrawlingTask webTask = new WebCrawlingTask(rootUrl, wordToFound);
        // TODO: Invoke invoke method on ForkJoinPool object passing WebCrawlingTask
        numberOfWords = pool.invoke(webTask);
        // TODO: Assign result of invoke method to numberOfWords variable
        

        System.out.printf("Number of words '%s': %d", wordToFound, numberOfWords);
    }
}
