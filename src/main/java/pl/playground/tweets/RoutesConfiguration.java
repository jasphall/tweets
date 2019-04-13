package pl.playground.tweets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
class RoutesConfiguration {

    @Bean
    RouterFunction<ServerResponse> routes(TweetsHandler tweetsHandler) {
        return route(GET("/tweets/json"), tweetsHandler::getAsJson)
                .andRoute(GET("/tweets/sse"), tweetsHandler::getAsSSE);
    }

}
