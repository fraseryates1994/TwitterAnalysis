package twitteranalysis;

import java.sql.SQLException;
import java.util.ArrayList;
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
}
