package com.drighetto.twitter4j;

import org.junit.BeforeClass;
import org.junit.Test;

import twitter4j.DirectMessage;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Simple class to show synchronous actions on Twitter through Twitter4J API
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 * 
 */
public class TwitterSynchronousActionsTest {

	/** Twitter API accessor */
	private static final Twitter TWITTER_CLIENT = new Twitter(Context.TWITTER_LOGIN, Context.TWITTER_PWD);

	/**
	 * Method to verify user credentials and display Twitter client version
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void verifyCredentialsAndDisplayTwitterClientVersion() throws Exception {
		System.out.println("***************************************************************************");
		System.out.println("Verify user credentials.");
		// Returns an HTTP 200 OK response code and a representation of the
		// requesting user if authentication was successful; returns a 401
		// status code and an error message if not. Use this method to test if
		// supplied user credentials are valid.
		User cUser = TWITTER_CLIENT.verifyCredentials();
		if (cUser != null) {
			System.out.printf("Name       : %s\n", cUser.getName());
			System.out.printf("Location   : %s\n", cUser.getLocation());
			System.out.printf("ScreenName : %s\n", cUser.getScreenName());
			System.out.printf("StatusText : %s\n", cUser.getStatusText());
			System.out.printf("TimeZone   : %s\n", cUser.getTimeZone());
		} else {
			System.err.println("Invalid credentials !!!");
		}
		System.out.println("\nTwitter client version.");
		System.out.printf("Client URL     : %s\n", TWITTER_CLIENT.getClientURL());
		System.out.printf("Client version : %s\n", TWITTER_CLIENT.getClientVersion());
		System.out.printf("Base URL       : %s\n", TWITTER_CLIENT.getBaseURL());
		System.out.printf("User agent     : %s\n", TWITTER_CLIENT.getUserAgent());
	}

	/**
	 * Test showing how to retrieve last messages
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void obtainsLastTrends() throws Exception {
		System.out.println("***************************************************************************");
		System.out.println("Returns the top 30 trending topics for each day in the current week.");
		// Returns the top 30 trending topics for each day in the current week.
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		List<Trends> trendsLst = TWITTER_CLIENT.getWeeklyTrends();
		if (trendsLst != null) {
			for (Trends trends : trendsLst) {
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
	 * Test showing how to retrieve current trends
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void obtainsCurrentTrends() throws Exception {
		System.out.println("***************************************************************************");
		System.out.println("Returns the current top 10 trending topics on Twitter.");
		// Returns the current top 10 trending topics on Twitter.
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Trends trends = TWITTER_CLIENT.getCurrentTrends();
		System.out.printf("==> Day %s <===\n", dateFormat.format(trends.getTrendAt()));
		if (trends.getTrends() != null) {
			for (Trend trend : trends.getTrends()) {
				System.out.printf("Name:[%s] - Query:[%s] - Url:[%s]\n", trend.getName(), trend.getQuery(), trend.getUrl());
			}
		}
	}

	/**
	 * Test showing direct messages sent to the current authenticated user
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void obtainsDirectMessages() throws Exception {
		System.out.println("***************************************************************************");
		System.out.println("Returns a list of the direct messages sent to the authenticating user.");
		// Returns a list of the direct messages sent to the authenticating
		// user.
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		List<DirectMessage> messages = TWITTER_CLIENT.getDirectMessages();
		if (messages != null) {
			for (DirectMessage message : messages) {
				System.out.printf("From:[%s] - To:[%s] - SentDate:[%s] - Content:[%s]\n", message.getSenderScreenName(), message.getRecipientScreenName(), dateFormat.format(message.getCreatedAt()), message.getText());
			}
		}
	}

	/**
	 * Test showing how obtains followers list
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void obtainsFollowers() throws Exception {
		System.out.println("***************************************************************************");
		System.out.println("Returns the authenticating user's followers, each with current status inline.");
		// Returns the authenticating user's followers, each with current status
		// inline
		List<User> followers = TWITTER_CLIENT.getFollowers();
		if (followers != null) {
			for (User follower : followers) {
				System.out.printf("Name:[%s] - Location:[%s] - ScreenName:[%s] - StatusText:[%s]\n", follower.getName(), follower.getLocation(), follower.getScreenName(), follower.getStatusText());
			}
		}
	}

	/**
	 * Test showing how to update status text message
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void updateStatus() throws Exception {
		System.out.println("***************************************************************************");
		System.out.println("Update status");
		String cStatus = TWITTER_CLIENT.getUserDetail(Context.TWITTER_LOGIN).getStatusText();
		System.out.printf("Current status : [%s]\n", cStatus);
		// Update status
		TWITTER_CLIENT.updateStatus("Update my status using Twitter4J API ;o)");
		System.out.printf("Current status : [%s]\n", TWITTER_CLIENT.getUserDetail(Context.TWITTER_LOGIN).getStatusText());
		// Reset status to initial value
		TWITTER_CLIENT.updateStatus(cStatus);
		System.out.printf("Current status : [%s]\n", TWITTER_CLIENT.getUserDetail(Context.TWITTER_LOGIN).getStatusText());
	}

}
