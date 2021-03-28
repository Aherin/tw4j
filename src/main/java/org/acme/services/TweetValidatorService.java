package org.acme.services;

import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;

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

}
