package pl.playground.tweets;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
class TweetsHandler {

    private final Tweets tweets;

    TweetsHandler(Tweets tweets) {
        this.tweets = tweets;
    }

    Mono<ServerResponse> getAsJson(ServerRequest serverRequest) {
        int count = getCountParamFromRequest(serverRequest);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(tweets.tweetsStream().take(count), Tweet.class);
    }

    Mono<ServerResponse> getAsSSE(ServerRequest serverRequest) {
        int count = getCountParamFromRequest(serverRequest);

        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(tweets.tweetsStream().take(count), Tweet.class);
    }

    private int getCountParamFromRequest(ServerRequest serverRequest) {
        Optional<String> countParam = serverRequest.queryParam("count");
        int count = 0;

        if (countParam.isPresent()) {
            count = Integer.valueOf(countParam.get());
        }
        return count;
    }

}
