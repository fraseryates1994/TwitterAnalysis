package twitteranalysis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XYZWebApplicationDB {

    JDBCWrapper wrapper;

    public XYZWebApplicationDB(JDBCWrapper w) {
        wrapper = w;
    }

    /*
    Name: insertUser
    Parameters: u : User
    Returns: void
    Comments: inserts user to DB
     */
    public void insertUser(User u) {
        wrapper.createStatement();
        try {
            wrapper.getStatement().executeUpdate("insert into users values ('" + u.getId() + "', '" + u.getFirstName() + "', '" + u.getLastName() + "', '" + u.getTwitterName() + "')");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    Name: getUser
    Parameters: id : String
    Returns: User
    Comments: Gets User from ID
     */
    public User getUser(String id) {
        User ret = new User();
        wrapper.createStatement();
        wrapper.findRecord("users", "id", id);
        try {
            ret.setFirstName(wrapper.getResultSet().getString("id"));
            ret.setFirstName(wrapper.getResultSet().getString("firstName"));
            ret.setLastName(wrapper.getResultSet().getString("lastName"));
            ret.setTwitterName(wrapper.getResultSet().getString("twitterName"));
        } catch (SQLException ex) {
            Logger.getLogger(XYZWebApplicationDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    /*
    Name: getAllMembers
    Parameters: none
    Returns: ArrayList : Member
    Comments: Returns arraylist of Members
     */
    public ArrayList<User> getAllMembers() {
        ArrayList ret = new ArrayList<User>();
        wrapper.createStatement();
        wrapper.createResultSet("SELECT * FROM users");
        try {
            wrapper.getResultSet().next();
            do {
                ret.add(new User(wrapper.getResultSet().getString("id"), wrapper.getResultSet().getString("firstName"), wrapper.getResultSet().getString("lastName"), wrapper.getResultSet().getString("twitterName")));
            } while (wrapper.getResultSet().next());
        } catch (SQLException ex) {
            Logger.getLogger(XYZWebApplicationDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
