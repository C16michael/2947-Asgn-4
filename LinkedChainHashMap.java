import java.util.LinkedList;
//Cristiano Michael 3147571
public class LinkedChainHashMap<K, V> extends AbstractHashMap<K, V> {
    private LinkedList<MapEntry<K, V>>[] table;
    private int collisions = 0;

    public LinkedChainHashMap() { super(); }
    public LinkedChainHashMap(int cap) { super(cap); }
    public LinkedChainHashMap(int cap, int p) { super(cap, p); }

    protected void createTable() {
        table = (LinkedList<MapEntry<K, V>>[]) new LinkedList[capacity];
    }

    protected V bucketGet(int h, K k) {
        LinkedList<MapEntry<K, V>> bucket = table[h];
        if (bucket == null) return null;
        for (MapEntry<K, V> entry : bucket) {
            if (entry.getKey().equals(k)) return entry.getValue();
        }
        return null;
    }

    protected V bucketPut(int h, K k, V v) {
        LinkedList<MapEntry<K, V>> bucket = table[h];
        if (bucket == null) {
            bucket = table[h] = new LinkedList<>();
        }else{
        for (MapEntry<K, V> entry : bucket) {
            if (entry.getKey().equals(k)) {
                V oldValue = entry.getValue();
                entry.setValue(v);
                return oldValue;
            }
        }
            collisions++;
        }
        bucket.addLast(new MapEntry<>(k, v));
        n++; 
        return null;
    }

    protected V bucketRemove(int h, K k) {
        LinkedList<MapEntry<K, V>> bucket = table[h];
        if (bucket == null) return null;
        for (MapEntry<K, V> entry : bucket) {
            if (entry.getKey().equals(k)) {
                V value = entry.getValue();
                bucket.remove(entry);
                n--; // Decrement size
                return value;
            }
        }
        return null;
    }

    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++) {
            if (table[h] != null) {
                for (MapEntry<K, V> entry : table[h]) {
                    buffer.add(entry);
                }
            }
        }
        return buffer;
    }

    // Method to get the number of collisions
    public int getCollisions() {
        return collisions;
    }


    // Nested MapEntry class
    private static class MapEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
        public void setValue(V value) { this.value = value; }
    }
}
