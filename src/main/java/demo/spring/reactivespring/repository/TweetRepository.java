package demo.spring.reactivespring.repository;

import demo.spring.reactivespring.model.Tweet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TweetRepository extends ReactiveMongoRepository<Tweet, String> {

}
