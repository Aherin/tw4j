package org.acme.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.logging.Logger;

import io.quarkus.runtime.StartupEvent;
import twitter4j.*;

@ApplicationScoped
public class StreamingService {

    private TwitterStream twitterStream;
    private static final Logger log = Logger.getLogger(StreamingService.class);

    private StreamingService() {
    }

    void onStart(@Observes StartupEvent ev) {
        log.info("The application is starting...");
        twitterStream = new TwitterStreamFactory().getInstance().addListener(new StatusListener() {
            @Override
            public void onStatus(Status status) {
                log.debug("@" + status.getUser().getScreenName() + " - " + status.getText());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                //Empty method implementation
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                //Empty method implementation
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                //Empty method implementation
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                //Empty method implementation
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
