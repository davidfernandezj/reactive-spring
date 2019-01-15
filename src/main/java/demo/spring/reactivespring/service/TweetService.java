package demo.spring.reactivespring.service;

import demo.spring.reactivespring.model.TrendingWord;
import demo.spring.reactivespring.model.Tweet;
import demo.spring.reactivespring.repository.TrendingWordRepository;
import demo.spring.reactivespring.repository.TweetRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final TrendingWordRepository trendingWordRepository;

    @Autowired
    public TweetService(TweetRepository tweetRepository, TrendingWordRepository trendingWordRepository) {
        this.tweetRepository = tweetRepository;
        this.trendingWordRepository = trendingWordRepository;
    }

    public Flux<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    public Flux<Tweet> findAllTrendingTweets() {
        Flux<Tweet> tweetFlux = findAll().collectList().flatMap(tweets -> getTrendingWords().collectList()
            .map(words ->
                tweets.stream()
                    .filter(tweet -> isTrendingTweet(tweet, words))
                    .collect(Collectors.toList())))
            .flatMapMany(list -> Flux.fromStream(list.stream()));

        return tweetFlux;
    }

    private boolean isTrendingTweet(Tweet tweet, List<TrendingWord> words) {
        final String whiteSpaceRegExp = "\\s+";

        final Set<String> trendingWords = words.stream().map(TrendingWord::getText).collect(Collectors.toSet());
        String[] tweetWords = tweet.getText().split(whiteSpaceRegExp);
        return Stream.of(tweetWords).anyMatch(trendingWords::contains);

    }

    private Flux<TrendingWord> getTrendingWords() {
        return trendingWordRepository.findAll();
    }

    public Mono<Tweet> findById(String tweetId) {
        return tweetRepository.findById(tweetId);
    }

    public Mono<Tweet> save(Tweet newTweet) {
        return tweetRepository.save(newTweet);
    }

    public Mono<Void> delete(Tweet existingTweet) {
        return tweetRepository.delete(existingTweet);
    }


}
