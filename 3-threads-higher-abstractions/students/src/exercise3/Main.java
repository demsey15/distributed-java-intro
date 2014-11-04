package exercise3;

import common.html.GazetaHtmlDocument;
import common.html.HtmlDocument;
import common.html.PudelekHtmlDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws Exception {
      //  HtmlDocument rootDocument = new GazetaHtmlDocument("http://wiadomosci.gazeta.pl/");
        HtmlDocument rootDocument = new PudelekHtmlDocument("http://www.pudelek.pl/");
        Set<String> links = rootDocument.getLinks();
        String wordToFound = "stenka";
        
        long start = System.currentTimeMillis();
        // TODO: Create ExecutorService
        
      //  ExecutorService executors = Executors.newCachedThreadPool(); //Czas wykonania zadania: 6 s
      //  ExecutorService executors = Executors.newFixedThreadPool(5);  //Czas wykonania zadania: 9 s
      //  ExecutorService executors = Executors.newFixedThreadPool(100); //Czas wykonania zadania: 7 s
        ExecutorService executors = Executors.newFixedThreadPool(100);  //Czas wykonania zadania: 6 s
        //   ExecutorService executors = Executors.newSingleThreadExecutor(); //Czas wykonania zadania: 156 s

        		
        // TODO: Create list of results of type List<Future<Integer>>
        
        List<Future<Integer>> list = new ArrayList<Future<Integer>>();

        for (String link : links) {
            // TODO: Create new WordCounter and submit it to executorService
        	WordCounter counter = new WordCounter(link, wordToFound);
            // TODO: Store Future object in list of results
        	list.add(executors.submit(counter));
        }

        // TODO: shutdown executor
        executors.shutdown();

        int numberOfWords = 0;
        // TODO: Iterate over list of results and for each Future invoke get() method
        for(Future<Integer> no : list){
        	numberOfWords += no.get();
        }
        // TODO: add value returned from get() method to numberOfWords variable

        System.out.printf("Number of words '%s': %d\n", wordToFound, numberOfWords);
        System.out.println("Czas wykonania zadania: " + (System.currentTimeMillis() - start)/1000 + " s");
    }
}
