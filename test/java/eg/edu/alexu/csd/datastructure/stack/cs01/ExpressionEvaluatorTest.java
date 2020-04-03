
package eg.edu.alexu.csd.datastructure.stack.cs01;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *This class is for testing ExpressionEvaluator class
 * @author Aya Elsayed Muhammed
 * @version 1.0
 */
public class ExpressionEvaluatorTest {
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test 1 of infixToPostfix method, of class ExpressionEvaluator.
     */
    @Test
    public void testInfixToPostfix() {
        System.out.println("infixToPostfix");
        String expression = "(a / (b - c + d)) * (e - a) * c";
        ExpressionEvaluator instance = new ExpressionEvaluator();
        String expResult = "a b c - d + / e a - * c *";
        String result = instance.infixToPostfix(expression);
        assertEquals(expResult, result);
    }

    /**
     * Test 2 of infixToPostfix method, of class ExpressionEvaluator.
     */
    @Test
    public void testInfixToPostfix2() {
        System.out.println("infixToPostfix");
        String expression = "-1 / 5 - 6 + 7 * 4 -- (2*3)"; //a negative before an open paranthesis
        ExpressionEvaluator instance = new ExpressionEvaluator();
        String expResult = "0 1 - 5 / 6 - 7 4 * + 0 1 - 2 3 * * -";
        String result = instance.infixToPostfix(expression);
        assertEquals(expResult, result);
    }
 
    /**
     *
     */
    @Rule
    public final ExpectedException exception = ExpectedException.none();    
    /**
     *Test 3 of infixToPostfix method, of class ExpressionEvaluator.
     */
    @Test
    public void testInfixToPostfix3() {
        System.out.println("infixToPostfix");
        ExpressionEvaluator instance = new ExpressionEvaluator();
        exception.expect(RuntimeException.class);
        String s= "(5+5) *7+3-+10.5";
        instance.infixToPostfix(s);
    }


    /**
     * Test 1 of evaluate method, of class ExpressionEvaluator.
     */
    @Test
    public void testEvaluate() {
        System.out.println("evaluate");
        String expression = "0 1 - 5 / 6 - 7 4 * + 0 1 - 2 3 * * -";
        ExpressionEvaluator instance = new ExpressionEvaluator();
        int expResult = 27;
        int result = instance.evaluate(expression);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }    
    /**
     * Test 2 of evaluate method, of class ExpressionEvaluator.
     */
    @Test
    public void testEvaluate2() {
        System.out.println("evaluate");
        String expression = "0 1 - 2 3 - 5 + / 5 4 - * 4 *";
        ExpressionEvaluator instance = new ExpressionEvaluator();
        int expResult = -1;
        int result = instance.evaluate(expression);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test 3 of evaluate method, of class ExpressionEvaluator.
     */
    @Test
    public void testEvaluate3() {
        System.out.println("evaluate");
        String expression = "0 a - 2 3 - 5 + / 5 4 - * 4 *";
        ExpressionEvaluator instance = new ExpressionEvaluator();
        exception.expect(RuntimeException.class);
        instance.evaluate(expression);
        // TODO review the generated test code and remove the default call to fail.
    }        
}
