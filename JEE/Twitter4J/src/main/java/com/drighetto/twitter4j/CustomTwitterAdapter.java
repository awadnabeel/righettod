package com.drighetto.twitter4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * Custom Twitter adapter<br>
 * Used to perform asynchronous processing in response to asynchronous Twitter
 * call using Twitter4J Async class<br>
 * <br>
 * 
 * @see http 
 *      ://yusuke.homeip.net/twitter4j/en/javadoc/twitter4j/TwitterAdapter.html
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class CustomTwitterAdapter extends TwitterAdapter {

	/**
	 * {@inheritDoc}
	 * 
	 * @see twitter4j.TwitterAdapter#onException(twitter4j.TwitterException,
	 *      int)
	 */
	@SuppressWarnings("boxing")
	@Override
	public void onException(TwitterException ex, int method) {
		System.err.printf("!!! ERROR ==> Exception:[%s] - HTTP Response Code:[%s] !!!\n", ex.getMessage(), method);
		ex.printStackTrace();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see twitter4j.TwitterAdapter#gotCurrentTrends(twitter4j.Trends)
	 */
	@Override
	public void gotCurrentTrends(Trends trends) {
		// Display trends informations...
		System.out.println("Returns the current top 10 trending topics on Twitter.");
		if (trends != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			System.out.printf("==> Day %s <===\n", dateFormat.format(trends.getTrendAt()));
			if (trends.getTrends() != null) {
				for (Trend trend : trends.getTrends()) {
					System.out.printf("Name:[%s] - Query:[%s] - Url:[%s]\n", trend.getName(), trend.getQuery(), trend.getUrl());
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see twitter4j.TwitterAdapter#gotFollowers(java.util.List)
	 */
	@Override
	public void gotFollowers(List<User> users) {
		// Display followers informations...
		System.out.println("Returns the authenticating user's followers, each with current status inline.");
		if (users != null) {
			for (User follower : users) {
				System.out.printf("Name:[%s] - Location:[%s] - ScreenName:[%s] - StatusText:[%s]\n", follower.getName(), follower.getLocation(), follower.getScreenName(), follower.getStatusText());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see twitter4j.TwitterAdapter#gotWeeklyTrends(java.util.List)
	 */
	@Override
	public void gotWeeklyTrends(List<Trends> trendsList) {
		// Display trends informations...
		System.out.println("Returns the top 30 trending topics for each day in the current week.");
		if (trendsList != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			for (Trends trends : trendsList) {
				System.out.printf("==> Day %s <===\n", dateFormat.format(trends.getTrendAt()));
				if (trends.getTrends() != null) {
					for (Trend trend : trends.getTrends()) {
						System.out.printf("Name:[%s] - Query:[%s] - Url:[%s]\n", trend.getName(), trend.getQuery(), trend.getUrl());
					}
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see twitter4j.TwitterAdapter#gotDirectMessages(java.util.List)
	 */
	@Override
	public void gotDirectMessages(List<DirectMessage> messages) {
		// Display messages informations...
		System.out.println("Returns a list of the direct messages sent to the authenticating user.");
		if (messages != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			for (DirectMessage message : messages) {
				System.out.printf("From:[%s] - To:[%s] - SentDate:[%s] - Content:[%s]\n", message.getSenderScreenName(), message.getRecipientScreenName(), dateFormat.format(message.getCreatedAt()), message.getText());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see twitter4j.TwitterAdapter#updatedStatus(twitter4j.Status)
	 */
	@Override
	public void updatedStatus(Status statuses) {
		// Display current status
		System.out.printf("Current status : [%s]\n", statuses.getText());
	}

}
