package basics.practice;

import java.util.Random;
import java.util.Scanner;

public class ArrayAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(201) - 100;
        }
        System.out.println("\nGenerated array:");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        
        int min = array[0];
        int max = array[0];
        int positiveCount = 0;
        int negativeCount = 0;
        int zeroCount = 0;
        
        for (int i = 0; i < size; i++) {
            if (array[i] < min) min = array[i];
            if (array[i] > max) max = array[i];
            if (array[i] > 0) {
                positiveCount++;
            } else if (array[i] < 0) {
                negativeCount++;
            } else {
                zeroCount++;
            }
        }
        System.out.print("\n");
        System.out.println("Minimum element: " + min);
        System.out.println("Maximum element: " + max);
        System.out.println("Positive numbers: " + positiveCount);
        System.out.println("Negative numbers: " + negativeCount);
        System.out.println("Zeros: " + zeroCount);
        
        scanner.close();
    }
}