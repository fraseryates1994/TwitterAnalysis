package twitteranalysis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
                ret.add(new Country(wrapper.getResultSet().getString("code"), wrapper.getResultSet().getString("name"), wrapper.getResultSet().getString("continentcode")));
            } while (wrapper.getResultSet().next());
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public ArrayList<State> getAllStates() {
        ArrayList<State> ret = new ArrayList();
        wrapper.createStatement();
        wrapper.createResultSet("SELECT * FROM state");
        try {
            wrapper.getResultSet().next();
            do {
                ret.add(new State(wrapper.getResultSet().getString("code"), wrapper.getResultSet().getString("name")));
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

    public Country getCountry(String countryCode) {

        wrapper.createStatement();
        wrapper.findRecord("COUNTRIES", "CODE", countryCode);
        try {
            Country ret = new Country(wrapper.getResultSet().getString("code"), wrapper.getResultSet().getString("name"), wrapper.getResultSet().getString("continentcode"));
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

    public void createTable(String tableName) {
        try {
            wrapper.createStatement();

            String sql = "CREATE TABLE " + tableName
                    + "(id int,"
                    + " ogUserName VARCHAR(64),"
                    + " ogStatus VARCHAR(500),"
                    + " ogComment VARCHAR(500),"
                    + " contextId int,"
                    + " followersCount int,"
                    + " favouriteCount int,"
                    + " friendCount int,"
                    + " location int,"
                    + " isVerified int,"
                    + " hasSwear int,"
                    + " hasPositiveWord int,"
                    + " hasNegativeWord int,"
                    + " hasPositiveEmoji int,"
                    + " hasNegativeEmoji int,"
                    + " classifier int)";

            wrapper.getStatement().executeUpdate(sql);

            System.out.println("Created " + tableName + " table successfully");
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertClassifier(HashMap<Integer, Integer> hmap, String tableName) {
        wrapper.createStatement();
        try {
            for (Map.Entry<Integer, Integer> entry : hmap.entrySet()) {
                wrapper.getStatement().executeUpdate("UPDATE " + tableName
                        + " SET classifier=" + entry.getValue()
                        + " WHERE id =" + entry.getKey());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getMaxContextId(String tableName) {
        int maxId = 0;
        wrapper.createStatement();
        wrapper.createResultSet("SELECT MAX(contextId) FROM " + tableName);
        try {
            while (wrapper.getResultSet().next()) {
                maxId = wrapper.getResultSet().getInt(1);
                if (wrapper.getResultSet().wasNull()) {
                    return -1;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            return 0;
        }
        return maxId;
    }

    public int getMaxId(String tableName) {
        wrapper.createStatement();
        wrapper.createResultSet("SELECT MAX(id) FROM " + tableName);
        int maxId = 0;
        try {
            while (wrapper.getResultSet().next()) {
                maxId = wrapper.getResultSet().getInt(1);
                if (wrapper.getResultSet().wasNull()) {
                    return -1;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            return 0;
        }
        return maxId;
    }

    public int insertCondition(ArrayList<EncodeChromosome> conditions, String tableName, int maxEntries) {
        wrapper.createStatement();
        int count = 0;
        int temp = getMaxId(tableName) + 1;
        int oldMaxId = temp;
        int oldMaxContextId = getMaxContextId(tableName) + 1;

        try {
            for (EncodeChromosome condition : conditions) {
                wrapper.getStatement().executeUpdate("insert into " + tableName + "(id, ogUserName, ogStatus, ogComment,"
                        + " contextId, followersCount, favouriteCount, friendCount, location, isVerified, hasSwear, hasPositiveWord, hasNegativeWord, hasPositiveEmoji,"
                        + " hasNegativeEmoji) values (" + oldMaxId + ",'" + condition.getOgUserName() + "','" + condition.getOgStatus()
                        + "','" + condition.getComment() + "'," + oldMaxContextId + "," + condition.getFollowersCount() + "," + condition.getFavouriteCount() + "," + condition.getFriendCount() + ","
                        + condition.getLocation() + "," + condition.getIsVerified() + "," + condition.getHasSwear() + "," + condition.getHasPositiveWord() + "," + condition.getHasNegativeWord()
                        + "," + condition.getHasPositiveEmoji() + "," + condition.getHasNegativeEmoji() + ")");
                if (maxEntries - 1 <= count) {
                    break;
                }
                count++;
                oldMaxId++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaDB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    public void insertEmoji(String fileName, String tableName) {
        // Read from txt
        Scanner scan = new Scanner(twitteranalysis.SocialMediaDB.class
                .getResourceAsStream(fileName));
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
            Logger.getLogger(JDBCWrapper.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
