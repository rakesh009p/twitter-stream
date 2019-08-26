package com.stackroute.twitterstream;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@RestController
public class Stream {
    // /statuses/home_timeline.json
    WebClient client = WebClient.builder()
            .baseUrl("https://api.twitter.com/1.1")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//            .defaultHeaders(HttpHeaders.AUTHORIZATION, OAuth)
            .build();

    @GetMapping("/tweets")
    public Flux<?> getTweets() {
        List<Object> myArray = new ArrayList<>();
        Flux<?> response = Flux.fromIterable(myArray);

        return client.get()
                .uri("/statuses/home_timeline.json")
                .header("Authorization", "OAuth oauth_consumer_key=\"5NVjD0i1Ns60tapjScjyLx2zj\",oauth_token=\"950068702078148608-VC5VqbimxRd0zTJRYrX2ADqHBj03knV\",oauth_signature_method=\"HMAC-SHA1\",oauth_version=\"1.0\"")
                .retrieve()
                .bodyToFlux(Object.class);

    }

}

