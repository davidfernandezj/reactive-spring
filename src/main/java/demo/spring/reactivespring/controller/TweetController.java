package demo.spring.reactivespring.controller;

import demo.spring.reactivespring.dto.TweetDto;
import demo.spring.reactivespring.model.Tweet;
import demo.spring.reactivespring.service.TweetService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CacheConfig(cacheNames={"tweetsCache"})
public class TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping("/tweets")
    @Cacheable
    public Flux<TweetDto> getAllTweets() {
        return tweetService.findAll();
    }

    @GetMapping("/tweets/trending")
    public Flux<TweetDto> getTrendingTweets() {
        return tweetService.findAllTrendingTweets();
    }

    @PostMapping("/tweets")
    @CachePut
    public Mono<TweetDto> createTweets(@Valid @RequestBody Tweet tweet) {
        return tweetService.save(tweet);
    }

    @GetMapping("/tweets/{id}")
    @Cacheable
    public Mono<ResponseEntity<TweetDto>> getTweetById(@PathVariable(value = "id") String tweetId) {
        return tweetService.findById(tweetId)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/tweets/{id}")
    @CachePut
    public Mono<ResponseEntity<TweetDto>> updateTweet(@PathVariable(value = "id") String tweetId,
        @Valid @RequestBody Tweet tweet) {
        return tweetService.updateTweet(tweetId, tweet)
            .map(updatedTweet -> new ResponseEntity<>(updatedTweet, HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/tweets/{id}")
    @CachePut
    public Mono<ResponseEntity<Void>> deleteTweet(@PathVariable(value = "id") String tweetId) {
        return tweetService.deleteTweet(tweetId)
            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Tweets are Sent to the client as Server Sent Events
    @GetMapping(value = "/stream/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TweetDto> streamAllTweets() {
        return tweetService.findAll();
    }

}