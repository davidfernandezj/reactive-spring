package demo.spring.reactivespring.testimplementations;

import demo.spring.reactivespring.model.TrendingWord;
import demo.spring.reactivespring.repository.TrendingWordRepository;
import org.reactivestreams.Publisher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestTrendingWordRepository implements TrendingWordRepository {

    @Override
    public Flux<TrendingWord> findAll() {
        return Flux.just(new TrendingWord("Hokage"));
    }

    @Override
    public <S extends TrendingWord> Mono<S> insert(S s) {
        throw new UnsupportedOperationException("The method 'public <S extends TrendingWord> Mono<S> insert(S s)' is not implemented yet'");
    }

    @Override
    public <S extends TrendingWord> Flux<S> insert(Iterable<S> iterable) {
        throw new UnsupportedOperationException("The method 'public <S extends TrendingWord> Flux<S> insert(Iterable<S> iterable)' is not implemented yet'");
    }

    @Override
    public <S extends TrendingWord> Flux<S> insert(Publisher<S> publisher) {
        throw new UnsupportedOperationException("The method 'public <S extends TrendingWord> Flux<S> insert(Publisher<S> publisher)' is not implemented yet'");
    }

    @Override
    public <S extends TrendingWord> Mono<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("The method 'public <S extends TrendingWord> Mono<S> findOne(Example<S> example)' is not implemented yet'");
    }

    @Override
    public <S extends TrendingWord> Flux<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("The method 'public <S extends TrendingWord> Flux<S> findAll(Example<S> example)' is not implemented yet'");
    }

    @Override
    public <S extends TrendingWord> Flux<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("The method 'public <S extends TrendingWord> Flux<S> findAll(Example<S> example, Sort sort)' is not implemented yet'");
    }

    @Override
    public <S extends TrendingWord> Mono<Long> count(Example<S> example) {
        throw new UnsupportedOperationException("The method 'public <S extends TrendingWord> Mono<Long> count(Example<S> example)' is not implemented yet'");
    }

    @Override
    public <S extends TrendingWord> Mono<Boolean> exists(Example<S> example) {
        throw new UnsupportedOperationException("The method 'public <S extends TrendingWord> Mono<Boolean> exists(Example<S> example)' is not implemented yet'");
    }

    @Override
    public Flux<TrendingWord> findAll(Sort sort) {
        throw new UnsupportedOperationException("The method 'public Flux<TrendingWord> findAll(Sort sort)' is not implemented yet'");
    }

    @Override
    public <S extends TrendingWord> Mono<S> save(S s) {
        throw new UnsupportedOperationException("The method 'public <S extends TrendingWord> Mono<S> save(S s)' is not implemented yet'");
    }

    @Override
    public <S extends TrendingWord> Flux<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException("The method 'public <S extends TrendingWord> Flux<S> saveAll(Iterable<S> iterable)' is not implemented yet'");
    }

    @Override
    public <S extends TrendingWord> Flux<S> saveAll(Publisher<S> publisher) {
        throw new UnsupportedOperationException("The method 'public <S extends TrendingWord> Flux<S> saveAll(Publisher<S> publisher)' is not implemented yet'");
    }

    @Override
    public Mono<TrendingWord> findById(String s) {
        throw new UnsupportedOperationException("The method 'public Mono<TrendingWord> findById(String s)' is not implemented yet'");
    }

    @Override
    public Mono<TrendingWord> findById(Publisher<String> publisher) {
        throw new UnsupportedOperationException("The method 'public Mono<TrendingWord> findById(Publisher<String> publisher)' is not implemented yet'");
    }

    @Override
    public Mono<Boolean> existsById(String s) {
        throw new UnsupportedOperationException("The method 'public Mono<Boolean> existsById(String s)' is not implemented yet'");
    }

    @Override
    public Mono<Boolean> existsById(Publisher<String> publisher) {
        throw new UnsupportedOperationException("The method 'public Mono<Boolean> existsById(Publisher<String> publisher)' is not implemented yet'");
    }

    @Override
    public Flux<TrendingWord> findAllById(Iterable<String> iterable) {
        throw new UnsupportedOperationException("The method 'public Flux<TrendingWord> findAllById(Iterable<String> iterable)' is not implemented yet'");
    }

    @Override
    public Flux<TrendingWord> findAllById(Publisher<String> publisher) {
        throw new UnsupportedOperationException("The method 'public Flux<TrendingWord> findAllById(Publisher<String> publisher)' is not implemented yet'");
    }

    @Override
    public Mono<Long> count() {
        throw new UnsupportedOperationException("The method 'public Mono<Long> count()' is not implemented yet'");
    }

    @Override
    public Mono<Void> deleteById(String s) {
        throw new UnsupportedOperationException("The method 'public Mono<Void> deleteById(String s)' is not implemented yet'");
    }

    @Override
    public Mono<Void> deleteById(Publisher<String> publisher) {
        throw new UnsupportedOperationException("The method 'public Mono<Void> deleteById(Publisher<String> publisher)' is not implemented yet'");
    }

    @Override
    public Mono<Void> delete(TrendingWord trendingWord) {
        throw new UnsupportedOperationException("The method 'public Mono<Void> delete(TrendingWord trendingWord)' is not implemented yet'");
    }

    @Override
    public Mono<Void> deleteAll(Iterable<? extends TrendingWord> iterable) {
        throw new UnsupportedOperationException("The method 'public Mono<Void> deleteAll(Iterable<? extends TrendingWord> iterable)' is not implemented yet'");
    }

    @Override
    public Mono<Void> deleteAll(Publisher<? extends TrendingWord> publisher) {
        throw new UnsupportedOperationException("The method 'public Mono<Void> deleteAll(Publisher<? extends TrendingWord> publisher)' is not implemented yet'");
    }

    @Override
    public Mono<Void> deleteAll() {
        throw new UnsupportedOperationException("The method 'public Mono<Void> deleteAll()' is not implemented yet'");
    }

}
