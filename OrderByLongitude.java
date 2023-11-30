import java.util.Comparator;
//Cristiano Micahel 3147571
public class OrderByLongitude implements Comparator<PostalCode> {
    @Override
    public int compare(PostalCode p1, PostalCode p2) {
        return Double.compare(p1.getLongitude(), p2.getLongitude());
    }
}
