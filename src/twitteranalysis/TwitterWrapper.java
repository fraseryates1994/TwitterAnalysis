package twitteranalysis;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Fraser
 */
public class TwitterWrapper {

    private Twitter twitter = null;

    public TwitterWrapper() {
        ConfigurationBuilder cb = new ConfigurationBuilder();

        cb.setOAuthConsumerKey("DAFPH7ewKnEBXnLDGJny9KUlE");
        cb.setOAuthConsumerSecret("lAepJAzJumublFyniqfxSsUIcf8etgjbrPQOSfWNT4kZ6o54ri");
        cb.setOAuthAccessToken("945834119187128321-HWTcx9UffcoQs8fDN8WfBlGDbGSBpoe");
        cb.setOAuthAccessTokenSecret("3FHblIHb0HZHJb3xdCgz6n3232QhGRzCEN4PdwKMq5YEj");
        this.twitter = new TwitterFactory(cb.build()).getInstance();
    }

    public Twitter getTwitter() {
        return twitter;
    }
    
    public ArrayList<Status> getStatuses (String user) {
        int pageno = 1;
        ArrayList<Status> statuses = new ArrayList();

        while (true) {
            try {
                int size = statuses.size();
                Paging page = new Paging(pageno++, 100);
                statuses.addAll(getTwitter().getUserTimeline(user, page));
                if (statuses.size() == size) {
                    break;
                }
            } catch (TwitterException e) {
                System.out.println("Get Statuses Exception");
            }
        }
        return statuses;
    }
    
    public ArrayList<Status> getDiscussion(Status status) {
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
            System.out.println("Get Replies Exception");
        }
        return replies;
    }
}
