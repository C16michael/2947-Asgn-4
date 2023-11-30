import java.util.Comparator;
//Cristiano Michael 3147571
public class DefaultComparator<T> implements Comparator<T> {

    public int compare(T o1, T o2) throws ClassCastException{
        return ((Comparable<T>) o1).compareTo(o2);
    }
    
}
