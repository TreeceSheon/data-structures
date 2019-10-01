import java.util.Objects;
import java.util.Vector;

public class ArrayStack<E> implements Stack<E>{

    protected Object[] stackElements;
    public static final int CAPACITY = 1024;
    protected int capacity;
    protected int top = -1;

    public ArrayStack(){
        this(CAPACITY);
    }

    public ArrayStack(int capacity) {
        stackElements = new Object[capacity];
    }
    @Override
    public boolean isEmpty() {
        return top < 0;
    }

    @Override
    public int getSize() {
        return top + 1;
    }

    @Override
    public void push(E element) {
        if (getSize() == capacity){
            throw new StackFullException("stack full");
        }
        stackElements[capacity++] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized E pop() throws StackEmptyException {
        if (getSize() < 0)
            throw new StackEmptyException("stack empty");
         var element = stackElements[top--];
         stackElements[top] = null;
         return (E)element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E top() throws StackEmptyException {
        if (getSize() < 0)
            throw new StackEmptyException("stack empty");
        return (E)stackElements[top];
    }
}
