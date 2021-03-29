package org.acme.services;

import java.util.Arrays;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import org.acme.entities.Tweet;

import twitter4j.Status;
import twitter4j.User;

@ApplicationScoped
public class TweetValidatorService {

    public TweetValidatorService() {
        // Empty constructor implementation
    }

    enum Language {
        ES, FR, IT
    }

    public boolean isTweetValid(Status status) {
        return isfollowersCountGreaterThanOrEqualTo(status.getUser(), 1) && hasLocationEnabled(status)
                && hasAllowedLanguage(status);
    }

    private boolean isfollowersCountGreaterThanOrEqualTo(User user, int minimumFollowersCount) {

        return user.getFollowersCount() >= minimumFollowersCount;
    }

    private boolean hasLocationEnabled(Status status) {
        return status.getPlace() != null;
    }

    private boolean hasAllowedLanguage(Status status) {
        long count = Arrays.asList(Language.values()).stream()
                .filter(language -> language.toString().equalsIgnoreCase(status.getLang())).count();
        return count > 0;
    }

    public Response processValidationUpdate(Set<Tweet> tweets, long id) {
        String codeNotFoundMessage = "{\"errorMessage\": \"Code not found\"}";
        Tweet tweet = Tweet.updateValidTweet(tweets, id);
        if (tweet.getScreenName() == null) {
            return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).entity(codeNotFoundMessage).build();
        }
        return Response.status(javax.ws.rs.core.Response.Status.CREATED).entity(tweet).build();
    }

}
