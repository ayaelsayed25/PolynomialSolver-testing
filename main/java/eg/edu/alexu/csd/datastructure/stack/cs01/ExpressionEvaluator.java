package eg.edu.alexu.csd.datastructure.stack.cs01;

import java.util.*;
import java.util.regex.Pattern;
import java.io.*;
import java.lang.*;

/**
 *This class is used to convert an  infix expression to a postfix expression and then evaluate it.
 * @author Aya Elsayed
 * @version 1.0
 */
public class ExpressionEvaluator implements IExpressionEvaluator {

    String expression; 
    String[] str;
/**
* Takes a symbolic/numeric infix expression as input and converts it to
* postfix notation. There is no assumption on spaces between terms or the
* length of the term (e.g., two digits symbolic  or numeric term)
* @param expression infix expression
* @return postfix expression
*/
    @Override
    public String infixToPostfix(String expression) {
        String myExp = changeString(expression);
        this.expression = myExp;
        str = myExp.split(" "); //making an array of strings for the expression
        LinkedList exp = new LinkedList(); //creating an arraylist 
        exp = validExpression(str); //validation
        exp = changeTypes(exp); //changing the types of objects inside the arraylist
        Stack stack = new Stack();
        String result = "";
        for (int i = 0; i < exp.size(); i++) {
            Object obj = exp.get(i);
            if (obj instanceof Integer) { 
                result = result + obj.toString() + " ";
            } else if (obj instanceof Character) {
                if ((char) obj == '(') {
                    stack.push('(');
                } else if (Character.toString((char) obj).matches("^[a-zA-Z]*$")) {
                    result = result + obj.toString() + " ";
                } else if ((char) obj == ')') {
                    while ((char) stack.peek() != '(') {

                        result = result + stack.pop().toString() + " ";
                    }
                    stack.pop();
                } else {
                    if (stack.isEmpty() || isHigher((char) obj, (char) stack.peek())) {
                        stack.push(obj);
                    } else {
                        while (!stack.isEmpty() && !isHigher((char) obj, (char) stack.peek())) {
                            char c = (char) stack.pop();
                            result = result + c + " ";
                        }
                        stack.push(obj);
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            result = result + stack.pop().toString() + " ";
        }
        //removing the space at the end of the string
        int index = result.length() - 1;
        String newstr = result.substring(0, index) + result.substring(index + 1);
        return newstr;
    }
/**
* Evaluate a postfix numeric expression, with a single space separator
* @param expression
* postfix expression
* @return the expression evaluated value
*/
    @Override
    public int evaluate(String expression) {
        //checking if the expression can be evaluated or not
        String[] exp = expression.split(" ");

        Stack stack = new Stack();
        double mid = 0;
        for (int i = 0; i < exp.length; i++) {
            if (Pattern.matches("^[a-zA-Z]*$", exp[i])) {
                throw new RuntimeException("You can not evaluate this expression ! It contains symbols.");
            } else if (Pattern.matches("^[0-9]*$", exp[i])) {
                stack.push(Double.parseDouble(exp[i]));
            } else {
                double r1 = (double) stack.pop();
                double r2 = (double) stack.pop();
                switch (exp[i].charAt(0)) {
                    case '+':
                        mid = (double) r1 + r2;
                        stack.push((double) mid);
                        break;
                    case '-':
                        mid = (double) r2 - r1;
                        stack.push((double) mid);
                        break;
                    case '/':
                        if (r1 == 0) { //division by zero : throw exception
                            throw new RuntimeException("You cannot divide by zero!");
                        }
                        mid = (double) r2 / r1;
                        stack.push((double) mid);
                        break;
                    case '*':
                        mid = (double) r1 * r2;
                        stack.push((double) mid);
                        break;
                }
                mid = 0;
            }
        }
        double result = (double) stack.pop();
        int r = (int) result;
        return r;
    }
/**
*A method to check whether the expression the user entered is wrong
*or not and then returns a list of characters and integers (expression) if no
*exception happened.
 * @param str an array of strings used to save the expression
 * @return LinkedList : a list contains the operators, symbols, and the integers used 
 */
    public LinkedList validExpression(String[] str) {
        int size = str.length;
        LinkedList exp = new LinkedList();
        //Check that each char is valid
        for (int i = 0; i < size; i++) {
            if (!isSymbolOrOperator(str[i])) {
                throw new RuntimeException("The Expression you entered is wrong");
            } else {
                //For negative numbers : dummy zeroes
                if (!str[i].equals("-") && Pattern.matches("^[-]\\d*$", str[i])) {
                    exp.add(new String("("));
                    exp.add(new String("0"));
                    exp.add(new String("-"));
                    int s = Integer.parseInt(str[i]);
                    s = -1 * s;
                    String myS = Integer.toString(s);
                    exp.add(new String(myS));
                    exp.add(new String(")"));
                } else {
                    exp.add(new String(str[i]));
                }
            }
        }
        //Check if there if the expression starts with a wrong string:
        if (str[0].matches("[\\+\\*\\-\\/]") || str[size - 1].matches("[\\+\\*\\-\\/]")) {
            throw new RuntimeException("The Expression you entered is wrong");
        }
        //Check the order of parentheses
        if (!checkParentheses(exp)) {
            throw new RuntimeException("The Expression you entered is wrong");
        }
        for (int i = 0; i < size - 1; i++) {
            String a = (String) exp.get(i);
            String b = (String) exp.get(i + 1);
            boolean isA = a.matches("^[a-zA-Z]*$") || Pattern.matches("^[-+]?\\d+$", a);
            boolean isB = b.matches("^[a-zA-Z]*$") || Pattern.matches("^[-+]?\\d+$", b);
            //Check there is no two consecutive numbers or symbols
            if (a.matches("^[a-zA-Z]*$") && b.matches("^[a-zA-Z]*$")) {
                throw new RuntimeException("The Expression you entered is wrong");
            } //Check there is no something like "a (" or "1 (" or ") a" or ") 1"
            else if (isA && b.equals("(")) {
                throw new RuntimeException("The Expression you entered is wrong");
            } else if (isB && a.equals(")")) {
                throw new RuntimeException("The Expression you entered is wrong");
            } //"Check there is no two consecutive operators "
            else if (a.matches("[\\+\\*\\-\\/\\(]") && b.matches("[\\+\\*\\-\\/\\)]")) {
                throw new RuntimeException("The Expression you entered is wrong");
            }
        }
        return exp;
    }
/**
 * A method to check if the string is an operator, number, or a parenthesis
 * @param s : the expression the user entered
 * @return a Boolean value
 */
    public boolean isSymbolOrOperator(String s) {
        if (Pattern.matches("^[-+]?\\d+$", s)) {
            return true;
        }
        if (s.length() == 1) {
            char a = s.charAt(0);
            if (a == '+' || a == '-' || a == '/' || a == '*' || a == '(' || a == ')') {
                return true;
            } else if (Character.toString(a).matches("^[a-zA-Z]*$")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
/**
 * A method to check if parentheses are ordered in the appropriate way
 * @param str a list that contains the expression
 * @return a Boolean value
 */
    public boolean checkParentheses(LinkedList str) {
        Stack stack = new Stack();
        for (int i = 0; i < str.size(); i++) {
            if (str.get(i).equals("(")) {
                stack.push(str.get(i));
            }
            if (str.get(i).equals(")")) {
                if (stack.isEmpty()) {
                    return false;
                } else if (!(((String) stack.pop()).equals("(") && str.get(i).equals(")"))) {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
/**
 * a method that changed the numbers from strings to integers and the characters from string to char
 * @param str a list containing the expression
 * @return a list with the type of elements changed
 */
    public LinkedList changeTypes(LinkedList str) {
        LinkedList exp = new LinkedList();
        for (int i = 0; i < str.size(); i++) {
            String a = (String) str.get(i);
            if (Pattern.matches("^[-+]?\\d+$", a)) {
                exp.add(Integer.parseInt(a));
            } else {
                exp.add(a.charAt(0));
            }
        }
        return exp;
    }
/**
 * A method that checks if a has higher precedence than b or not
 * @param a : first character
 * @param b : second one
 * @return : a Boolean value
 */
    public boolean isHigher(char a, char b) {
        if ((a == '*' && b == '-') || (a == '*' && b == '+')) {
            return true;
        } else if ((a == '/' && b == '+') || (a == '/' && b == '-')) {
            return true;
        } else if (b == '(') {
            return true;
        } else {
            return false;
        }
    }
    
/**
 * Takes the expression from the user and put spaces in it ( in the right order )
 * @param str : the string taken from the user 
 * @return : the string to be converted 
 */
    public String changeString(String str)
    {
        str = str.replaceAll("\\s","");
        String s = "";
        int k =0;
        char b = str.charAt(0);
        if(b == '-' && str.length()>1 && Character.toString(str.charAt(1)).matches("^[0-9\\(]*$"))
        {
            if(str.charAt(1) == '(')
                s = "-1 * ";
            else
                s = s + b;
            k = 1;
        }
        else
            k =0;
        for(int i=k; i<str.length()-1; i++)
        {
            char a = str.charAt(i);
            if(Character.toString(a).matches("^[0-9]*$") && Character.toString(str.charAt(i + 1)).matches("^[0-9]*$"))
                s = s + a;
            else if(a == '-' && Character.toString(str.charAt(i + 1)).matches("^[0-9]*$") && !Character.toString(str.charAt(i - 1)).matches("^[0-9\\)]*$"))
                s = s + a;
            else
                s = s + a + " ";
        }
        s = s + str.charAt(str.length() - 1);
        System.out.println(s);
        return s;
    }    
}
