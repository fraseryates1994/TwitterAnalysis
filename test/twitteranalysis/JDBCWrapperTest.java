/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteranalysis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fraser
 */
public class JDBCWrapperTest {

    public JDBCWrapperTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTableCount method, of class JDBCWrapper.
     */
    @Test
    public void testGetTableCount() {
        System.out.println("getTableCount");
        String table = "set1";
        JDBCWrapper instance = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        int expResult = 450;
        int result = instance.getTableCount(table);
        assertEquals(expResult, result);
    }

    /**
     * Test of createStatement method, of class JDBCWrapper.
     */
    @Test
    public void testCreateStatement() {
        System.out.println("createStatement");
        JDBCWrapper instance = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        instance.createStatement();
    }

    /**
     * Test of createResultSet method, of class JDBCWrapper.
     */
    @Test
    public void testCreateResultSet() {
        System.out.println("createResultSet");
        String query = "SELECT * FROM results";
        JDBCWrapper instance = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        instance.createResultSet(query);
    }

    /**
     * Test of findRecord method, of class JDBCWrapper.
     */
    @Test
    public void testFindRecord() {
        System.out.println("findRecord");
        String table = "";
        String column = "";
        String searchValue = "";
        JDBCWrapper instance = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        boolean expResult = false;
        boolean result = instance.findRecord(table, column, searchValue);
        assertEquals(expResult, result);
    }

}
