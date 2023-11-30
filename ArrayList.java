//Cristiano Michael 3147571
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {
    public static final int CAPACITY = 16;
    private E[] data;
    private int size = 0;

    public ArrayList(){this(CAPACITY);} //No args Constructor
    public ArrayList(int capacity) { //Args Constructor
        data = (E[]) new Object[capacity];
    }

    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}
    
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    public void add(int i, E e) throws IndexOutOfBoundsException, IllegalStateException{
        checkIndex(i, size + 1);
        if(size == data.length)
            resize(data.length * 2);
        for(int k = size-1; k >= i; k--)
            data[k+1] = data[k];
        data[i] = e;
        size++;
    }

    public void add(E e) throws IllegalStateException{
        if (size == data.length)
            resize(data.length * 2);
        data[size] = e;
        size++;
    }

    public E remove(int i) throws IndexOutOfBoundsException{
        checkIndex(i, size);
        E temp = data[i];
        for(int k = i; k < size -1; k++)
            data[k] = data[k+1];
        data[size-1] = null;
        size--;
        if(size < data.length / 4 && data.length > CAPACITY)
            resize(data.length / 2);
        return temp;
    }
    
    private void resize(int capacity){
        E[] newData = (E[]) new Object[capacity];
        for(int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }

    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException{
        if(i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayList<?> other = (ArrayList<?>) o;
        if (size!= other.size) return false;
        for(int i = 0; i < size; i++)
            if(!data[i].equals(other.data[i]))
                return false;
        return true;
    }
    
   @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[currentIndex++];
            }
        };
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i].toString());
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }





}
