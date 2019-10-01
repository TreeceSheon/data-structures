import java.util.List;

public interface Stack<E> {

    public boolean isEmpty();

    public int getSize();

    public void push(E element);

    public E pop() throws StackEmptyException;

    public E top() throws StackEmptyException;

}
