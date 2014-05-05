package auth;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class ConfigBuilder {
	private static Configuration config;
	
	private static final String API_KEY = "lueXa1fwhkRKnQUmnS3BqA";
	private static final String API_SECRET = "lh9L4nuraZosK0VgsC9fAIJvdVRfUC5OiwbMnabkMk";
	private static final String ACCESS_TOKEN = "1882349227-uhlOky6DZqFDrLKxXjyCftEzXepJfslSrs1jRo8";
	private static final String ACCESS_SECRET = "xnR6tG4QxCKBbAVtsWxdFYjXzxNk2eRvr9vjYYN31Ep4f";
	
	static {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey(API_KEY);
		cb.setOAuthConsumerSecret(API_SECRET);
		cb.setOAuthAccessToken(ACCESS_TOKEN);
		cb.setOAuthAccessTokenSecret(ACCESS_SECRET);
		config = cb.build();
	}
	
	public static Configuration getConfig() {
		return config;
	}
}
