package pl.playground.tweets;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
class FakeTweets implements Tweets {

    private final Flux<Tweet> tweetsStream = defaultTweetStream();

    private final List<Tweet> tweets;

    FakeTweets() {
        this.tweets = new ArrayList<>();

        for (long i = 0; i < 100_000; i++) {
            tweets.add(randomTweet(i));
        }
    }

    @Override
    public Flux<Tweet> tweetsStream() {
         return tweetsStream;
    }

    private Flux<Tweet> defaultTweetStream() {
         return Flux
                 .generate(
                        () -> 0,
                        (state, sink) -> {
                            sink.next(tweets.get(state));
                            return state+1;
                        })
                 .cast(Tweet.class);
    }

    private Tweet randomTweet(long sequenceId) {
        return new Tweet(String.valueOf(sequenceId), String.format("Text no %d", sequenceId), UUID.randomUUID().toString());
    }

}
