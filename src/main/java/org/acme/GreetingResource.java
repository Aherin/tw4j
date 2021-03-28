package org.acme;

import javax.ws.rs.core.Response;

import org.acme.entities.Tweet;
import org.acme.services.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-resteasy")
public class GreetingResource {

    @Autowired
    StreamingService streamingService;

    @GetMapping(produces = "application/json")
    public Response list() {
        return Response.ok(streamingService.getTweets()).build();
    }

    @GetMapping(path = "/valid", produces = "application/json")
    public Response lists() {
        return Response.ok(Tweet.getValidTweets(streamingService.getTweets())).build();
    }
}