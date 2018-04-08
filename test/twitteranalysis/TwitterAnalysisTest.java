/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteranalysis;

import java.util.ArrayList;
import java.util.List;
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
public class TwitterAnalysisTest {
    
    public TwitterAnalysisTest() {
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
     * Test of cleanText method, of class TwitterAnalysis.
     */
    @Test
    public void testCleanText() {
        System.out.println("cleanText");
        String text = "hi \n hi \n";
        String expResult = "hi \\n hi \\n";
        String result = TwitterAnalysis.cleanText(text);
        assertEquals(expResult, result);
    }
    
}
