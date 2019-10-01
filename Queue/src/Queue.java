import java.util.Collection;

public abstract class Queue<E> {

    public abstract int getSize();

    public abstract boolean isEmpty();

    public abstract void inQueue(E ele) throws QueueFullException;

    public abstract E deQueue() throws QueueEmptyException;

    public abstract E pop();
}
