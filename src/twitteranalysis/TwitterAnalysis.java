package twitteranalysis;

import java.util.ArrayList;
import java.util.List;
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
        ArrayList<Status> statuses = new ArrayList();

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
        System.out.println("Total Statuses: " + statuses.size());

        // Print first status
        System.out.println("OG Status: "+ statuses.get(0).getText() +"\n============================================");
        
        // Get replies
        ArrayList<Status> replies = getDiscussion(statuses.get(0), twitter);

        // Print replies
        for (int i = 0; i < replies.size(); i++) {
            System.out.print(replies.get(i).getUser().getName());
            System.out.print(" - " + replies.get(i).getText());
            System.out.print("\n");
        }
    }

    static public ArrayList<Status> getDiscussion(Status status, Twitter twitter) {
        ArrayList<Status> replies = new ArrayList<>();
        ArrayList<Status> all = null;

        try {
            long id = status.getId();
            String screenname = status.getUser().getScreenName();

            Query query = new Query("@" + screenname + " since_id:" + id);

            try {
                query.setCount(100);
            } catch (Throwable e) {
                // enlarge buffer error?
                query.setCount(30);
            }

            QueryResult result = twitter.search(query);
            all = new ArrayList<Status>();

            do {
                List<Status> tweets = result.getTweets();

                for (Status tweet : tweets) {
                    if (tweet.getInReplyToStatusId() == id) {
                        all.add(tweet);
                    }
                }

                if (all.size() > 0) {
                    for (int i = all.size() - 1; i >= 0; i--) {
                        replies.add(all.get(i));
                    }
                    all.clear();
                }

                query = result.nextQuery();

                if (query != null) {
                    result = twitter.search(query);
                }

            } while (query != null);

        } catch (Exception | OutOfMemoryError e) {
            e.printStackTrace();
        }
        return replies;
    }
}
