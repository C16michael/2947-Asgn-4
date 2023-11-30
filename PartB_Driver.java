import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

//Cristiano Michael 3147571
public class PartB_Driver {
    public static void main(String[] args) {
        // Create a hash map to store postal codes.
        LinkedChainHashMap<String, PostalCode> map = new LinkedChainHashMap<>();

        // Read and store postal codes from the file.
        try {
            Scanner fileScanner = new Scanner(new File("PartB.txt"));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
        
                // Create a PostalCode object from the line data.
                PostalCode postalCode = new PostalCode(parts[0], parts[1], parts[2], 
                                                      Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
                // Store the postal code in the map with the code as the key.
                map.put(parts[0], postalCode);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Display the total number of entries and the number of collisions.
        System.out.println("Total Number of entries: " + map.size());
        System.out.println("Number of collisions: " + map.getCollisions());

        // Insert an entry with an existing key and print the old value.
        System.out.println("Old Postal Code replaced: " + map.put("T0A", new PostalCode("T0A", "New Area", "AB", 55.0, -112.0)));

        // Remove an entry where the key exists and print the removed value.
        System.out.println("Postal Code removed: " + map.remove("T0A"));

        // Display sorting options to the user.
        displaySortingOptions(map);
    }

    private static void displaySortingOptions(LinkedChainHashMap<String, PostalCode> map) {
        Scanner input = new Scanner(System.in);
        System.out.println("Display by code (C) or Longitude (L) (any other key to quit)");
        String choice = input.nextLine();

        Comparator<PostalCode> comparator;
        if (choice.equalsIgnoreCase("C")) {
            // Initialize comparator for sorting by code.
            comparator = new DefaultComparator<>();
            sortAndDisplay(map, comparator);
        } else if (choice.equalsIgnoreCase("L")) {
            // Initialize comparator for sorting by longitude.
            comparator = new OrderByLongitude();
            sortAndDisplay(map, comparator);
        } else {
            input.close();
        }
    }

    private static void sortAndDisplay(LinkedChainHashMap<String, PostalCode> map, Comparator<PostalCode> comp) {
        // Convert map values to an array for sorting.
        PostalCode[] postalCodes = new PostalCode[map.size()];
        int i = 0;
        for (PostalCode postalCode : map.values()) {
            postalCodes[i++] = postalCode;
        }

        // Sort the postal codes using QuickSort.
        QuickSort.quickSort(postalCodes, comp);

        // Display the sorted postal codes.
        for (PostalCode postalCode : postalCodes) {
            System.out.println(postalCode);
        }
    }
}
