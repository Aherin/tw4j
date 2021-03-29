package org.acme.services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@ApplicationScoped
public class TwitterTrendService {

    private static final Logger log = Logger.getLogger(TwitterTrendService.class);
    private static final int WORLDWIDE_TREND_CODE = 1;

    @Autowired
    private TwitterConfigurationService twitterConfigurationService;
    @ConfigProperty(name = "twitter.trend.maximum.hashtag.results", defaultValue = "10")
    int maximumHashtagResults;

    public List<Trend> getWorldwideTrends() {
        int count = 0;
        List<Trend> trendList = new ArrayList<>();
        try {
            Twitter twitter = new TwitterFactory(twitterConfigurationService.getTwitterConfiguration().build())
                    .getInstance();
            Trends trends = twitter.getPlaceTrends(WORLDWIDE_TREND_CODE);
            for (Trend trend : trends.getTrends()) {
                count++;
                trendList.add(trend);
                if (count == maximumHashtagResults) {
                    break;
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return trendList;
    }
}
