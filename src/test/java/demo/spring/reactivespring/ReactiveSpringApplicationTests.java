package demo.spring.reactivespring;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

import demo.spring.reactivespring.model.Tweet;
import demo.spring.reactivespring.repository.TweetRepository;
import java.util.Collections;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	properties="spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration, "
		+ "org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration")
//https://springframework.guru/spring-data-mongodb-with-reactive-mongodb/
public class ReactiveSpringApplicationTests {

	@Autowired
	ApplicationContext context;

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	TweetRepository tweetRepository;

	@Before
	public void setup() {
		this.webTestClient = WebTestClient
			.bindToApplicationContext(this.context)
			// add Spring Security test Support
			.apply(springSecurity())
			.configureClient()
			.filter(basicAuthentication())
			.build();
	}

	@Test
	@WithMockUser
	public void testCreateTweet() {
		//Given
		Tweet tweet = new Tweet("This is a Test Tweet");
		//When
		webTestClient.mutateWith(csrf()).post().uri("/tweets")
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.body(Mono.just(tweet), Tweet.class)
			.exchange()
		//Then
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
			.expectBody()
			.jsonPath("$.id").isNotEmpty()
			.jsonPath("$.text").isEqualTo("This is a Test Tweet");
	}

	@Test
	@WithMockUser
	public void testGetAllTweets() {
		//Given
		//When
		webTestClient.get().uri("/tweets")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
		//Then
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
			.expectBodyList(Tweet.class);
	}

	@Test
	@WithMockUser
	public void testGetTrendingTweets() {
		//Given
		tweetRepository.save(new Tweet("Where is the Hokage ?"));
		//When
		webTestClient.get().uri("/tweets/trending")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
		//Then
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
			.expectBodyList(Tweet.class);
	}

	@Test
	@WithMockUser
	public void testGetSingleTweet() {
		//Given
		Tweet tweet = tweetRepository.save(new Tweet("Hello, World!")).block();
		//When
		webTestClient.get()
			.uri("/tweets/{id}", Collections.singletonMap("id", tweet.getId()))
			.exchange()
		//Then
			.expectStatus().isOk()
			.expectBody()
			.consumeWith(response ->
				Assertions.assertThat(response.getResponseBody()).isNotNull());
	}

	@Test
	@WithMockUser
	public void testUpdateTweet() {
		//Given
		Tweet tweet = tweetRepository.save(new Tweet("Initial Tweet")).block();
		Tweet newTweetData = new Tweet("Updated Tweet");
		//When
		webTestClient.mutateWith(csrf()).put()
			.uri("/tweets/{id}", Collections.singletonMap("id", tweet.getId()))
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.body(Mono.just(newTweetData), Tweet.class)
			.exchange()
		//Then
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
			.expectBody()
			.jsonPath("$.text").isEqualTo("Updated Tweet");
	}

	@Test
	@WithMockUser
	public void testDeleteTweet() {
		//Given
		Tweet tweet = tweetRepository.save(new Tweet("To be deleted")).block();
		//When
		webTestClient.mutateWith(csrf()).delete()
			.uri("/tweets/{id}", Collections.singletonMap("id",  tweet.getId()))
			.exchange()
		//Then
			.expectStatus().isOk();
	}
}