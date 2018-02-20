package twitteranalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private static Scanner reader = new Scanner(System.in);  // Reading from System.in

    public static void main(String[] args) {
        TwitterWrapper tw = new TwitterWrapper();
        System.out.println("Admin mode or Twitter search mode? admin/search");
        String adminInput = reader.next();

        if (adminInput.toLowerCase().equals("search")) {
            try {
                Map<String, RateLimitStatus> rateLimitStatus = tw.getTwitter().getRateLimitStatus("search");
                RateLimitStatus searchTweetsRateLimit = rateLimitStatus.get("/search/tweets");
                System.out.printf("You have %d calls remaining out of %d, Limit resets in %d seconds\n",
                        searchTweetsRateLimit.getRemaining(),
                        searchTweetsRateLimit.getLimit(),
                        searchTweetsRateLimit.getSecondsUntilReset());

                System.out.println("Enter the name of the twitter account you would like to examine:");
                String user = reader.next();

                System.out.println("Getting Statuses for... " + user);
                List<Status> statuses = tw.getStatuses(user);
                System.out.println("Total Statuses: " + statuses.size());

                System.out.println("Enter which status you would like to data mine:");
                int statusIndex = reader.nextInt();

                // Print first status
                System.out.println("OG Status: " + statuses.get(statusIndex - 1).getText() + "\n============================================");

                // Get replies
                System.out.println("Getting replies for most recent status...");
                ArrayList<Status> replies = tw.getDiscussion(statuses.get(statusIndex - 1));
                System.out.println("Total Replies: " + replies.size());

                int count = 0;
                // Print replies
                for (int i = 0; i < replies.size(); i++) {
                    if ((getCountryFromLocation(replies.get(i)) != null)) {
                        TwitterToDB tu = new TwitterToDB();

                        System.out.println("OG status ID - " + statuses.get(statusIndex - 1).getId());
                        tu.setId(count);
                        System.out.println("OG user name - " + statuses.get(statusIndex - 1).getUser().getName());
                        tu.setOgUserName(cleanText(statuses.get(statusIndex - 1).getUser().getName().replace("'", "")));
                        System.out.println("OG status - " + statuses.get(statusIndex - 1).getText());
                        tu.setOgStatus(cleanText(statuses.get(statusIndex - 1).getText().replace("'", "")));
                        System.out.println("Comment - " + replies.get(i).getText());
                        tu.setComment(cleanText(replies.get(i).getText().replace("'", "")));
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
                        count++;
                    }
                }
                System.out.println("Would you like to write to DB? y/n");
                String dbUserInput = reader.next();
                if (dbUserInput.equals("y")) {
                    // Write to txt file for supervised learning 
                    System.out.println("Enter the table name/ DB name:");
                    String fileName = reader.next();
                    File file = new File("C:\\Users\\Fraser\\Google Drive\\GitProjects\\TwitterAnalysis\\src\\twitteranalysis\\SupervisedLearningTxt\\" + fileName + ".txt");
                    writeConditionsToTxt(file);

                    // Write to DB
                    JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
                    SocialMediaDB db = new SocialMediaDB(wr);

                    // Write to txt file for supervised learning 
                    db.createTable(fileName);
                    db.insertCondition(twitterConditions, fileName);
                } else {
                    System.out.println("Conditions have not been saved to the database");
                    reader.close();
                }
            } catch (TwitterException ex) {
                Logger.getLogger(TwitterAnalysis.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (adminInput.toLowerCase().equals("admin")) {
            System.out.println("Which table would you like to update with their classifiers?");
            String fileInput = reader.next();

            // Read from txt file
            File file = new File("C:\\Users\\Fraser\\Google Drive\\GitProjects\\TwitterAnalysis\\src\\twitteranalysis\\SupervisedLearningTxt\\" + fileInput + ".txt");
            HashMap<Integer, Integer> hmap = readConditionsFromTxt(file);

            // Write to DB
            JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
            SocialMediaDB db = new SocialMediaDB(wr);
            db.insertClassifier(hmap, fileInput);
            System.out.println("Successfully updated " + fileInput + " classifiers");
        }
    }

    public static String cleanText(String text) {
        text = text.replace("\n", "\\n");
        text = text.replace("\t", "\\t");

        return text;
    }

    public static HashMap<Integer, Integer> readConditionsFromTxt(File file) {
        BufferedReader br = null;
        FileReader fr = null;
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();

        try {
            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                String array[] = sCurrentLine.split("\\|");
                hmap.put(Integer.parseInt(array[0].replace(" ", "")), Integer.parseInt(array[4].replace(" ", "")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return hmap;
    }

    public static void writeConditionsToTxt(File file) {
        try {
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException ex) {
            System.out.println("Create file exception");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (TwitterToDB twitterCondition : twitterConditions) {
                bw.write(twitterCondition.getId() + " | " + twitterCondition.getOgUserName() + " | " + twitterCondition.getOgStatus() + " | " + twitterCondition.getComment() + " | ");
                bw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Write file exception");
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
