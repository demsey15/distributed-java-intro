package com.uam.exercise1;

import com.uam.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.uam.Application;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BookStoreFirstIntegrationTest {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void bookStoreShouldNotBeEmpty() {
        RestTemplate template = new RestTemplate();
        List<Book> list = template.getForObject("http://bsodzik.herokuapp.com/books", List.class);
        Assertions.assertThat(list).isNotEmpty();
	}
//mó test:
    @Test
    public void check(){
        int i = 5;
        Assertions.assertThat(i).isEqualTo(5);
    }
}
