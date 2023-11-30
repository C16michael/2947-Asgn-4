public class OrderLettersByFrequency extends DefaultComparator<Entry<Character, Integer>> {
    @Override
    public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
        int freqCompare = o1.getValue().compareTo(o2.getValue());
        if (freqCompare != 0) {
            return freqCompare;
        }
        // Directly compare the keys as they are Comparable
        return o1.getKey().compareTo(o2.getKey());
    }
}
