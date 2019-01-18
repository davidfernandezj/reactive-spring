package demo.spring.reactivespring.service;

import com.googlecode.jmapper.JMapper;
import demo.spring.reactivespring.dto.TweetDto;
import demo.spring.reactivespring.model.TrendingWord;
import demo.spring.reactivespring.model.Tweet;
import demo.spring.reactivespring.repository.TrendingWordRepository;
import demo.spring.reactivespring.repository.TweetRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final TrendingWordRepository trendingWordRepository;
    private final JMapper<TweetDto, Tweet> tweetMapper;

    @Autowired
    public TweetService(TweetRepository tweetRepository, TrendingWordRepository trendingWordRepository,
        JMapper<TweetDto, Tweet> tweetMapper) {
        this.tweetRepository = tweetRepository;
        this.trendingWordRepository = trendingWordRepository;
        this.tweetMapper = tweetMapper;
    }

    public Flux<TweetDto> findAll() {
        return tweetRepository.findAll().map(tweetMapper::getDestination);
    }

    public Mono<TweetDto> updateTweet(String tweetId, Tweet tweet) {
        return tweetRepository.findById(tweetId)
            .flatMap(existingTweet -> {
                existingTweet.setText(tweet.getText());
                return tweetRepository.save(existingTweet);
            }).map(tweetMapper::getDestination);
    }

    public Mono<Void> deleteTweet(String tweetId) {
        return tweetRepository.findById(tweetId)
            .flatMap(tweetRepository::delete);
    }

    public Flux<TweetDto> findAllTrendingTweets() {
        //https://spring.io/blog/2016/06/13/notes-on-reactive-programming-part-ii-writing-some-code
        return tweetRepository.findAll().collectList().flatMap(tweets -> getTrendingWords().collectList()
            .map(words ->
                tweets.stream()
                    .filter(tweet -> isTrendingTweet(tweet, words))
                    .collect(Collectors.toList())))
            .flatMapMany(list -> Flux.fromStream(list.stream()))
            .map(tweetMapper::getDestination);
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

    public Mono<TweetDto> findById(String tweetId) {
        return tweetRepository.findById(tweetId)
            .map(tweetMapper::getDestination);
    }

    public Mono<TweetDto> save(Tweet newTweet) {
        return tweetRepository.save(newTweet)
            .map(tweetMapper::getDestination);
    }

    public Mono<Void> delete(Tweet existingTweet) {
        return tweetRepository.delete(existingTweet);
    }


}
