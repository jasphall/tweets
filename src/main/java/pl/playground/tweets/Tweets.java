package pl.playground.tweets;

import reactor.core.publisher.Flux;

public interface Tweets {

    Flux<Tweet> tweetsStream();

}
