package backend;

import java.util.Collection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;

/**
 *
 * Simple services provider for the communication between Frontend and Google Talk Server
 * using XMPP protocol with SMACK API
 *
 * This class is a Java POJO
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 */
public abstract class ServicesProvider {

    /**
     * Method to obtains the buddies list of a account
     * @param login Account login
     * @param password Account password
     * @return the buddies list
     * @throws java.lang.Exception
     */
    public static RosterEntry[] obtainsBuddies(String login, String password) throws Exception  {
        RosterEntry[] buddies = null;
        XMPPConnection connection = null;

        try {
            //Connect to GTalk server
            ConnectionConfiguration config =
                    new ConnectionConfiguration("talk.google.com",
                    5222,
                    "gmail.com");
            connection = new XMPPConnection(config);
            connection.connect();
            connection.login(login, password);

            //Get the buddies list
            Collection<RosterEntry> rosters = connection.getRoster().getEntries();
            buddies = new RosterEntry[rosters.size()];
            int i = 0;
            for (RosterEntry buddy : rosters) {
                buddies[i++] = buddy;
            }
        } finally {
            //Disconnect
            if (connection != null) {
                connection.disconnect();
            }
        }

        //Return buddies list
        return buddies;
    }
}
