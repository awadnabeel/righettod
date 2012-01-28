package com.drighetto.twitter4j;

import org.junit.BeforeClass;
import org.junit.Test;

import twitter4j.AsyncTwitter;
import twitter4j.User;

/**
 * Simple class to show asynchronous actions on Twitter through Twitter4J API
 * 
 * @author Dominique RIGHETTO (dominique.righetto@logica.com)
 *
 */
public class TwitterAsynchronousActionsTest {
	
	/** Twitter API accessor */
	private static final AsyncTwitter TWITTER_CLIENT = new AsyncTwitter(Context.TWITTER_LOGIN, Context.TWITTER_PWD);
	
	/**Custom asynchronous processing handler*/
	private CustomTwitterAdapter customTwitterAdapter = new CustomTwitterAdapter();
	
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
		// Returns the top 30 trending topics for each day in the current week.
		TWITTER_CLIENT.getWeeklyTrendsAsync(this.customTwitterAdapter);
	}

	/**
	 * Test showing how to retrieve current trends
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void obtainsCurrentTrends() throws Exception {
		System.out.println("***************************************************************************");
		// Returns the current top 10 trending topics on Twitter.
		TWITTER_CLIENT.getCurrentTrendsAsync(this.customTwitterAdapter);
	}

	/**
	 * Test showing direct messages sent to the current authenticated user
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void obtainsDirectMessages() throws Exception {
		System.out.println("***************************************************************************");
		// Returns a list of the direct messages sent to the authenticating
		// user.
		TWITTER_CLIENT.getDirectMessagesAsync(this.customTwitterAdapter);
	}

	/**
	 * Test showing how obtains followers list
	 * 
	 * @throws java.lang.Exception
	 */
	@Test
	public void obtainsFollowers() throws Exception {
		System.out.println("***************************************************************************");
		// Returns the authenticating user's followers, each with current status
		// inline
		TWITTER_CLIENT.getFollowersAsync(this.customTwitterAdapter);
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
		System.out.println("Update status");
		TWITTER_CLIENT.updateStatusAsync("Update my status using Twitter4J API ;o)",this.customTwitterAdapter);
		// Reset status to initial value
		System.out.println("Update status");
		TWITTER_CLIENT.updateStatusAsync(cStatus,this.customTwitterAdapter);
	}	

}
