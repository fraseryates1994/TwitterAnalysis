package twitteranalysis;

import facebook4j.*;
import facebook4j.conf.ConfigurationBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
//import twitter4j.*;

/**
 *
 * @author Fraser
 */
public class TwitterAnalysis {

    public static void main(String[] args) {
        TwitterWrapper tw = new TwitterWrapper();
        String user = "BBCWorld";
        ArrayList<Status> statuses = tw.getStatuses(user);

        System.out.println("Total Statuses: " + statuses.size());

        // Print first status
        System.out.println("OG Status: " + statuses.get(0).getText() + "\n============================================");

        // Get replies
        ArrayList<Status> replies = tw.getDiscussion(statuses.get(0));

        // Print replies
        for (int i = 0; i < replies.size(); i++) {
            System.out.print(replies.get(i).getUser().getName());
            System.out.print(" - " + replies.get(i).getText());
            System.out.print("\n");
        }
    }
}
