package twitteranalysis;

import facebook4j.*;
import facebook4j.conf.ConfigurationBuilder;
import java.sql.*;
import java.util.ArrayList;
import twitter4j.Status;
//import twitter4j.*;

/**
 *
 * @author Fraser
 */
public class TwitterAnalysis {

    public static void main(String[] args) {
        hasSwearWord();
        TwitterWrapper tw = new TwitterWrapper();
        String user = "KimKardashian";
        ArrayList<Status> statuses = tw.getStatuses(user);

        System.out.println("Total Statuses: " + statuses.size());

        // Print first status
        System.out.println("OG Status: " + statuses.get(0).getText() + "\n============================================");
        System.out.println("OG Status: ");

        // Get replies
        ArrayList<Status> replies = tw.getDiscussion(statuses.get(0));

        // Print replies
        for (int i = 0; i < replies.size(); i++) {
            System.out.println("Comment - " + replies.get(i).getText());
            System.out.println("TimeZone - " + replies.get(i).getUser().getTimeZone());
            System.out.println("Lang - " + replies.get(i).getUser().getLang());
            System.out.println("Created at - " + replies.get(i).getUser().getCreatedAt());
            System.out.println("Name - " + replies.get(i).getUser().getName());
            System.out.println("Screen Name - " + replies.get(i).getUser().getScreenName());
            System.out.println("ID - " + replies.get(i).getUser().getId());
            System.out.println("favouriteCount - " + replies.get(i).getFavoriteCount());
            System.out.println("followerscount - " + replies.get(i).getUser().getFollowersCount());
            System.out.println("friends count - " + replies.get(i).getUser().getFriendsCount());
            System.out.println("location - " + replies.get(i).getUser().getLocation());
            System.out.println("status count - " + replies.get(i).getUser().getStatusesCount());
            System.out.println("is verified? - " + replies.get(i).getUser().isVerified());
            System.out.println("===============================================");
        }
    }

    public static void hasSwearWord() {
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver","jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        System.out.println(db.getAllSwears());
    }
}
