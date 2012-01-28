package be.hikage.xml.rome;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.WireFeedOutput;
import junit.framework.TestCase;
import org.xml.sax.InputSource;

import java.util.Collections;
import java.util.Date;

/**
 * @Autor : Hikage
 */
public class RomeTest extends TestCase {
    private SyndFeed feed;
    private WireFeedOutput output;

    protected void setUp() throws Exception {
        this.feed = createFeed();
        this.output = new WireFeedOutput();


    }


    public void testWriteRss2() {
        WireFeed wireFeed = this.feed.createWireFeed("rss_2.0");
        outputFeed(wireFeed);

    }

    public void testWriteRss1() {
        WireFeed wireFeed = this.feed.createWireFeed("rss_1.0");
        outputFeed(wireFeed);

    }


    public void testWriteAtom1() {
        WireFeed wireFeed = this.feed.createWireFeed("atom_1.0");
        outputFeed(wireFeed);
    }

    public void testReadRss2() throws FeedException {
        SyndFeedInput input = new SyndFeedInput();

        SyndFeed result = input.build(new InputSource(getClass().getResourceAsStream("/be/hikage/xml/rome/rss_2.0.xml")));

        assertEquals("RSS Veille Techno", result.getTitle());
        assertEquals("http://svn.cyg.be/", result.getLink());
        assertEquals(1, result.getEntries().size());
    }

    public void testReadRss1() throws FeedException {
        SyndFeedInput input = new SyndFeedInput();

        SyndFeed result = input.build(new InputSource(getClass().getResourceAsStream("/be/hikage/xml/rome/rss_1_0.xml")));

        assertEquals("RSS Veille Techno", result.getTitle());
        assertEquals("http://svn.cyg.be/", result.getLink());
        assertEquals(1, result.getEntries().size());
    }

    public void testReadAtom1() throws FeedException {
        SyndFeedInput input = new SyndFeedInput();

        SyndFeed result = input.build(new InputSource(getClass().getResourceAsStream("/be/hikage/xml/rome/atom_1.0.xml")));

        assertEquals("RSS Veille Techno", result.getTitle());
        assertEquals("http://svn.cyg.be/", result.getLink());
        assertEquals(1, result.getEntries().size());
    }

    public void outputFeed(WireFeed feed) {
        try {
            System.out.println(feed.getFeedType());
            System.out.println(output.outputString(feed));
        } catch (FeedException e) {

        }
    }


    private SyndFeed createFeed() {

        SyndFeed feed = new SyndFeedImpl();

        SyndPerson auteur = new SyndPersonImpl();
        auteur.setName("Gildas Cuisinier");
        auteur.setEmail("hikage@hikage.be");

        feed.setTitle("RSS Veille Techno");
        feed.setAuthors(Collections.singletonList(auteur));
        feed.setDescription("RSS d'exemple !");
        feed.setLink("http://svn.cyg.be/");
        feed.setPublishedDate(new Date());
        feed.setLanguage("fr");

        SyndEntry entry = new SyndEntryImpl();
        entry.setTitle("Ajout du projet Rome sur le SVN");
        entry.setLink("https://rome.dev.java.net/");

        SyndContent description = new SyndContentImpl();
        description.setValue("Ajout d'un projet Rome sur le SVN afin de voir comment creer un RSS");
        description.setType("text");
        entry.setDescription(description);
        entry.setAuthors(Collections.singletonList(auteur));

        feed.getEntries().add(entry);


        return feed;


    }
}
