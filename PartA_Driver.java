import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;
//Cristiano Michael 3147571

public class PartA_Driver {

    public static void main(String[] args) {
        // Create hash maps for words and characters
        ProbeHashMap<String, Integer> wordMap = new ProbeHashMap<>();
        ProbeHashMap<Character, Integer> charMap = new ProbeHashMap<>();

        try {
            // Set up a scanner to read from a file
            Scanner fileScanner = new Scanner(new File("PartA.txt"));
            // Use a delimiter to only read alphabetic characters (words)
            fileScanner.useDelimiter("[^a-zA-Z]+");

            // Loop through all words in the file
            while (fileScanner.hasNext()) {
                // Convert the word to lowercase and remove non-alphabetic characters
                String word = fileScanner.next().toLowerCase();
                word = word.replaceAll("[^a-zA-Z]+", "");

                // Update the freqeuncy of the word in the wordMap
                updateFrequency(wordMap, word);

                // Update the frequency of each character in the charMap
                for (char c : word.toCharArray()) {
                    updateFrequency(charMap, c);
                }
            }
            // Close the file scanner
            fileScanner.close();
        } catch (FileNotFoundException e) {
            // Handle the case where the file is not found
            e.printStackTrace();
        }

        // Display the results of the analysis
        displayResults(wordMap, charMap);
    }

    // Method to update the frequency of a key in a given map
    private static <K> void updateFrequency(ProbeHashMap<K, Integer> map, K key) {
        // Get the current count of the key in the map
        Integer count = map.get(key);
        // If the key is not present, initialize its count to 1
        if (count == null) {
            map.put(key, 1);
        } else {
            // Otherwise, increment the count by 1
            map.put(key, count + 1);
        }
    }

    // Method to find the entry with the maximum or least value in a map
    private static <K, V> Entry<K, V> findMaxLeast(Map<K, V> map, Comparator<Entry<K, V>> comp, boolean findMax) {
        // Create an array to hold map entries
        Entry<K, V>[] entries = (Entry<K, V>[]) new Entry[map.size()];
        int i = 0;
        // Populate the array with entries from the map
        for (Entry<K, V> entry : map.entrySet()) {
            entries[i++] = entry;
        }
        // Sort the entries using MergeSort
        MergeSort.MergeSort(entries, comp);
        // Retirn the last entry for maximum or the first entry for least
        return findMax ? entries[entries.length - 1] : entries[0];
    }

    // Method to find the entry with the maximum or least value in a map for a specific category
    private static <K, V> Entry<K, V> findCategoryMaxLeast(Map<K, V> map, ArrayList<K> categories, Comparator<Entry<K, V>> comp, boolean findMax) {
        // Create a list to hold filtered entries
        ArrayList<Entry<K, V>> filteredEntries = new ArrayList<>();
        // Filter entries based on the specified categories
        for (Entry<K, V> entry : map.entrySet()) {
            if (contains(categories, entry.getKey())) {
                filteredEntries.add(entry);
            }
        }
    
        // Convert the filtered list to an array
        Entry<K, V>[] entries = new Entry[filteredEntries.size()];
        for (int i = 0; i < filteredEntries.size(); i++) {
            entries[i] = filteredEntries.get(i);
        }
    
        // Sort the entries using MergeSort
        MergeSort.MergeSort(entries, comp);
        // Return the last entry for maximum or the first entry for least
        return findMax ? entries[entries.length - 1] : entries[0];
    }
    
    //Helper method to check if a list contains a specific element
    private static <E> boolean contains(ArrayList<E> list, E element) {
        for (int i = 0; i < list.size(); i++) {
            if (element.equals(list.get(i))) {
                return true;
            }
        }
        return false;
    }

    //Method to display the results of the text analysis
    private static void displayResults(ProbeHashMap<String, Integer> wordMap, ProbeHashMap<Character, Integer> charMap) {
        // Print out the analysis summary
        System.out.println("Text Analyzer");
        System.out.println("Total number of distinct words: " + wordMap.size());
        System.out.println("Total number of distinct letters: " + charMap.size());
    
        // Find and display the most and least frequent character along with their counts
        Entry<Character, Integer> mostFreqChar = findMaxLeast(charMap, new OrderLettersByFrequency(), true);
        Entry<Character, Integer> leastFreqChar = findMaxLeast(charMap, new OrderLettersByFrequency(), false);
        System.out.println("Most occurring character: " + mostFreqChar.getKey() + ", " + mostFreqChar.getValue());
        System.out.println("Least occurring character: " + leastFreqChar.getKey() + ", " + leastFreqChar.getValue());
    
        // Find and display the most and least frequent word along with their counts
        Entry<String, Integer> mostFreqWord = findMaxLeast(wordMap, new OrderWordsByFrequency(), true);
        Entry<String, Integer> leastFreqWord = findMaxLeast(wordMap, new OrderWordsByFrequency(), false);
        System.out.println("Most occurring word: " + mostFreqWord.getKey() + ", " + mostFreqWord.getValue());
        System.out.println("Least occurring word: " + leastFreqWord.getKey() + ", " + leastFreqWord.getValue());
    
        // Create a custom ArrayList for pronouns
        ArrayList<String> pronouns = new ArrayList<>();
        pronouns.add("he");
        pronouns.add("she");
        pronouns.add("it");
        pronouns.add("they");
        pronouns.add("you");
        pronouns.add("we");
        pronouns.add("i");
    
        // Find and display the most and least frequent pronoun along with their counts
        Entry<String, Integer> mostFreqPronoun = findCategoryMaxLeast(wordMap, pronouns, new OrderWordsByFrequency(), true);
        Entry<String, Integer> leastFreqPronoun = findCategoryMaxLeast(wordMap, pronouns, new OrderWordsByFrequency(), false);
        System.out.println("Most occurring pronoun: " + mostFreqPronoun.getKey() + ", " + mostFreqPronoun.getValue());
        System.out.println("Least occurring pronoun: " + leastFreqPronoun.getKey() + ", " + leastFreqPronoun.getValue());
    }
    
}
