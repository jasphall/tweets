package pl.playground.tweets;

import reactor.core.publisher.Flux;

import java.time.Duration;

public interface Tweets {

    Flux<Tweet> tweetsStream();

    Flux<Tweet> changeSpeed(Duration change);
}
