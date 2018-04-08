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
public class EncodeChromosomeTest {
    
    public EncodeChromosomeTest() {
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
     * Test of setOgUserName method, of class EncodeChromosome.
     */
    @Test
    public void testSetOgUserName() {
        System.out.println("setOgUserName");
        String ogUserName = "Fraser";
        EncodeChromosome instance = new EncodeChromosome();
        instance.setOgUserName(ogUserName);
    }

    /**
     * Test of setOgStatus method, of class EncodeChromosome.
     */
    @Test
    public void testSetOgStatus() {
        System.out.println("setOgStatus");
        String status = "Hello";
        EncodeChromosome instance = new EncodeChromosome();
        instance.setOgStatus(status);
    }

    /**
     * Test of setComment method, of class EncodeChromosome.
     */
    @Test
    public void testSetComment() {
        System.out.println("setComment");
        String comment = "Hi";
        EncodeChromosome instance = new EncodeChromosome();
        instance.setComment(comment);
    }

    /**
     * Test of setFollowersCount method, of class EncodeChromosome.
     */
    @Test
    public void testSetFollowersCount() {
        System.out.println("setFollowersCount");
        int followersCount = 500;
        EncodeChromosome instance = new EncodeChromosome();
        instance.setFollowersCount(followersCount);
    }

    /**
     * Test of setFavouriteCount method, of class EncodeChromosome.
     */
    @Test
    public void testSetFavouriteCount() {
        System.out.println("setFavouriteCount");
        int favouriteCount = 2;
        EncodeChromosome instance = new EncodeChromosome();
        instance.setFavouriteCount(favouriteCount);
    }

    /**
     * Test of setFriendCount method, of class EncodeChromosome.
     */
    @Test
    public void testSetFriendCount() {
        System.out.println("setFriendCount");
        int friendCount = 400;
        EncodeChromosome instance = new EncodeChromosome();
        instance.setFriendCount(friendCount);
    }

    /**
     * Test of setLocation method, of class EncodeChromosome.
     */
    @Test
    public void testSetLocation() {
        System.out.println("setLocation");
        Country location = new Country("AT","Austria","EU");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setLocation(location);
    }

    /**
     * Test of setIsVerified method, of class EncodeChromosome.
     */
    @Test
    public void testSetIsVerified() {
        System.out.println("setIsVerified");
        boolean verified = true;
        EncodeChromosome instance = new EncodeChromosome();
        instance.setIsVerified(verified);
    }

    /**
     * Test of setHasSwear method, of class EncodeChromosome.
     */
    @Test
    public void testSetHasSwear() {
        System.out.println("setHasSwear");
        boolean swear = true;
        EncodeChromosome instance = new EncodeChromosome();
        instance.setHasSwear(swear);
    }

    /**
     * Test of setHasPositiveWord method, of class EncodeChromosome.
     */
    @Test
    public void testSetHasPositiveWord() {
        System.out.println("setHasPositiveWord");
        boolean posWord = true;
        EncodeChromosome instance = new EncodeChromosome();
        instance.setHasPositiveWord(posWord);
    }

    /**
     * Test of setHasNegativeWord method, of class EncodeChromosome.
     */
    @Test
    public void testSetHasNegativeWord() {
        System.out.println("setHasNegativeWord");
        boolean negWord = true;
        EncodeChromosome instance = new EncodeChromosome();
        instance.setHasNegativeWord(negWord);
    }

    /**
     * Test of setHasPositiveEmoji method, of class EncodeChromosome.
     */
    @Test
    public void testSetHasPositiveEmoji() {
        System.out.println("setHasPositiveEmoji");
        boolean posEmoji = true;
        EncodeChromosome instance = new EncodeChromosome();
        instance.setHasPositiveEmoji(posEmoji);
    }

    /**
     * Test of setHasnegativeEmoji method, of class EncodeChromosome.
     */
    @Test
    public void testSetHasnegativeEmoji() {
        System.out.println("setHasnegativeEmoji");
        boolean negEmoji = true;
        EncodeChromosome instance = new EncodeChromosome();
        instance.setHasnegativeEmoji(negEmoji);
    }

    /**
     * Test of getOgUserName method, of class EncodeChromosome.
     */
    @Test
    public void testGetOgUserName() {
        System.out.println("getOgUserName");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setOgUserName("Fraser");
        String expResult = "Fraser";
        String result = instance.getOgUserName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOgStatus method, of class EncodeChromosome.
     */
    @Test
    public void testGetOgStatus() {
        System.out.println("getOgStatus");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setOgStatus("Hello");
        String expResult = "Hello";
        String result = instance.getOgStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of getComment method, of class EncodeChromosome.
     */
    @Test
    public void testGetComment() {
        System.out.println("getComment");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setComment("hi");
        String expResult = "hi";
        String result = instance.getComment();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFollowersCount method, of class EncodeChromosome.
     */
    @Test
    public void testGetFollowersCount() {
        System.out.println("getFollowersCount");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setFollowersCount(150);
        int expResult = 2;
        int result = instance.getFollowersCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFavouriteCount method, of class EncodeChromosome.
     */
    @Test
    public void testGetFavouriteCount() {
        System.out.println("getFavouriteCount");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setFavouriteCount(5);
        int expResult = 1;
        int result = instance.getFavouriteCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFriendCount method, of class EncodeChromosome.
     */
    @Test
    public void testGetFriendCount() {
        System.out.println("getFriendCount");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setFriendCount(1000);
        int expResult = 3;
        int result = instance.getFriendCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLocation method, of class EncodeChromosome.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setLocation(new Country("AT","Austria","EU"));
        int expResult = 3;
        int result = instance.getLocation();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIsVerified method, of class EncodeChromosome.
     */
    @Test
    public void testGetIsVerified() {
        System.out.println("getIsVerified");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setIsVerified(true);
        int expResult = 1;
        int result = instance.getIsVerified();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHasSwear method, of class EncodeChromosome.
     */
    @Test
    public void testGetHasSwear() {
        System.out.println("getHasSwear");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setHasSwear(true);
        int expResult = 1;
        int result = instance.getHasSwear();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHasPositiveWord method, of class EncodeChromosome.
     */
    @Test
    public void testGetHasPositiveWord() {
        System.out.println("getHasPositiveWord");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setHasPositiveWord(true);
        int expResult = 1;
        int result = instance.getHasPositiveWord();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHasNegativeWord method, of class EncodeChromosome.
     */
    @Test
    public void testGetHasNegativeWord() {
        System.out.println("getHasNegativeWord");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setHasNegativeWord(true);
        int expResult = 1;
        int result = instance.getHasNegativeWord();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHasPositiveEmoji method, of class EncodeChromosome.
     */
    @Test
    public void testGetHasPositiveEmoji() {
        System.out.println("getHasPositiveEmoji");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setHasPositiveEmoji(true);
        int expResult = 1;
        int result = instance.getHasPositiveEmoji();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHasNegativeEmoji method, of class EncodeChromosome.
     */
    @Test
    public void testGetHasNegativeEmoji() {
        System.out.println("getHasNegativeEmoji");
        EncodeChromosome instance = new EncodeChromosome();
        instance.setHasnegativeEmoji(true);
        int expResult = 1;
        int result = instance.getHasNegativeEmoji();
        assertEquals(expResult, result);
    }
}
