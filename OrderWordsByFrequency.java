public class OrderWordsByFrequency extends DefaultComparator<Entry<String, Integer>> {
    @Override
    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
        int freqCompare = o1.getValue().compareTo(o2.getValue());
        if (freqCompare != 0) {
            return freqCompare;
        }
        // Directly compare the keys as they are Comparable
        return o1.getKey().compareTo(o2.getKey());
    }
}
