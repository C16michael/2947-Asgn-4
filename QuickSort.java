import java.util.Comparator;
//Cristiano Michael 3147571
public class QuickSort {
    public static<K> void quickSort(Queue<K> S, Comparator<K> comp){
        int n = S.size();
        if (n < 2) return;

        K pivot = S.first();
        Queue<K> L = new LinkedQueue<>();
        Queue<K> E = new LinkedQueue<>();
        Queue<K> G = new LinkedQueue<>();
        
        while(!S.isEmpty()){
            K element = S.dequeue();
            int c = comp.compare(element, pivot);
            if(c<0)
                L.enqueue(element);
            else if(c==0)
                E.enqueue(element);
            else
                G.enqueue(element);
        }

        quickSort(L, comp);
        quickSort(G, comp);

        while(!L.isEmpty())
            S.enqueue(L.dequeue());
        while(!E.isEmpty())
            S.enqueue(E.dequeue());
        while(!G.isEmpty())
            S.enqueue(G.dequeue());
    }

    //Overload to use defaultComparator
    public static <K> void quickSort(Queue<K> S) {
        quickSort(S, new DefaultComparator<K>());
    }

    // Overload quickSort method for arrays
    public static <K> void quickSort(K[] S, Comparator<K> comp) {
        quickSort(S, 0, S.length - 1, comp);
    }

    private static <K> void quickSort(K[] S, int low, int high, Comparator<K> comp) {
        if (low < high) {
            int pivotIndex = partition(S, low, high, comp);
            quickSort(S, low, pivotIndex - 1, comp);
            quickSort(S, pivotIndex + 1, high, comp);
        }
    }

    private static <K> int partition(K[] S, int low, int high, Comparator<K> comp) {
        K pivot = S[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comp.compare(S[j], pivot) <= 0) {
                i++;
                K temp = S[i];
                S[i] = S[j];
                S[j] = temp;
            }
        }
        K temp = S[i + 1];
        S[i + 1] = S[high];
        S[high] = temp;
        return i + 1;
    }
}
