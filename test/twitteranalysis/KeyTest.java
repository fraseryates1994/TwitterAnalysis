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

/**
 *
 * @author Fraser
 */
public class KeyTest {
    
    public KeyTest() {
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
     * Test of getId method, of class Key.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Key instance = new Key(1,"facebook","OAuthAccessToken","aaa");
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlatform method, of class Key.
     */
    @Test
    public void testGetPlatform() {
        System.out.println("getPlatform");
        Key instance = new Key(1,"facebook","OAuthAccessToken","aaa");
        String expResult = "facebook";
        String result = instance.getPlatform();
        assertEquals(expResult, result);
    }

    /**
     * Test of getKeyName method, of class Key.
     */
    @Test
    public void testGetKeyName() {
        System.out.println("getKeyName");
        Key instance = new Key(1,"facebook","OAuthAccessToken","aaa");
        String expResult = "OAuthAccessToken";
        String result = instance.getKeyName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getKayValue method, of class Key.
     */
    @Test
    public void testGetKayValue() {
        System.out.println("getKayValue");
        Key instance = new Key(1,"facebook","OAuthAccessToken","aaa");
        String expResult = "aaa";
        String result = instance.getKayValue();
        assertEquals(expResult, result);
    }
    
}
