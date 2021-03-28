package org.acme.services;

import java.util.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.acme.entities.Tweet;
import org.jboss.logging.Logger;

import io.quarkus.runtime.StartupEvent;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

@ApplicationScoped
public class StreamingService {

    private TwitterStream twitterStream;
    private Set<Tweet> tweets = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    private static final Logger log = Logger.getLogger(StreamingService.class);

    @Inject
    private TweetValidatorService tweetValidatorService;

    void onStart(@Observes StartupEvent ev) {
        log.info("The application is starting...");
        ConfigurationBuilder config = new ConfigurationBuilder();
        config.setDebugEnabled(false).setOAuthConsumerKey("jRtx7mcn1vhgmkt15MnDOV3dY")
                .setOAuthConsumerSecret("U7JpOrO82V6h7FL1pVw4ioNoaZbt7TGXJbP1H6YLGKZpUFrIBg")
                .setOAuthAccessToken("2598404008-EAWKX8WNIF0hkVfJ76EI9MznFGJ2hleW3xkXkQx")
                .setOAuthAccessTokenSecret("6vthrEaGPGaAxjLpyPHW1GQvZROR1RRE6DvJEDHNqLNyG");

        twitterStream = new TwitterStreamFactory(config.build()).getInstance().addListener(new StatusListener() {
            @Override
            public void onStatus(Status status) {
                if (tweetValidatorService.isTweetValid(status)) {
                    log.debug("@" + status.getUser().getScreenName() + " - " + status.getText());
                    tweets.add(new Tweet(status.getUser().getScreenName(), status.getText(),
                            status.getPlace().getFullName()));
                    log.debug("Numero de tweets:" + tweets.size());
                }

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                // Empty method implementation
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                // Empty method implementation
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                // Empty method implementation
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                // Empty method implementation
            }

            @Override
            public void onException(Exception ex) {
                log.error(ex.getMessage(), ex);
            }
        }).sample();
    }

    TwitterStream getTwitterStream() {
        return twitterStream;
    }

    void setTwitterStream(TwitterStream twitterStream) {
        this.twitterStream = twitterStream;
    }

    static Logger getLog() {
        return log;
    }

    public Set<Tweet> getTweets() {
        return tweets;
    }

    void setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
    }

}
