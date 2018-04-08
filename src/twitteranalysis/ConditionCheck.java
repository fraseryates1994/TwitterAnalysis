package twitteranalysis;

import java.util.ArrayList;
import java.util.Scanner;
import twitter4j.Status;

/**
 *
 * @author Fraser
 */
public class ConditionCheck {
      
     public  static Country getCountryFromLocation(Status reply) {
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB db = new SocialMediaDB(wr);
        ArrayList<Country> countries = new ArrayList();
        ArrayList<State> states = new ArrayList();

        countries.addAll(db.getAllCountries());
        states.addAll(db.getAllStates());
        
        if (reply == null) {
             return null;
         }
        
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
        
        if (reply == null) {
             return false;
         }

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

        if (reply == null) {
             return false;
         }
        
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
        
        if (reply == null) {
             return false;
         }

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
        
        if (reply == null) {
             return false;
         }

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
        
        if (reply == null) {
             return false;
         }

        for (int i = 0; i < swears.size(); i++) {
            if (reply.getText().contains(swears.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    
}
