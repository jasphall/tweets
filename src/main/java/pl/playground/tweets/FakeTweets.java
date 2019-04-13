package pl.playground.tweets;

import me.xdrop.jrand.JRand;
import me.xdrop.jrand.generators.text.SentenceGenerator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
class FakeTweets implements Tweets {

    private final Flux<Tweet> tweetsStream = defaultTweetStream();

    private final SentenceGenerator sentenceGenerator;
    private final List<Tweet> tweets;

    FakeTweets() {
        this.sentenceGenerator = JRand.sentence();
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
        return new Tweet(String.valueOf(sequenceId), sentenceGenerator.gen(), UUID.randomUUID().toString());
    }

}
