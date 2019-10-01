import java.util.Arrays;

public class circularArrayQueueTest {

    public static void main(String[] args) throws QueueEmptyException, QueueFullException {
        Integer[] arr = {1, 2, 3, 4, 5, 6};
        Queue<Integer> newQueue = new circularArrayQueue<>(Arrays.asList(arr));
        newQueue.deQueue();
        newQueue.inQueue(7);
        var len = newQueue.getSize();
        for(int i=0 ;i < len; i++){
            System.out.println(newQueue.deQueue());
        }
        System.out.println(newQueue.getSize());
    }
}