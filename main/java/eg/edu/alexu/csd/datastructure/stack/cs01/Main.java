
package eg.edu.alexu.csd.datastructure.stack.cs01;
import java.io.*;
import java.lang.*;
import java.util.*;
/**
 * Main class for running the app
 * @author Aya Elsayed
 * @version 1.0
 */
public class Main {
    static String infixExp, postfixExp;
    public static void main(String[] args) throws Exception { 
        Scanner scan = new Scanner(System.in);
        ExpressionEvaluator obj = new ExpressionEvaluator();
        System.out.println("Hey! Expression Evaluator app is used to convert an infix expression to a postfix expression.\nTry it out now!\nA menu will be shown to you, so you can choose an action");
        while(true)
        {
            System.out.println("1- Convert an infix expression to a postfix expression.\n"
                             + "2- Evaluate the expression you entered\n"
                             + "3- Important Tips on how to write the expressions.\n"
                             + "4- Exit");
            boolean flag1 = true;
            String choice = "";
            while (flag1){
                choice = scan.nextLine();
                if (isNumeric(choice) && Integer.parseInt(choice) > 0 && Integer.parseInt(choice) < 5){
                    flag1 = false;
                }
                else System.out.println("Enter a valid input!");
            }
            if(choice.equals("1"))
            {
                System.out.println("Enter the infix expression (contains numbers, operators, parentheses, and letters only.:\n");
                infixExp = scan.nextLine();
                postfixExp = obj.infixToPostfix(infixExp);
                System.out.println("Postfix Expression : " + postfixExp +"\n");
            }
            else if(choice.equals("2"))
            {
                if(postfixExp == null)
                    System.out.println("You haven't entered any expressions yet. The menu will be shown again to you!");
                else
                {
                    System.out.println("Your expression to be evaluated :\nPostfix Form : " + postfixExp + " Infix Form: " + infixExp );
                    int result = obj.evaluate(postfixExp);
                    System.out.println("The result is : " + result + "\n");
                }
            }
            else if(choice.equals("3"))
            {
                System.out.println("1-You can use symbols which are the characters from A/a to Z/z.\n"
                                  +"2-If your entered digits with spaces between them, the program will consider them all as one number\n"
                                  +"3-You cannot enter negative symbols, e.g., -b");
            }
            else
                break;
        }
        
    } 
/**
 * Check if the value is numeric
 * @param str
 * @return 
 */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
