import java.util.Arrays;
import java.util.Collection;

public class circularArrayQueue<E> extends Queue<E>{
    public static final int CAPACITY = 1024;
    private Object[] queueElements;
    private int head;
    private int tail;
    private int capacity;

    public circularArrayQueue() {
        this(CAPACITY);
    }

    public circularArrayQueue(int capacity) {
        this.capacity = capacity;
        queueElements = new Object[capacity];
    }

    public circularArrayQueue(Collection<? extends E> c) {
        Object[] ec = c.toArray();
        capacity = ec.length;
        if (ec.getClass() != Object[].class)
            ec = Arrays.copyOf(ec, ec.length, Object[].class);
        if (ec.length == 1 && ec[0] == null) {
            throw new NullPointerException();
        }
        this.queueElements = (capacity > 0)?ec:new Object[1];
        head = 0;
        tail = capacity - 1;
    }

    @Override
    public int getSize() {
        return (capacity + tail - head) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    @Override
    public void inQueue(E ele) throws QueueFullException {
        if (getSize() == capacity) {
            throw new QueueFullException("Queue full error");
        }
        queueElements[tail++] = ele;
        tail = tail % capacity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E deQueue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("queue empty error");
        }
        var ele = queueElements[head++];
        head %= capacity;
        return (E)ele;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E pop() {
        return (E) queueElements[head];
    }
}
