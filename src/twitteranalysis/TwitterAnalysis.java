package twitteranalysis;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.TwitterException;
//import twitter4j.*;

/**
 *
 * @author Fraser
 */
public class TwitterAnalysis {

    public static void main(String[] args) {
        TwitterWrapper tw = new TwitterWrapper();
        try {
            System.out.println(tw.getTwitter().getRateLimitStatus());
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
        String user = "kyliejenner";

        System.out.println("Getting Statuses for... " + user);
        ArrayList<Status> statuses = tw.getStatuses(user);
        System.out.println("Total Statuses: " + statuses.size());

        // Print first status
        System.out.println("OG Status: " + statuses.get(0).getText() + "\n============================================");

        // Get replies
        System.out.println("Getting replies for most recent status...");
        ArrayList<Status> replies = tw.getDiscussion(statuses.get(0));
        System.out.println("Total Replies: " + replies.size());

        // Print replies
        for (int i = 0; i < replies.size(); i++) {
            if ((getContinentFromLocation(replies.get(i)) != null) || (replies.get(i).getGeoLocation() != null)) {

                System.out.println("OG status ID - " + statuses.get(0).getId());
                System.out.println("OG user ID - " + statuses.get(0).getUser().getName());
                System.out.println("ID - " + replies.get(i).getUser().getId());
                System.out.println("Name - " + replies.get(i).getUser().getName());
                System.out.println("Screen Name - " + replies.get(i).getUser().getScreenName());
                System.out.println("Comment - " + replies.get(i).getText());
                System.out.println("hasSwear? - " + hasSwearWord(replies.get(i)));
                System.out.println("hasPositiveWord? - " + hasPositiveWord(replies.get(i)));
                System.out.println("hasNegativeWord? - " + hasNegativeWord(replies.get(i)));
                System.out.println("hasPositiveEmoji? - " + hasPositiveEmoji(replies.get(i)));
                System.out.println("hasNegativeEmoji? - " + hasNegativeEmoji(replies.get(i)));
                System.out.println("TimeZone - " + replies.get(i).getUser().getTimeZone());
                System.out.println("Lang - " + replies.get(i).getUser().getLang());
                System.out.println("Created at - " + replies.get(i).getUser().getCreatedAt());
                System.out.println("favouriteCount - " + replies.get(i).getFavoriteCount());
                System.out.println("followerscount - " + replies.get(i).getUser().getFollowersCount());
                System.out.println("friends count - " + replies.get(i).getUser().getFriendsCount());
                System.out.println("location - " + replies.get(i).getUser().getLocation());
                System.out.println("GeoLocation - " + replies.get(i).getGeoLocation());
                System.out.println(getContinentFromLocation(replies.get(i)));
//            if ((getContinentFromLocation(replies.get(i)) != null) || (replies.get(i).getGeoLocation() != null)) {
//                System.out.println("Can't find country from location");
//            } else {
//                System.out.println(getContinentFromLocation(replies.get(i)));
//            }
                System.out.println("status count - " + replies.get(i).getUser().getStatusesCount());
                System.out.println("is verified? - " + replies.get(i).getUser().isVerified());
                System.out.println("===============================================");
            }
        }
    }

    public static Country getContinentFromLocation(Status reply) {
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        ArrayList<Country> countries = new ArrayList();
        countries.addAll(db.getAllCountries());

        for (int i = 0; i < countries.size(); i++) {
            if (reply.getUser().getLocation().contains(countries.get(i).getCountry())) {
                return countries.get(i);
            }
        }
        return null;
    }

    public static boolean hasPositiveWord(Status reply) {
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        ArrayList<String> positiveWords = new ArrayList();
        positiveWords.addAll(db.getAllPositiveWords());

        // Remove tag
        Scanner sc = new Scanner(reply.getText());
        String tag = sc.next();
        String comment = reply.getText().replace(tag, "");

        for (int i = 0; i < positiveWords.size(); i++) {
            if (comment.contains(positiveWords.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNegativeWord(Status reply) {
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        ArrayList<String> negativeWords = new ArrayList();
        negativeWords.addAll(db.getAllNegativeWords());

        // Remove tag
        Scanner sc = new Scanner(reply.getText());
        String tag = sc.next();
        String comment = reply.getText().replace(tag, "");

        for (int i = 0; i < negativeWords.size(); i++) {
            if (comment.contains(negativeWords.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPositiveEmoji(Status reply) {
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        ArrayList<String> positiveEmoji = new ArrayList();
        positiveEmoji.addAll(db.getAllPositiveEmojis());

        for (int i = 0; i < positiveEmoji.size(); i++) {
            if (reply.getText().contains(positiveEmoji.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasNegativeEmoji(Status reply) {
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        ArrayList<String> negativeEmoji = new ArrayList();
        negativeEmoji.addAll(db.getAllNegativeEmojis());

        for (int i = 0; i < negativeEmoji.size(); i++) {
            if (reply.getText().contains(negativeEmoji.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSwearWord(Status reply) {
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        ArrayList<String> swears = new ArrayList();
        swears.addAll(db.getAllSwears());

        for (int i = 0; i < swears.size(); i++) {
            if (reply.getText().contains(swears.get(i))) {
                return true;
            }
        }
        return false;
    }
}
