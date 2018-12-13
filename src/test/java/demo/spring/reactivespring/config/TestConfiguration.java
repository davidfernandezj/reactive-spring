package demo.spring.reactivespring.config;

import demo.spring.reactivespring.testimplementations.TestTweetRepository;
import demo.spring.reactivespring.repository.TweetRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestConfiguration {

    @Bean
    @Primary
    public TweetRepository testTweetRepository() {
        return new TestTweetRepository();
    }
}
