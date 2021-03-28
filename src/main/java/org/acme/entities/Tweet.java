package org.acme.entities;

public class Tweet {

    private String screenName;
    private String text;
    private String place;
    private boolean valid;

    public Tweet() {
        //Empty method implementation
    }
    public Tweet(String screenName, String text, String place) {
        this.screenName = screenName;
        this.text = text;
        this.place = place;
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
}
