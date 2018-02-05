package twitteranalysis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Harri Renney
 */
public class JDBCWrapper {
    private Connection con = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    
    //This constructor establishes a connection with a target database.
    //Example instantiation: JBCWrapper wrapper = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/XYZ Web Application DB", "root", "root");
    public JDBCWrapper(String driver, String db, String dbName, String dbPassword) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection(db, dbName, dbPassword);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Taking a parameter of target table, returns the number of rows.
    public int getTableCount(String table)
    {
        int ret = 0;
        try {
            createStatement();
            createResultSet("SELECT * FROM " + table);
            
            resultSet.last();
            ret = resultSet.getRow();
            resultSet.beforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public void createStatement()
    {
        try {
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //The resultSet requires an initalised statement. Call createStatment first.
    public void createResultSet(String query) throws NullPointerException
    {
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex){
            System.err.println("A STATEMENT HAS NOT BEEN INITALISED. CALL CREATESTATEMENT ON OBJECT FIRST BEFORE CREATING A RESULT SET.");
        }
    }
    
    //Takes the table name, column and value to search for. If found returns true and result set to the record.
    public boolean findRecord(String table, String column, String searchValue)
    {
        createResultSet("select * from "+table+" where \""+column+"\" = '" + searchValue + "'");
        try {
            if(this.getResultSet().next())
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Statement getStatement() {
        return statement;
    }
    
    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}
