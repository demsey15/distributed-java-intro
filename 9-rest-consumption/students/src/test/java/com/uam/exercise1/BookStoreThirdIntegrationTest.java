package com.uam.exercise1;

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
public class BookStoreThirdIntegrationTest {

    @Autowired
    private RestTemplate restTemplate;

    private String uniqueIsbn;
    private Book testBook;

    @Before
    public void setUp() {
        uniqueIsbn = Long.toString(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE / 100, Long.MAX_VALUE));
        testBook = new Book(uniqueIsbn, "Baduuuuuum! Tsss...", "O tym, że grać można", "Janko Muzykant");
    }

    @Test
    public void bookShouldNotBeAddedTwice() {
        try {
            restTemplate.getForObject(Book.URL + testBook.getIsbn(), Object.class);
        } catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
        try {
            restTemplate.postForObject(Book.URL, testBook, Book.class);
        } catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
        try {
            restTemplate.postForObject(Book.URL, testBook, Book.class);
        } catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        }
        try {
            restTemplate.delete(Book.URL + testBook.getIsbn());
        } catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
    }
}
