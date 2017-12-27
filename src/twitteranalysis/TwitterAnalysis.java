package twitteranalysis;

import java.util.ArrayList;
import twitter4j.*;
import twitter4j.conf.*;

/**
 *
 * @author Fraser
 */
public class TwitterAnalysis {

    public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setOAuthConsumerKey("DAFPH7ewKnEBXnLDGJny9KUlE");
        cb.setOAuthConsumerSecret("lAepJAzJumublFyniqfxSsUIcf8etgjbrPQOSfWNT4kZ6o54ri");
        cb.setOAuthAccessToken("945834119187128321-HWTcx9UffcoQs8fDN8WfBlGDbGSBpoe");
        cb.setOAuthAccessTokenSecret("3FHblIHb0HZHJb3xdCgz6n3232QhGRzCEN4PdwKMq5YEj");

        Twitter twitter = new TwitterFactory(cb.build()).getInstance();

        int pageno = 1;
        String user = "cnn";
        ArrayList statuses = new ArrayList();

        while (true) {
            try {
                int size = statuses.size();
                Paging page = new Paging(pageno++, 100);
                statuses.addAll(twitter.getUserTimeline(user, page));
                if (statuses.size() == size) {
                    break;
                }
            } catch (TwitterException e) {

                e.printStackTrace();
            }
        }
        System.out.println("Total: " + statuses.size());
    }

}

