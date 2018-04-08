package twitteranalysis;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.TwitterException;
import static twitteranalysis.ConditionCheck.*;
import static twitteranalysis.SupervisedLearning.*;

/**
 *
 * @author Fraser
 */
public class TwitterAnalysis {

    public static ArrayList<EncodeChromosome> twitterConditions = new ArrayList();
    private static Scanner reader = new Scanner(System.in);  // Reading from System.in
    private static JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
    private static SocialMediaDB db = new SocialMediaDB(wr);

    public static void main(String[] args) {
        TwitterWrapper tw = new TwitterWrapper();

        while (true) {
            System.out.println("Admin mode or Twitter search mode? admin/search/exit");
            String adminInput = reader.next();

            switch (adminInput.toLowerCase()) {
                case "search":
                    // Print rate limit details
                    printRateLimitDetails(tw);
                    // Get Twitter account user input
                    boolean enterStatusClear = true;
                    List<Status> statuses = null;
                    do {
                        System.out.println("Enter the name of the twitter account you would like to examine:");
                        String user = reader.next();
                        System.out.println("Getting Statuses for... " + user);
                        statuses = tw.getStatuses(user);
                        enterStatusClear = statuses == null;
                    } while (enterStatusClear);
                    System.out.println("Total Statuses: " + statuses.size());

                    // List statuses
                    for (int i = 0; i < statuses.size(); i++) {
                        System.out.println(i + " --> " + cleanText(statuses.get(i).getText()));
                    }

                    // Ask which status to mine and get the replies
                    boolean enterReplyClear = true;
                    ArrayList<Status> replies = new ArrayList();
                    String statusIndexString = "";
                    while (enterReplyClear) {
                        System.out.println("Enter the number of the status you would like to data mine:");
                        statusIndexString = reader.next();
                        if (isStringInteger(statusIndexString)) {
                            enterReplyClear = false;
                        }
                    }
                    int statusIndex = Integer.parseInt(statusIndexString);
                    System.out.println("Total Replies: " + replies.size());
                    System.out.println("Getting replies for \"" + cleanText(statuses.get(statusIndex).getText() + "\""));

                    // Get Replies
                    replies = tw.getDiscussion(statuses.get(statusIndex));

                    // Loop through replies and set twitter variables appropriately
                    int replyCount = setTwitterDetails(replies, statuses, statusIndex);
                    System.out.println("Total replies gathered: " + replyCount);

                    // Ask user if they want to write twitter variables to DB
                    System.out.println("Would you like to write to DB? y/n");
                    String dbUserInput = reader.next();

                    if (dbUserInput.equals("y")) {
                        // Ask how many entries the use wants to write to DB
                        System.out.println("Enter the max entries you would like to write to the DB:");
                        int maxEntries = reader.nextInt();

                        // Ask if user would like to create a new table or update an existing one?
                        System.out.println("Would you like to create a new table or update an existing one? new/ update");
                        String newOrUpdate = reader.next();

                        // Ask user for table name/ txt file name
                        System.out.println("Enter your chosen file/ table name:");
                        String fileName = reader.next();

                        // Make new table if user enters 'new'
                        if (newOrUpdate.toLowerCase().equals("new")) {
                            db.createTable(fileName);
                        }
                        // // Write to txt file and DB for supervised learning
                        int oldMaxId = db.insertCondition(twitterConditions, fileName, maxEntries);
                        writeConditionsToTxt(fileName, oldMaxId, maxEntries);
                    } else {
                        System.out.println("Conditions have not been saved to the database");
                    }
                    break;
                case "admin":
                    // Ask user for table/ txt file name
                    System.out.println("Which table would you like to update with their classifiers?");
                    String fileInput = reader.next();
                    // Read from txt file
                    File file = new File("C:\\Users\\Fraser\\Google Drive\\GitProjects\\TwitterAnalysis\\src\\twitteranalysis\\SupervisedLearningTxt\\" + fileInput + ".txt");
                    HashMap<Integer, Integer> hmap = readConditionsFromTxt(file);
                    // Write to DB
                    db.insertClassifier(hmap, fileInput);
                    System.out.println("Successfully updated " + fileInput + " classifiers");
                    break;
                case "exit":
                    reader.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    public static boolean isStringInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void printRateLimitDetails(TwitterWrapper tw) {
        Map<String, RateLimitStatus> rateLimitStatus;
        try {
            rateLimitStatus = tw.getTwitter().getRateLimitStatus("search");

            RateLimitStatus searchTweetsRateLimit = rateLimitStatus.get("/search/tweets");
            System.out.printf("You have %d calls remaining out of %d, Limit resets in %d seconds\n",
                    searchTweetsRateLimit.getRemaining(),
                    searchTweetsRateLimit.getLimit(),
                    searchTweetsRateLimit.getSecondsUntilReset());
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String cleanText(String text) {
        text = text.replace("\n", "\\n");
        text = text.replace("\t", "\\t");

        return text;
    }

    public static int setTwitterDetails(ArrayList<Status> replies, List<Status> statuses, int statusIndex) {
        int count = 0;
        // Print replies
        for (int i = 0; i < replies.size(); i++) {
            if ((getCountryFromLocation(replies.get(i)) != null)) { // Only processes replies in which the location can be determined
                EncodeChromosome encode = new EncodeChromosome();

                System.out.println("OG status ID - " + statuses.get(statusIndex).getId());
                System.out.println("OG user name - " + statuses.get(statusIndex).getUser().getName());
                encode.setOgUserName(cleanText(statuses.get(statusIndex).getUser().getName().replace("'", "")));
                System.out.println("OG status - " + statuses.get(statusIndex).getText());
                encode.setOgStatus(cleanText(statuses.get(statusIndex).getText().replace("'", "")));

                System.out.println("Comment - " + replies.get(i).getText());
                encode.setComment(cleanText(replies.get(i).getText().replace("'", "")));

                System.out.println("hasSwear? - " + hasSwearWord(replies.get(i)));
                encode.setHasSwear(hasSwearWord(replies.get(i)));

                System.out.println("hasPositiveWord? - " + hasPositiveWord(replies.get(i)));
                encode.setHasPositiveWord(hasPositiveWord(replies.get(i)));

                System.out.println("hasNegativeWord? - " + hasNegativeWord(replies.get(i)));
                encode.setHasNegativeWord(hasNegativeWord(replies.get(i)));

                System.out.println("hasPositiveEmoji? - " + hasPositiveEmoji(replies.get(i)));
                encode.setHasPositiveEmoji(hasPositiveEmoji(replies.get(i)));

                System.out.println("hasNegativeEmoji? - " + hasNegativeEmoji(replies.get(i)));
                encode.setHasnegativeEmoji(hasNegativeEmoji(replies.get(i)));

                System.out.println("favouriteCount - " + replies.get(i).getFavoriteCount());
                encode.setFavouriteCount(replies.get(i).getFavoriteCount());

                System.out.println("followerscount - " + replies.get(i).getUser().getFollowersCount());
                encode.setFollowersCount(replies.get(i).getUser().getFollowersCount());

                System.out.println("friends count - " + replies.get(i).getUser().getFriendsCount());
                encode.setFriendCount(replies.get(i).getUser().getFriendsCount());

                System.out.println("location - " + replies.get(i).getUser().getLocation());
                encode.setLocation(getCountryFromLocation(replies.get(i)));
                System.out.println(getCountryFromLocation(replies.get(i)));

                System.out.println("is verified? - " + replies.get(i).getUser().isVerified());
                encode.setIsVerified(replies.get(i).getUser().isVerified());

                System.out.println("===============================================");

                twitterConditions.add(encode);
                count++;
            }
        }
        return count;
    }
}
