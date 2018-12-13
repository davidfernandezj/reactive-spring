package demo.spring.reactivespring.testimplementations;

import demo.spring.reactivespring.model.Tweet;
import demo.spring.reactivespring.repository.TweetRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.reactivestreams.Publisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestTweetRepository implements TweetRepository {

    private final Map<String, Tweet> tweets = new HashMap<>();

    @Override
    public Mono<Tweet> findById(String tweetId) {
        return Mono.just(tweets.get(tweetId));
    }

    @Override
    public Flux<Tweet> findAll() {
        return Flux.fromStream(tweets.values().stream());
    }

    @Override
    public Flux<Tweet> findAllById(Iterable<String> iterable) {
        return Flux.fromStream(
            StreamSupport.stream(iterable.spliterator(), false)
                .map(tweets::get)
                .filter(Objects::nonNull));
    }

    @Override
    public Mono<Long> count() {
        return Mono.just(((long) tweets.size()));
    }

    @Override
    public Mono<Void> deleteById(String s) {
        tweets.remove(s);
        return Mono.empty();
    }

    @Override
    public <S extends Tweet> Flux<S> saveAll(Iterable<S> iterable) {
        Flux<Tweet> tweetFlux = Flux.fromStream(iterableToStream(iterable)
            .map(this::save)
            .map(Mono::block));
        return (Flux<S>) tweetFlux;
    }

    @Override
    public Mono<Boolean> existsById(String s) {
        return Mono.just(tweets.containsKey(s));
    }

    @Override
    public Mono<Tweet> save(Tweet tweet) {
        String tweetId = tweet.getId();
        if(tweetId == null) {
            tweetId = UUID.randomUUID().toString();
            tweet.setId(tweetId);
        }
        tweets.put(tweetId, tweet);
        return Mono.just(tweet);
    }

    @Override
    public Mono<Void> deleteAll(Iterable<? extends Tweet> iterable) {
        iterableToStream(iterable)
            .map(Tweet::getId)
            .forEach(tweets::remove);
        return Mono.empty();
    }

    private Stream<? extends Tweet> iterableToStream(Iterable<? extends Tweet> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    @Override
    public Mono<Void> deleteAll() {
        tweets.clear();
        return Mono.empty();
    }

    @Override
    public <S extends Tweet> Mono<S> insert(S s) {
        return (Mono<S>) save(s);
    }

    @Override
    public <S extends Tweet> Flux<S> insert(Iterable<S> iterable) {
        Flux<Tweet> tFlux = Flux.fromStream(iterableToStream(iterable)
            .map(this::insert)
            .map(a -> (Tweet) a.block()));
        return (Flux<S>) tFlux;
    }

    @Override
    public Mono<Void> delete(Tweet tweet) {
        return deleteById(tweet.getId());
    }

    @Override
    public Flux<Tweet> findAllById(Publisher<String> publisher) {
        throw new UnsupportedOperationException("Method 'public Flux<Tweet> findAllById(Publisher<String> publisher)' not implemented yet");
    }

    @Override
    public Mono<Void> deleteById(Publisher<String> publisher) {
        throw new UnsupportedOperationException("Method 'public Mono<Void> deleteById(Publisher<String> publisher)' not implemented yet");
    }

    @Override
    public <S extends Tweet> Flux<S> saveAll(Publisher<S> publisher) {
        throw new UnsupportedOperationException("Method 'public <S extends Tweet> Flux<S> saveAll(Publisher<S> publisher)' not implemented yet");
    }

    @Override
    public Mono<Tweet> findById(Publisher<String> publisher) {
        throw new UnsupportedOperationException("Method 'public Mono<Tweet> findById(Publisher<String> publisher)' not implemented yet");
    }

    @Override
    public Mono<Boolean> existsById(Publisher<String> publisher) {
        throw new UnsupportedOperationException("Method 'public Mono<Boolean> existsById(Publisher<String> publisher)' not implemented yet");
    }

    @Override
    public Mono<Void> deleteAll(Publisher<? extends Tweet> publisher) {
        throw new UnsupportedOperationException("Method 'public Mono<Void> deleteAll(Publisher<? extends Tweet> publisher)' not implemented yet");
    }

    @Override
    public <S extends Tweet> Flux<S> insert(Publisher<S> publisher) {
        throw new UnsupportedOperationException("Method 'public <S extends Tweet> Flux<S> insert(Publisher<S> publisher)' not implemented yet");
    }

    @Override
    public <S extends Tweet> Mono<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("Method 'public <S extends Tweet> Mono<S> findOne(Example<S> example)' not implemented yet");
    }

    @Override
    public <S extends Tweet> Flux<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("Method 'public <S extends Tweet> Flux<S> findAll(Example<S> example)' not implemented yet");
    }

    @Override
    public <S extends Tweet> Flux<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("Method 'public <S extends Tweet> Flux<S> findAll(Example<S> example, Sort sort)' not implemented yet");
    }

    @Override
    public <S extends Tweet> Mono<Long> count(Example<S> example) {
        throw new UnsupportedOperationException("Method 'public <S extends Tweet> Mono<Long> count(Example<S> example)' not implemented yet");
    }

    @Override
    public <S extends Tweet> Mono<Boolean> exists(Example<S> example) {
        throw new UnsupportedOperationException("Method 'public <S extends Tweet> Mono<Boolean> exists(Example<S> example)' not implemented yet");
    }

    @Override
    public Flux<Tweet> findAll(Sort sort) {
        throw new UnsupportedOperationException("Method 'public Flux<Tweet> findAll(Sort sort)' not implemented yet");
    }

}
