/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteranalysis;

import java.io.File;
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
public class SupervisedLearningTest {

    public SupervisedLearningTest() {
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
     * Test of readConditionsFromTxt method, of class SupervisedLearning.
     */
    @Test
    public void testReadConditionsFromTxt() {
        System.out.println("readConditionsFromTxt");
        File file = new File("C:\\Users\\Fraser\\Google Drive\\GitProjects\\TwitterAnalysis\\src\\twitteranalysis\\SupervisedLearningTxt\\" + "test" + ".txt");
        int expResult = 2;
        int result = SupervisedLearning.readConditionsFromTxt(file).get(1).intValue();
        assertEquals(expResult, result);
    }

}
