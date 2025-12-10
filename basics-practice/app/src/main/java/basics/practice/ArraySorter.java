package basics.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class ArraySorter {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();
        
        Integer[] array = new Integer[size]; 
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }
        
        System.out.println("\nOriginal: " + Arrays.toString(array));
        
        System.out.println("\nSort order:");
        System.out.println("1. Ascending ↑");
        System.out.println("2. Descending ↓");
        System.out.print("Choice: ");
        
        int choice = scanner.nextInt();
        
        if (choice == 1) {
            Arrays.sort(array); 
            System.out.println("\nAscending: " + Arrays.toString(array));
        } else if (choice == 2) {
            Arrays.sort(array, Comparator.reverseOrder()); 
            System.out.println("\nDescending: " + Arrays.toString(array));
        } else {
            System.out.println("Invalid choice!");
        }
        
        scanner.close();
    }
}