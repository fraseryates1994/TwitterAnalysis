package twitteranalysis;


import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.User;
import java.util.ArrayList;
import java.util.List;
//import twitter4j.*;

/**
 *
 * @author Fraser
 */
public class TwitterAnalysis {

    public static void main(String[] args) {
//        TwitterWrapper tw = new TwitterWrapper();
//        String user = "BBCWorld";
//        ArrayList<Status> statuses = tw.getStatuses(user);
//
//        System.out.println("Total Statuses: " + statuses.size());
//
//        // Print first status
//        System.out.println("OG Status: " + statuses.get(0).getText() + "\n============================================");
//
//        // Get replies
//        ArrayList<Status> replies = tw.getDiscussion(statuses.get(0));
//
//        // Print replies
//        for (int i = 0; i < replies.size(); i++) {
//            System.out.print(replies.get(i).getUser().getName());
//            System.out.print(" - " + replies.get(i).getText());
//            System.out.print("\n");
//        }
        
        String accessToken = "EAACEdEose0cBADijKO8y9gN0S7GdYG1PfOoajZAAyi52g7TaH1jjA1UGzLfXAChNXsWWC4vndS1sq8PGptYpiZB76G8XRyXef5TAsni9ERofZAbSTZAT4To9WuaZAphW1Qk7toOMkKKIuxcMqgHnF7L7kyo2FgSdIX66D0pwtJTpA3pJ6CyM83ssZAI5nduWJZBlhMOiUPP9QZDZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);

        Page page = fbClient.fetchObject("BillGates", Page.class);
        
        Connection<Post> postFeed = fbClient.fetchConnection(page.getId() + "/feed", Post.class);
        
        for (List<Post> postPage : postFeed) {
            for (Post aPost : postPage) {
                System.out.println(aPost.getFrom().getName());
            }
        }
    }   
}
