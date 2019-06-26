package com.zentity.demo.jsonplaceholder;

import com.google.common.net.HttpHeaders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void testOk() {
		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.path("/user/{userId}").build("1"))
			.exchange()
			.expectHeader().exists(HttpHeaders.ETAG)
			.expectStatus().isOk()
			.expectBody().json("{\"id\":1,\"username\":\"Bret\",\"name\":\"Leanne Graham\",\"email\":\"Sincere@april.biz\",\"todos\":[{\"title\":\"delectus aut autem\",\"completed\":false},{\"title\":\"quis ut nam facilis et officia qui\",\"completed\":false},{\"title\":\"fugiat veniam minus\",\"completed\":false},{\"title\":\"et porro tempora\",\"completed\":true},{\"title\":\"laboriosam mollitia et enim quasi adipisci quia provident illum\",\"completed\":false},{\"title\":\"qui ullam ratione quibusdam voluptatem quia omnis\",\"completed\":false},{\"title\":\"illo expedita consequatur quia in\",\"completed\":false},{\"title\":\"quo adipisci enim quam ut ab\",\"completed\":true},{\"title\":\"molestiae perspiciatis ipsa\",\"completed\":false},{\"title\":\"illo est ratione doloremque quia maiores aut\",\"completed\":true},{\"title\":\"vero rerum temporibus dolor\",\"completed\":true},{\"title\":\"ipsa repellendus fugit nisi\",\"completed\":true},{\"title\":\"et doloremque nulla\",\"completed\":false},{\"title\":\"repellendus sunt dolores architecto voluptatum\",\"completed\":true},{\"title\":\"ab voluptatum amet voluptas\",\"completed\":true},{\"title\":\"accusamus eos facilis sint et aut voluptatem\",\"completed\":true},{\"title\":\"quo laboriosam deleniti aut qui\",\"completed\":true},{\"title\":\"dolorum est consequatur ea mollitia in culpa\",\"completed\":false},{\"title\":\"molestiae ipsa aut voluptatibus pariatur dolor nihil\",\"completed\":true},{\"title\":\"ullam nobis libero sapiente ad optio sint\",\"completed\":true}]}")
			;
	}

	@Test
	public void testNotModified() {
		EntityExchangeResult<byte[]> result = webTestClient.get()
			.uri(uriBuilder -> uriBuilder.path("/user/{userId}").build("1"))
			.exchange()
			.expectStatus().isOk()
			.expectBody().json("{\"id\":1,\"username\":\"Bret\",\"name\":\"Leanne Graham\",\"email\":\"Sincere@april.biz\",\"todos\":[{\"title\":\"delectus aut autem\",\"completed\":false},{\"title\":\"quis ut nam facilis et officia qui\",\"completed\":false},{\"title\":\"fugiat veniam minus\",\"completed\":false},{\"title\":\"et porro tempora\",\"completed\":true},{\"title\":\"laboriosam mollitia et enim quasi adipisci quia provident illum\",\"completed\":false},{\"title\":\"qui ullam ratione quibusdam voluptatem quia omnis\",\"completed\":false},{\"title\":\"illo expedita consequatur quia in\",\"completed\":false},{\"title\":\"quo adipisci enim quam ut ab\",\"completed\":true},{\"title\":\"molestiae perspiciatis ipsa\",\"completed\":false},{\"title\":\"illo est ratione doloremque quia maiores aut\",\"completed\":true},{\"title\":\"vero rerum temporibus dolor\",\"completed\":true},{\"title\":\"ipsa repellendus fugit nisi\",\"completed\":true},{\"title\":\"et doloremque nulla\",\"completed\":false},{\"title\":\"repellendus sunt dolores architecto voluptatum\",\"completed\":true},{\"title\":\"ab voluptatum amet voluptas\",\"completed\":true},{\"title\":\"accusamus eos facilis sint et aut voluptatem\",\"completed\":true},{\"title\":\"quo laboriosam deleniti aut qui\",\"completed\":true},{\"title\":\"dolorum est consequatur ea mollitia in culpa\",\"completed\":false},{\"title\":\"molestiae ipsa aut voluptatibus pariatur dolor nihil\",\"completed\":true},{\"title\":\"ullam nobis libero sapiente ad optio sint\",\"completed\":true}]}")
			.returnResult();

		String etag = result.getResponseHeaders().getFirst(HttpHeaders.ETAG);
		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.path("/user/{userId}").build("1"))
			.ifNoneMatch(etag)
			.exchange()
			.expectStatus().isNotModified();
	}

	@Test
	public void testUnknownUser() {
		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.path("/user/{userId}").build("1234"))
			.exchange()
			.expectStatus().isNotFound();
	}

	@Test
	public void testValidation() {
		webTestClient.get()
			.uri(uriBuilder -> uriBuilder.path("/user/{userId}").build("-1"))
			.exchange()
			.expectStatus().isBadRequest()
		;
	}

}
