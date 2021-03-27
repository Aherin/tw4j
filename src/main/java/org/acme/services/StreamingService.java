package org.acme.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.logging.Logger;

import io.quarkus.runtime.StartupEvent;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

@ApplicationScoped
public class StreamingService {

    private TwitterStream twitterStream;
    private static final Logger log = Logger.getLogger(StreamingService.class);

    private StreamingService() {
    }

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
                log.debug("@" + status.getUser().getScreenName() + " - " + status.getText());
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

    public TwitterStream getTwitterStream() {
        return twitterStream;
    }

    public void setTwitterStream(TwitterStream twitterStream) {
        this.twitterStream = twitterStream;
    }

    public static Logger getLog() {
        return log;
    }

}
