package demo.spring.reactivespring.repository;

import demo.spring.reactivespring.model.TrendingWord;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TrendingWordRepository extends ReactiveMongoRepository<TrendingWord, String> {

}
