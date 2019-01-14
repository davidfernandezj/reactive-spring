package demo.spring.reactivespring.config;

import demo.spring.reactivespring.repository.TweetRepository;
import demo.spring.reactivespring.testimplementations.TestTweetRepository;
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

    /* Comment this to make a proper test
    @Bean
    @Primary
    public SecurityWebFilterChain disableSpringSecurityFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
            .anyExchange().permitAll().and()
            .csrf().disable()
            .httpBasic().disable().build();
    }
     */
}
