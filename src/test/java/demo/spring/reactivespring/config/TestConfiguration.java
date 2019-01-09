package demo.spring.reactivespring.config;

import demo.spring.reactivespring.testimplementations.TestTweetRepository;
import demo.spring.reactivespring.repository.TweetRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class TestConfiguration {

    @Bean
    @Primary
    public TweetRepository testTweetRepository() {
        return new TestTweetRepository();
    }

    /* Comment this to make a proper test */
    @Bean
    @Primary
    public SecurityWebFilterChain disableSpringSecurityFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
            .anyExchange().permitAll().and()
            .csrf().disable()
            .httpBasic().disable().build();
    }
}
