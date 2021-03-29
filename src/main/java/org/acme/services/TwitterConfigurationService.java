package org.acme.services;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import twitter4j.conf.ConfigurationBuilder;

@ApplicationScoped
public class TwitterConfigurationService {
    @ConfigProperty(name = "twitter.config.debug.enable")
    boolean debug;
    @ConfigProperty(name = "twitter.config.oauth.consumer.key")
    String consumerKey;
    @ConfigProperty(name = "twitter.config.oauth.consumer.secret")
    String consumerSecret;
    @ConfigProperty(name = "twitter.config.oauth.access.token")
    String accessToken;
    @ConfigProperty(name = "twitter.config.oauth.access.token.secret")
    String accessTokenSecret;

    /**
     * Retorna la configuracion necesaria para la autenticacion con la API publica
     * de Twitter
     * 
     * @return {@link ConfigurationBuilder}
     */
    public ConfigurationBuilder getTwitterConfiguration() {
        ConfigurationBuilder config = new ConfigurationBuilder();
        config.setDebugEnabled(debug).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret);
        return config;
    }
}
