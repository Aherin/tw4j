package org.acme.services;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.acme.entities.Tweet;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import io.quarkus.runtime.StartupEvent;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

@ApplicationScoped
public class StreamingService {

    private long id;
    private TwitterStream twitterStream;
    private Set<Tweet> tweets = Collections.synchronizedSet(new LinkedHashSet<>());
    private static final Logger log = Logger.getLogger(StreamingService.class);

    @Autowired
    private TweetValidatorService tweetValidatorService;
    @Autowired
    private TwitterConfigurationService twitterConfigurationService;

    void onStart(@Observes StartupEvent ev) {
        ConfigurationBuilder config = twitterConfigurationService.getTwitterConfiguration();
        twitterStream = new TwitterStreamFactory(config.build()).getInstance().addListener(new StatusListener() {
            @Override
            public void onStatus(Status status) {
                if (tweetValidatorService.isTweetValid(status)) {
                    log.debug("@" + status.getUser().getScreenName() + " - " + status.getText());
                    tweets.add(new Tweet(id++, status.getUser().getScreenName(), status.getText(),
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

    long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

}
