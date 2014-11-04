package exercise3;

import java.util.concurrent.Callable;

import common.StringUtils;
import common.html.GazetaHtmlDocument;
import common.html.HtmlDocument;
import common.html.PudelekHtmlDocument;

public class WordCounter implements Callable<Integer>{

    private final String documentUrl;
    private final String wordToCount;

    public WordCounter(String documentUrl, String wordToCount) {
        this.documentUrl = documentUrl;
        this.wordToCount = wordToCount;
    }
    
    public Integer call() throws Exception {
    	//HtmlDocument doc = new GazetaHtmlDocument(documentUrl);
    	HtmlDocument doc = new PudelekHtmlDocument(documentUrl);
    	String content = doc.getContent().toLowerCase();
    	return StringUtils.countOccurrences(content, wordToCount);
    }
}
