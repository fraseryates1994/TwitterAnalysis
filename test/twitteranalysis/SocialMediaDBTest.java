/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteranalysis;

import java.util.ArrayList;
import java.util.HashMap;
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
public class SocialMediaDBTest {

    public SocialMediaDBTest() {
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
     * Test of getAllSwears method, of class SocialMediaDB.
     */
    @Test
    public void testGetAllSwears() {
        System.out.println("getAllSwears");
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB instance = new SocialMediaDB(wr);
        String expResult = "bloody";
        String result = instance.getAllSwears().get(9);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllPositiveWords method, of class SocialMediaDB.
     */
    @Test
    public void testGetAllPositiveWords() {
        System.out.println("getAllPositiveWords");
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB instance = new SocialMediaDB(wr);
        String expResult = "adored";
        String result = instance.getAllPositiveWords().get(38);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllNegativeWords method, of class SocialMediaDB.
     */
    @Test
    public void testGetAllNegativeWords() {
        System.out.println("getAllNegativeWords");
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB instance = new SocialMediaDB(wr);
        String expResult = "anger";
        String result = instance.getAllNegativeWords().get(125);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllCountries method, of class SocialMediaDB.
     */
    @Test
    public void testGetAllCountries() {
        System.out.println("getAllCountries");
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB instance = new SocialMediaDB(wr);
        String expResult = "Armenia";
        String result = instance.getAllCountries().get(6).getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllStates method, of class SocialMediaDB.
     */
    @Test
    public void testGetAllStates() {
        System.out.println("getAllStates");
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB instance = new SocialMediaDB(wr);
        String expResult = "Kansas";
        String result = instance.getAllStates().get(19).getStateName();
        assertEquals(expResult, result);
    }


    /**
     * Test of getAllPositiveEmojis method, of class SocialMediaDB.
     */
    @Test
    public void testGetAllPositiveEmojis() {
        System.out.println("getAllPositiveEmojis");
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB instance = new SocialMediaDB(wr);
        String expResult = "😀";
        String result = instance.getAllPositiveEmojis().get(0);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllNegativeEmojis method, of class SocialMediaDB.
     */
    @Test
    public void testGetAllNegativeEmojis() {
        System.out.println("getAllNegativeEmojis");
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB instance = new SocialMediaDB(wr);
        String expResult = "😒";
        String result = instance.getAllNegativeEmojis().get(0);
        assertEquals(expResult, result);
    }


    /**
     * Test of getMaxContextId method, of class SocialMediaDB.
     */
    @Test
    public void testGetMaxContextId() {
        System.out.println("getMaxContextId");
        String tableName = "set1";
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB instance = new SocialMediaDB(wr);
        int expResult = 8;
        int result = instance.getMaxContextId(tableName);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxId method, of class SocialMediaDB.
     */
    @Test
    public void testGetMaxId() {
        System.out.println("getMaxId");
        String tableName = "set1";
        JDBCWrapper wr = new JDBCWrapper("org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/SocialMedia", "social", "fraz");
        SocialMediaDB instance = new SocialMediaDB(wr);
        int expResult = 449;
        int result = instance.getMaxId(tableName);
        assertEquals(expResult, result);
    }

}
