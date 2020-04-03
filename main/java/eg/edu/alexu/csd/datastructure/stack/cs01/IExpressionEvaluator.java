
package eg.edu.alexu.csd.datastructure.stack.cs01;

/**
 * Interface for the expression evaluator application
 * @author Aya Elsayed Muhammed
 * @version 1.0
 */
public interface IExpressionEvaluator {

public String infixToPostfix(String expression);

public int evaluate(String expression);   
}
