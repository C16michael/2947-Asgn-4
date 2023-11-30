import java.util.LinkedList;

//Cristiano Michael 3147571
public class LinkedQueue<E> implements Queue<E> {
    private LinkedList<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E element) {
        list.addLast(element);
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            return null;
        return list.removeFirst();
    }

    @Override
    public E first() {
        if (list.isEmpty())
            return null;
        return list.getFirst();
    }


}
