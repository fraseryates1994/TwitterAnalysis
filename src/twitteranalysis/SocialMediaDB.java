package twitteranalysis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocialMediaDB {

    JDBCWrapper wrapper;

    public SocialMediaDB(JDBCWrapper w) {
        wrapper = w;
    }

    /*
    Name: getAllMembers
    Parameters: none
    Returns: ArrayList : Member
    Comments: Returns arraylist of Members
     */
    public ArrayList<String> getAllSwears() {
        ArrayList ret = new ArrayList<String>();
        wrapper.createStatement();
        wrapper.createResultSet("SELECT * FROM swears");
        try {
            wrapper.getResultSet().next();
            do {
                ret.add(wrapper.getResultSet().getString("swear"));
            } while (wrapper.getResultSet().next());
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public ArrayList<String> getAllPositiveWords() {
        ArrayList ret = new ArrayList<String>();
        wrapper.createStatement();
        wrapper.createResultSet("SELECT * FROM positivewords");
        try {
            wrapper.getResultSet().next();
            do {
                ret.add(wrapper.getResultSet().getString("word"));
            } while (wrapper.getResultSet().next());
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public ArrayList<String> getAllNegativeWords() {
        ArrayList ret = new ArrayList<String>();
        wrapper.createStatement();
        wrapper.createResultSet("SELECT * FROM negativewords");
        try {
            wrapper.getResultSet().next();
            do {
                ret.add(wrapper.getResultSet().getString("word"));
            } while (wrapper.getResultSet().next());
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public ArrayList<Country> getAllCountries() {
        ArrayList<Country> ret = new ArrayList();
        wrapper.createStatement();
        wrapper.createResultSet("SELECT * FROM countries");
        try {
            wrapper.getResultSet().next();
            do {      
                ret.add(new Country(wrapper.getResultSet().getString("code"),wrapper.getResultSet().getString("name"),wrapper.getResultSet().getString("continentcode")));
            } while (wrapper.getResultSet().next());
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public Key getKey(String keyName) {

        wrapper.createStatement();
        wrapper.findRecord("KEYS", "KEYNAME", keyName);
        try {
            Key ret = new Key(wrapper.getResultSet().getInt("id"), wrapper.getResultSet().getString("platform"), wrapper.getResultSet().getString("keyName"), wrapper.getResultSet().getString("keyValue"));
            return ret;
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> getAllPositiveEmojis() {
        ArrayList ret = new ArrayList<String>();
        wrapper.createStatement();
        wrapper.createResultSet("SELECT * FROM positiveemojis");
        try {
            wrapper.getResultSet().next();
            do {
                ret.add(wrapper.getResultSet().getString("emoji"));
            } while (wrapper.getResultSet().next());
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public ArrayList<String> getAllNegativeEmojis() {
        ArrayList ret = new ArrayList<String>();
        wrapper.createStatement();
        wrapper.createResultSet("SELECT * FROM negativeemojis");
        try {
            wrapper.getResultSet().next();
            do {
                ret.add(wrapper.getResultSet().getString("emoji"));
            } while (wrapper.getResultSet().next());
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public void insertWords(String fileName, String tableName) {
        // Read from txt
        Scanner scan = new Scanner(twitteranalysis.SocialMediaDB.class.getResourceAsStream(fileName));
        ArrayList<String> array = new ArrayList();
        int count = 1;

        for (int i = 0; scan.hasNext() == true; i++) {
            array.add(scan.next());
        }

        wrapper.createStatement();
        try {
            for (String string : array) {
                wrapper.getStatement().executeUpdate("insert into " + tableName + "(id, word) values (" + count + ",'" + string + "')");
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertEmoji(String fileName, String tableName) {
        // Read from txt
        Scanner scan = new Scanner(twitteranalysis.SocialMediaDB.class.getResourceAsStream(fileName));
        ArrayList<String> array = new ArrayList();
        int count = 1;

        for (int i = 0; scan.hasNext() == true; i++) {
            array.add(scan.next());
        }

        wrapper.createStatement();
        try {
            for (String string : array) {
                wrapper.getStatement().executeUpdate("insert into " + tableName + "(id, emoji) values (" + count + ",'" + string + "')");
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
