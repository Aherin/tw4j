package org.acme.services;

import javax.enterprise.context.ApplicationScoped;

import twitter4j.conf.ConfigurationBuilder;

@ApplicationScoped
public class TwitterConfigurationService {

    public ConfigurationBuilder getTwitterConfiguration() {
        ConfigurationBuilder config = new ConfigurationBuilder();
        config.setDebugEnabled(false).setOAuthConsumerKey("jRtx7mcn1vhgmkt15MnDOV3dY")
                .setOAuthConsumerSecret("U7JpOrO82V6h7FL1pVw4ioNoaZbt7TGXJbP1H6YLGKZpUFrIBg")
                .setOAuthAccessToken("2598404008-EAWKX8WNIF0hkVfJ76EI9MznFGJ2hleW3xkXkQx")
                .setOAuthAccessTokenSecret("6vthrEaGPGaAxjLpyPHW1GQvZROR1RRE6DvJEDHNqLNyG");
        return config;
    }
}
