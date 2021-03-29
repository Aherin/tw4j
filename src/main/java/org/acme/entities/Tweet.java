package org.acme.entities;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tweet {

    private long id;
    private String screenName;
    private String text;
    private String place;
    private boolean valid;

    public Tweet() {
        // Empty method implementation
    }

    public Tweet(long id, String screenName, String text, String place) {
        this.screenName = screenName;
        this.text = text;
        this.place = place;
        this.id = id;
    }

    /**
     * Regresa una lista de Tweets los cuales tienen la propiedad {@code valid} en
     * {@code true}
     * 
     * @param tweets
     * @return {@code Set<Tweet>}
     */
    public static Set<Tweet> getValidTweets(Set<Tweet> tweets) {
        Stream<Tweet> stream = tweets.stream();
        return stream.filter(t -> Boolean.TRUE.equals(t.getValid())).collect(Collectors.toSet());
    }

    /**
     * Regresa un objecto de tipo {@link Tweet} una vez se ha actualizado el
     * parametro {@code valid}
     * 
     * @param tweets
     * @param id
     * @return {@link Tweet}
     */
    public static Tweet updateValidTweet(Set<Tweet> tweets, long id) {
        Tweet foundTweet = new Tweet();
        for (Tweet tweet : tweets) {
            if (tweet.getId() == id) {
                foundTweet = tweet;
            }
        }
        if (tweets.remove(foundTweet)) {
            foundTweet.setValid(true);
            tweets.add(foundTweet);
        }
        return foundTweet;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
