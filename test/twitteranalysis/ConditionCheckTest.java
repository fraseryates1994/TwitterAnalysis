/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteranalysis;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import twitter4j.Status;

/**
 *
 * @author Fraser
 */
public class ConditionCheckTest {
    
    public ConditionCheckTest() {
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
     * Test of getCountryFromLocation method, of class ConditionCheck.
     */
    @Test
    public void testGetCountryFromLocation() {
        System.out.println("getCountryFromLocation");
        Status reply = null;
        Country expResult = null;
        Country result = ConditionCheck.getCountryFromLocation(reply);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasPositiveWord method, of class ConditionCheck.
     */
    @Test
    public void testHasPositiveWord() {
        System.out.println("hasPositiveWord");
        Status reply = null;
        boolean expResult = false;
        boolean result = ConditionCheck.hasPositiveWord(reply);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasNegativeWord method, of class ConditionCheck.
     */
    @Test
    public void testHasNegativeWord() {
        System.out.println("hasNegativeWord");
        Status reply = null;
        boolean expResult = false;
        boolean result = ConditionCheck.hasNegativeWord(reply);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasPositiveEmoji method, of class ConditionCheck.
     */
    @Test
    public void testHasPositiveEmoji() {
        System.out.println("hasPositiveEmoji");
        Status reply = null;
        boolean expResult = false;
        boolean result = ConditionCheck.hasPositiveEmoji(reply);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasNegativeEmoji method, of class ConditionCheck.
     */
    @Test
    public void testHasNegativeEmoji() {
        System.out.println("hasNegativeEmoji");
        Status reply = null;
        boolean expResult = false;
        boolean result = ConditionCheck.hasNegativeEmoji(reply);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasSwearWord method, of class ConditionCheck.
     */
    @Test
    public void testHasSwearWord() {
        System.out.println("hasSwearWord");
        Status reply = null;
        boolean expResult = false;
        boolean result = ConditionCheck.hasSwearWord(reply);
        assertEquals(expResult, result);
    }
    
}
