package org.acme.resources;

import javax.ws.rs.core.Response;

import org.acme.services.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tweets")
public class TweetResource {
    @Autowired
    StreamingService streamingService;

    @GetMapping(produces = "application/json")
    public Response list() {
        return Response.ok(streamingService.getTweets()).build();
    }
}
