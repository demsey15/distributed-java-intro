package com.uam.exercise2;

import com.uam.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.uam.Application;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BookStoreExchangeIntegrationTest {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void shouldReturnListOfBooksAsJsonString() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(Book.URL, HttpMethod.GET, entity, String.class);
        System.out.println(responseEntity.getStatusCode() + " " + responseEntity.getHeaders() + " " + responseEntity.getBody());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void shouldReturnListOfBooksAsJsonObject() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity(httpHeaders);
        ParameterizedTypeReference<List<Book>> responseType = new ParameterizedTypeReference<List<Book>>() {};
        ResponseEntity<List<Book>> responseEntity = restTemplate.exchange(Book.URL, HttpMethod.GET, entity, responseType);
        System.out.println("Code: " + responseEntity.getStatusCode());
        System.out.println("Headers: " + responseEntity.getHeaders());
        System.out.println("Body: " + responseEntity.getBody());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void shouldReturnListOfBooksAsXmlString() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/xml");
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(Book.URL, HttpMethod.GET, entity, String.class);
        System.out.println("Code: " + responseEntity.getStatusCode());
        System.out.println("Headers: " + responseEntity.getHeaders());
        System.out.println("Body: " + responseEntity.getBody());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void shouldReturnListOfBooksAsXmlObject() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/xml");
        HttpEntity entity = new HttpEntity(httpHeaders);
        ParameterizedTypeReference<List<Book>> responseType = new ParameterizedTypeReference<List<Book>>() {};
        ResponseEntity<List<Book>> responseEntity = restTemplate.exchange(Book.URL, HttpMethod.GET, entity, responseType);
        System.out.println("Code: " + responseEntity.getStatusCode());
        System.out.println("Headers: " + responseEntity.getHeaders());
        System.out.println("Body: " + responseEntity.getBody());
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}

