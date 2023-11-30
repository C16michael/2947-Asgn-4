import java.util.ArrayList;
//Cristiano Michael 3147571
public class ProbeHashMap<K,V> extends AbstractHashMap<K,V>{
    private MapEntry<K,V>[] table; //fixed array of entries
    private MapEntry<K,V> DEFUNCT = new MapEntry<>(null,null);
    public ProbeHashMap(){super();}
    public ProbeHashMap(int cap){super(cap);}
    public ProbeHashMap(int cap, int p){super(cap,p);}
    //Empty tabling having equal length to capacity
    protected void createTable(){
        table = (MapEntry<K,V>[]) new MapEntry[capacity]; //Safe Cast
    }
    //Returns true if location is empty or defunct
    private boolean isAvailable(int j){
        return (table[j] == null || table[j] == DEFUNCT);
    }

    private int findSlot(int h, K k){
        int avail = -1; //Invalid
        int j = h; //Index 
        do{
            if(isAvailable(j)){
                if(avail == -1) avail = j; //First available slot
                if(table[j] == null) break; //If empty, break
            } else if (table[j].getKey().equals(k))
                return j;   //Match
            j = (j+1) % capacity; //Keep looking
        } while(j!=h);
        return -(avail + 1); 
    }
    //Returns value associoated with key k in a bucket with hash value h
    protected V bucketGet(int h, K k){
        int j = findSlot(h, k);
        if(j<0) return null;
        return table[j].getValue();
    }
    //Associates key with value in bucket with hash h
    protected V bucketPut(int h, K k, V v){
        int j = findSlot(h, k);
        if(j>=0) return table[j].setValue(v);
        table[-(j+1)] = new MapEntry<>(k,v);
        n++;
        return null;
    }

    //Removes entry having key k from bucket with hash value h
    protected V bucketRemove(int h, K k){
        int j = findSlot(h, k);
        if(j<0) return null;
        V answer = table[j].getValue();
        table[j] =  DEFUNCT;
        n--;
        return answer; 
    }
    //Removes an iterable collection of all key value entries of the ma
    public Iterable<Entry<K,V>> entrySet(){
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        for(int h = 0; h<capacity; h++)
            if(!isAvailable(h)) buffer.add(table[h]);
        return buffer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (Entry<K,V> entry : entrySet()) {
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        sb.append("}");
        return sb.toString();
    }
}