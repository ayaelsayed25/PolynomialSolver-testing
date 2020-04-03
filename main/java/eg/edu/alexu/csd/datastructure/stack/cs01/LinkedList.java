
package eg.edu.alexu.csd.datastructure.stack.cs01;
/**
 * Implementation of a doubly inked list
 * @author Aya Elsayed Muhammed
 * @version 1.0
 */
public class LinkedList {
    Node head;
    Node tail;
    int size = 0;
    

/**
 * adds elements to the list
 * @param element 
 */
    public void add(Object element) {
        Node n = new Node(element);
        if(isEmpty())
        {
            head = n;
        }
        else
        {
            tail.setNext(n);
            n.setPrev(tail);
        }
        tail = n;
        size++;
    }
/**
 * gets elements form a certain position in the list
 * @param index
 * @return the element needed
 */
    public Object get(int index) {
        Node d = head;
        for(int i =0; i<index; i++)
        {
            d = d.getNext();
        }
        Object value = d.getData();
        return value;
    }
    
/**
 * checks if the list is empty
 * @return a Boolean value
 */
    public boolean isEmpty() {
        if(head == null)
            return true;
        else
            return false;
    }
/**
 * returns the size of the list
 * @return the size
 */
    public int size() {
        return size;
    }


  
}
