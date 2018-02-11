package twitteranalysis;

import facebook4j.*;
import facebook4j.conf.ConfigurationBuilder;
import java.sql.*;
import java.util.ArrayList;
import twitter4j.Status;
import twitteranalysis.Genderize.GenderizeIoAPI;
import twitteranalysis.Genderize.client.Genderize;
import twitteranalysis.Genderize.model.NameGender;
//import twitter4j.*;

/**
 *
 * @author Fraser
 */
public class TwitterAnalysis {

    public static void main(String[] args) {
        TwitterWrapper tw = new TwitterWrapper();
        String user = "KylieJenner";

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
            System.out.println("ID - " + replies.get(i).getUser().getId());
            System.out.println("Name - " + replies.get(i).getUser().getName());
            System.out.println("Screen Name - " + replies.get(i).getUser().getScreenName());
            System.out.println("Comment - " + replies.get(i).getText());
            System.out.println("hasSwear? - " + hasSwearWord(replies.get(i)));
            System.out.println("hasPositiveWord? - " + hasPositiveWord(replies.get(i)));
            System.out.println("hasNegativeWord? - " + hasNegativeWord(replies.get(i)));
            System.out.println("hasPositiveEmoji? - "+ hasPositiveEmoji(replies.get(i)));
            System.out.println("TimeZone - " + replies.get(i).getUser().getTimeZone());
            System.out.println("Lang - " + replies.get(i).getUser().getLang());
            System.out.println("Created at - " + replies.get(i).getUser().getCreatedAt());
            System.out.println("favouriteCount - " + replies.get(i).getFavoriteCount());
            System.out.println("followerscount - " + replies.get(i).getUser().getFollowersCount());
            System.out.println("friends count - " + replies.get(i).getUser().getFriendsCount());
            System.out.println("location - " + replies.get(i).getUser().getLocation());
            System.out.println("status count - " + replies.get(i).getUser().getStatusesCount());
            System.out.println("is verified? - " + replies.get(i).getUser().isVerified());
            System.out.println("===============================================");
        }
    }

    public static boolean hasPositiveWord(Status reply) {
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        ArrayList<String> positiveWords = new ArrayList();
        positiveWords.addAll(db.getAllPositiveWords());

        for (int i = 0; i < positiveWords.size(); i++) {
            if (reply.getText().contains(positiveWords.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPositiveEmoji(Status reply) {
        if (reply.getText().contains("😀")) {
            return true;
        } else if (reply.getText().contains("😁")) {
            return true;
        } else if(reply.getText().contains("🤣")) {
            return true;
        } else if(reply.getText().contains("😃")) {
            return true;
        } else if (reply.getText().contains("😄")) {
            return true;
        } else if (reply.getText().contains("😅")) {
            return true;
        } else if (reply.getText().contains("😆")) {
            return true;
        } else if (reply.getText().contains("😉")) {
            return true;
        } else if (reply.getText().contains("😊")) {
            return true;
        } else if (reply.getText().contains("😋")) {
            return true;
        } else if (reply.getText().contains("😎")) {
            return true;
        } else if (reply.getText().contains("😍")) {
            return true;
        } else if (reply.getText().contains("😘")) {
            return true;
        } else if (reply.getText().contains("😗")) {
            return true;
        } else if (reply.getText().contains("😙")) {
            return true;
        } else if (reply.getText().contains("😚")) {
            return true;
        }else if (reply.getText().contains("☺")) {
            return true;
        } else if (reply.getText().contains("🙂")) {
            return true;
        } else if (reply.getText().contains("🤗")) {
            return true;
        } else if (reply.getText().contains("🤩")) {
            return true;
        } else if (reply.getText().contains("😛")) {
            return true;
        } else if (reply.getText().contains("😜")) {
            return true;
        } else if (reply.getText().contains("😝")) {
            return true;
        } else if (reply.getText().contains("😇")) {
            return true;
        } else if (reply.getText().contains("🤠")) {
            return true;
        } else if (reply.getText().contains("😺")) {
            return true;
        } else if (reply.getText().contains("😸")) {
            return true;
        } else if (reply.getText().contains("😻")) {
            return true;
        } else if (reply.getText().contains("💋")) {
            return true;
        } else if (reply.getText().contains("💘")) {
            return true;
        } else if (reply.getText().contains("❤")) {
            return true;
        } else if (reply.getText().contains("💓")) {
            return true;
        } else if (reply.getText().contains("💕")) {
            return true;
        } else if (reply.getText().contains("💖")) {
            return true;
        } else if (reply.getText().contains("💗")) {
            return true;
        } else if (reply.getText().contains("💙")) {
            return true;
        } else if (reply.getText().contains("💚")) {
            return true;
        } else if (reply.getText().contains("💛")) {
            return true;
        } else if (reply.getText().contains("🧡")) {
            return true;
        } else if (reply.getText().contains("💜")) {
            return true;
        } else if (reply.getText().contains("🖤")) {
            return true;
        } else if (reply.getText().contains("💝")) {
            return true;
        } else if (reply.getText().contains("💞")) {
            return true;
        } else if (reply.getText().contains("💟")) {
            return true;
        } else if (reply.getText().contains("❣")) {
            return true;
        } else if (reply.getText().contains("💌")) {
            return true;
        } else if (reply.getText().contains("✌")) {
            return true;
        } else if (reply.getText().contains("👍")) {
            return true;
        } else if (reply.getText().contains("👍🏻")) {
            return true;
        } else if (reply.getText().contains("👍🏼")) {
            return true;
        } else if (reply.getText().contains("👍🏽")) {
            return true;
        } else if (reply.getText().contains("👍🏾")) {
            return true;
        } else if (reply.getText().contains("👍🏿")) {
            return true;
        } 
        return false;
    }

    public static boolean hasNegativeWord(Status reply) {
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        ArrayList<String> negativeWords = new ArrayList();
        negativeWords.addAll(db.getAllNegativeWords());

        for (int i = 0; i < negativeWords.size(); i++) {
            if (reply.getText().contains(negativeWords.get(i))) {
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
