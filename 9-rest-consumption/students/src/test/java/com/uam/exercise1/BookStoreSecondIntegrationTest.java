package com.uam.exercise1;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.uam.Application;
import com.uam.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BookStoreSecondIntegrationTest {

	@Autowired
	private RestTemplate restTemplate;

	//private String uniqueIsbn;
	private Book testBook;

	@Before
	public void setUp() {
		//uniqueIsbn = Long.toString(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE / 100, Long.MAX_VALUE));
		testBook = new Book("375554444", "Olaolaola!", "BadzOla!", "OlakowskaOla", "Kuba");
	}


    public void bookShouldntBeInBookStore(){
        try {
            restTemplate.getForObject("http://bsodzik.herokuapp.com/books/" + testBook.getIsbn(), Object.class);
        }
        catch(HttpClientErrorException errorException){
            Assertions.assertThat(errorException.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }


    public void ReturnedBookIsEqualToSentBook(){
        Book b = restTemplate.postForObject("http://bsodzik.herokuapp.com/books", testBook, Book.class);
        Assertions.assertThat(b).isEqualTo(testBook);
    }


    public void bookShouldBeInBookStore(){
        try {
            restTemplate.getForObject("http://bsodzik.herokuapp.com/books/" + testBook.getIsbn(), Object.class);
        }
        catch(HttpClientErrorException errorException){
            Assertions.assertThat(errorException.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
    }

	@Test
	public void bookShouldBeCreatedAndDeleted() {
        bookShouldntBeInBookStore();
        ReturnedBookIsEqualToSentBook();
        bookShouldBeInBookStore();
        restTemplate.delete("http://bsodzik.herokuapp.com/books/" + testBook.getIsbn());
        try {
            restTemplate.getForObject("http://bsodzik.herokuapp.com/books/" + testBook.getIsbn(), Object.class);
        }
        catch(HttpClientErrorException errorException){
            Assertions.assertThat(errorException.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
	}
}
