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
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        ConfigurationBuilder cb = new ConfigurationBuilder();
        String consumerKey =db.getKey("OAuthConsumerKey").getKayValue();
        String consumerSecret = db.getKey("OAuthConsumerSecret").getKayValue();
        String accessToken = db.getKey("OAuthAccessTokenTwitter").getKayValue(); 
        String accessTokenSecret = db.getKey("OAuthAccessTokenSecret").getKayValue();
        
        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuthAccessToken(accessToken);
        cb.setOAuthAccessTokenSecret(accessTokenSecret);
        this.twitter = new TwitterFactory(cb.build()).getInstance();
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public ArrayList<Status> getStatuses(String user) {
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
                e.printStackTrace();
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

        System.out.println("query string: " + query.getQuery());

        try {
            query.setCount(100);
        } catch (Throwable e) {
            // enlarge buffer error?
            query.setCount(30);
        }

        QueryResult result = twitter.search(query);
        System.out.println("result: " + result.getTweets().size());

        all = new ArrayList<Status>();

        do {

            List<Status> tweets = result.getTweets();

            for (Status tweet : tweets)
                if (tweet.getInReplyToStatusId() == id)
                    all.add(tweet);

            if (all.size() > 0) {
                for (int i = all.size() - 1; i >= 0; i--)
                    replies.add(all.get(i));
                all.clear();
            }

            query = result.nextQuery();

            if (query != null)
                result = twitter.search(query);

        } while (query != null);

    } catch (Exception e) {
        e.printStackTrace();
    } catch (OutOfMemoryError e) {
        e.printStackTrace();
    }
    return replies;
}

//    public ArrayList<Status> getDiscussion(Status status) {
//        ArrayList<Status> replies = new ArrayList<>();
//
//        try {
//            long tweetID = status.getId();
//            String screenName = status.getUser().getScreenName();
//            Query query = new Query("@" + screenName + " since_id:" + tweetID);
//            QueryResult results;
//
//            do {
//                results = twitter.search(query);
//                List<Status> tweets = results.getTweets();
//
//                for (Status tweet : tweets) {
//                    if (tweet.getInReplyToStatusId() == tweetID) {
//                        replies.add(tweet);
//                    }
//                }
//            } while ((query = results.nextQuery()) != null);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return replies;
//    }
}
