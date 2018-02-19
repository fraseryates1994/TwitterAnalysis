package twitteranalysis;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.TwitterException;
//import twitter4j.*;

/**
 *
 * @author Fraser
 */
public class TwitterAnalysis {

    public static ArrayList<TwitterToDB> twitterConditions = new ArrayList();

    public static void main(String[] args) {
        TwitterWrapper tw = new TwitterWrapper();
        try {
            Map<String, RateLimitStatus> rateLimitStatus = tw.getTwitter().getRateLimitStatus("search");
            RateLimitStatus searchTweetsRateLimit = rateLimitStatus.get("/search/tweets");
            System.out.printf("You have %d calls remaining out of %d, Limit resets in %d seconds\n",
                    searchTweetsRateLimit.getRemaining(),
                    searchTweetsRateLimit.getLimit(),
                    searchTweetsRateLimit.getSecondsUntilReset());
            
            String user = "kyliejenner";

            System.out.println("Getting Statuses for... " + user);
            ArrayList<Status> statuses = tw.getStatuses(user);
            System.out.println("Total Statuses: " + statuses.size());

            // Print first status
            System.out.println("OG Status: " + statuses.get(1).getText() + "\n============================================");

            // Get replies
            System.out.println("Getting replies for most recent status...");
            ArrayList<Status> replies = tw.getDiscussion(statuses.get(1));
            System.out.println("Total Replies: " + replies.size());

            // Print replies
            for (int i = 0; i < replies.size(); i++) {
                if ((getCountryFromLocation(replies.get(i)) != null)) {
                    TwitterToDB tu = new TwitterToDB();

                    System.out.println("OG status ID - " + statuses.get(1).getId());
                    System.out.println("OG user name - " + statuses.get(1).getUser().getName());
                    tu.setOgUserName(statuses.get(1).getUser().getName().replace("'", ""));
                    System.out.println("OG status - " + statuses.get(1).getText());
                    tu.setOgStatus(statuses.get(1).getText().replace("'", ""));
                    System.out.println("Comment - " + replies.get(i).getText());
                    tu.setComment(replies.get(i).getText().replace("'", ""));
                    System.out.println("hasSwear? - " + hasSwearWord(replies.get(i)));
                    tu.setHasSwear(hasSwearWord(replies.get(i)));
                    System.out.println("hasPositiveWord? - " + hasPositiveWord(replies.get(i)));
                    tu.setHasPositiveWord(hasPositiveWord(replies.get(i)));
                    System.out.println("hasNegativeWord? - " + hasNegativeWord(replies.get(i)));
                    tu.setHasNegativeWord(hasNegativeWord(replies.get(i)));
                    System.out.println("hasPositiveEmoji? - " + hasPositiveEmoji(replies.get(i)));
                    tu.setHasPositiveEmoji(hasPositiveEmoji(replies.get(i)));
                    System.out.println("hasNegativeEmoji? - " + hasNegativeEmoji(replies.get(i)));
                    tu.setHasnegativeEmoji(hasNegativeEmoji(replies.get(i)));
                    System.out.println("favouriteCount - " + replies.get(i).getFavoriteCount());
                    tu.setFavouriteCount(replies.get(i).getFavoriteCount());
                    System.out.println("followerscount - " + replies.get(i).getUser().getFollowersCount());
                    tu.setFollowersCount(replies.get(i).getUser().getFollowersCount());
                    System.out.println("friends count - " + replies.get(i).getUser().getFriendsCount());
                    tu.setFriendCount(replies.get(i).getUser().getFriendsCount());
                    System.out.println("location - " + replies.get(i).getUser().getLocation());
                    tu.setLocation(getCountryFromLocation(replies.get(i)));
                    System.out.println(getCountryFromLocation(replies.get(i)));
                    System.out.println("is verified? - " + replies.get(i).getUser().isVerified());
                    tu.setIsVerified(replies.get(i).getUser().isVerified());
                    System.out.println("===============================================");

                    twitterConditions.add(tu);
                }
            }
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            System.out.println("Would you like to write to DB? y/n");
            String input = reader.next();
            if (input.equals("y")) {
                JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
                SocialMediaDB db = new SocialMediaDB(wr);
                db.insertCondition(twitterConditions, "CONDITIONS");
                System.out.println("Write successful");
                reader.close();
            } else {
                System.out.println("Conditions have not been saved to the database");
            }

        } catch (TwitterException ex) {
            Logger.getLogger(TwitterAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Country getCountryFromLocation(Status reply) {
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        ArrayList<Country> countries = new ArrayList();
        ArrayList<State> states = new ArrayList();

        countries.addAll(db.getAllCountries());
        states.addAll(db.getAllStates());
        String locationCaps = reply.getUser().getLocation().toUpperCase();

        // Evaluate country nicknames/ special cases
        if (locationCaps.contains("ENGLAND") || locationCaps.contains("SCOTLAND") || locationCaps.contains("WALES") || locationCaps.contains("NORTHEN IRELAND")) {
            return db.getCountry("GB");
        } else if (locationCaps.contains("NIGERIA")) {
            return db.getCountry("NG");
        } else if (locationCaps.contains("USA") || locationCaps.contains("UNITED STATES")) {
            return db.getCountry("US");
        } else if (locationCaps.contains("ANTIGUA") || locationCaps.contains("BARBUDA")) {
            return db.getCountry("AG");
        } else if (locationCaps.contains("BOSNIA")) {
            return db.getCountry("BA");
        } else if (locationCaps.contains("COCOS")) {
            return db.getCountry("CC");
        } else if (locationCaps.contains("SOUTH KOREA")) {
            return db.getCountry("KR");
        } else if (locationCaps.contains("RUSSIA")) {
            return db.getCountry("RU");
        } else if (locationCaps.contains("TRINIDAD") || locationCaps.contains("TOBAGO")) {
            return db.getCountry("TT");
        } else if (locationCaps.contains("UAE")) {
            return db.getCountry("AE");
        }

        // evaluate US states
        for (int i = 0; i < states.size(); i++) {
            if (locationCaps.contains(states.get(i).getStateName().toUpperCase())) {
                return db.getCountry("US");
            }
        }

        // evaluate Countries
        for (int i = 0; i < countries.size(); i++) {
            if (locationCaps.contains(countries.get(i).getCountry().toUpperCase())) {
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
