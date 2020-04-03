
package eg.edu.alexu.csd.datastructure.stack.cs01;
import java.io.*;
import java.lang.*;
import java.util.*;
/**
 * A class of the nodes used in linked lists
 * @author Aya Elsayed muhammed
 * @version 1.0
 */
public class Node {
    private Node next;
    private Object data;
    private Node prev;
/**
 * a constructor to set the data of the node
 * @param data 
 */
    public Node( Object data)
    {
        this.data = data;
    }
/**
 * returns the data
 * @return 
 */
    public Object getData()
    {
        return data;
    }
/**
 * returns the next node
 * @return 
 */
    public Node getNext()
    {
        return next;
    }
/**
 * sets the next node
 * @param next 
 */
    public void setNext(Node next)
    {
        this.next = next;
    }
/**
 * sets the data of the node
 * @param data 
 */
    public void setData(Object data)
    {
        this.data = data;
    }
/**
 * sets the previous node
 * @param prev 
 */
    public void setPrev(Node prev)
    {
        this.prev = prev;
    }
/**
 * gets the previous node
 * @return 
 */
    public Node getPrev()
    {
        return prev;
    }
}
