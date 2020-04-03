
package eg.edu.alexu.csd.datastructure.stack.cs01;


/**
 * Interface for the stack implementation
 * @author Aya Elsayed Muhammed
 * @version 1.0
 */
public interface IStack {
   
    public Object pop();
    public Object peek();
    public void push(Object element);
    public boolean isEmpty();
    public int size();
    
}
