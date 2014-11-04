package exercise4;

import common.html.GazetaHtmlDocument;
import common.html.HtmlDocument;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class WebCrawlingTask extends RecursiveTask<Integer> {

    private static final Set<String> visitedLinks = new HashSet<String>();

    private final String documentUrl;
    private final String wordToCount;

    public WebCrawlingTask(String documentUrl, String wordToCount) {
        this.documentUrl = documentUrl;
        this.wordToCount = wordToCount;
    }

    @Override
    protected Integer compute() {
        System.out.printf("Looking for words '%s' in article %s\n", wordToCount, documentUrl);
        HtmlDocument document = new GazetaHtmlDocument(documentUrl);

        // TODO: Create list of forks of type List<RecursiveTask<Integer>>
        List<RecursiveTask<Integer>> forks = new ArrayList<RecursiveTask<Integer>>();
        // TODO: Create new WordCountingTask for given document
        WordCountingTask countingTask = new WordCountingTask(documentUrl, wordToCount);
        // TODO: Add created WordCountingTask to list of forks
        forks.add(countingTask);
        // TODO: Invoke method fork() on created WordCountingTask
        countingTask.fork();

        for (String link : document.getLinks()) {
            // Avoid visiting the same links and limit number of visited links
            boolean visited = true;
            synchronized (visitedLinks) {
                if (visitedLinks.size() > 50) {
                    break;
                }
                if (!visitedLinks.contains(link)) {
                    visited = false;
                    visitedLinks.add(link);
                }
            }

            if (!visited) {
                // TODO: If given link was not yet visited, go for it!
                // TODO: Create new WebCrawlingTask for given link
            	WebCrawlingTask crawlingTask = new WebCrawlingTask(link, wordToCount);
                // TODO: Add created WebCrawlingTask to list of forks
            	forks.add(crawlingTask);
                // TODO: Invoke method fork() on created WebCrawlingTask
            	crawlingTask.fork();
            }
        }

        int result = 0;
        // TODO: Iterate over list of forks and for each invoke join() method
        for(RecursiveTask<Integer> t : forks){
        	result += t.join();
        }
        // TODO: add value returned from join() method to result variable
        return result;
    }
}
