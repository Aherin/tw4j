package org.acme.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.acme.entities.Tweet;
import org.acme.services.StreamingService;
import org.acme.services.TwitterTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tweets")
public class TweetResource {
    @Autowired
    StreamingService streamingService;
    @Autowired
    TwitterTrendService twitterTrendService;

    @GetMapping(produces = "application/json")
    public Response listTweets() {
        return Response.ok(streamingService.getTweets()).build();
    }

    @GetMapping(path = "/valid", produces = "application/json")
    public Response listValidTweets() {
        return Response.ok(Tweet.getValidTweets(streamingService.getTweets())).build();
    }

    @PutMapping(path = "/valid/{id}", produces = "application/json")
    public Response updateValidTweets(@PathVariable(name = "id") long id) {
        String codeNotFoundMessage = "{\"errorMessage\": \"Code not found\"}";
        Tweet tweet = Tweet.updateValidTweet(streamingService.getTweets(), id);
        if (tweet.getScreenName() == null) {
            return Response.status(Status.NOT_FOUND).entity(codeNotFoundMessage).build();
        }
        return Response.status(Status.CREATED).entity(tweet).build();
    }

    @GetMapping(path = "/trends", produces = "application/json")
    public Response listTrends() {
        return Response.ok(twitterTrendService.getWorldwideTrends()).build();
    }
}
