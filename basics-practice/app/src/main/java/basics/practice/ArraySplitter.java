package basics.practice;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ArraySplitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();
        
        int[] original = new int[size];
        for (int i = 0; i < size; i++) {
            original[i] = random.nextInt(201) - 100;
        }
        System.out.println("\nOriginal array: " + Arrays.toString(original));
        
        int evenCount = 0, oddCount = 0, positiveCount = 0, negativeCount = 0;
        
        for (int num : original) {
            if (num % 2 == 0) evenCount++;
            else oddCount++;
            
            if (num > 0) positiveCount++;
            else if (num < 0) negativeCount++;
        }
        
        int[] evenArray = new int[evenCount];
        int[] oddArray = new int[oddCount];
        int[] positiveArray = new int[positiveCount];
        int[] negativeArray = new int[negativeCount];
        int evenIndex = 0, oddIndex = 0, posIndex = 0, negIndex = 0;
        for (int num : original) {
            if (num % 2 == 0) {
                evenArray[evenIndex++] = num;
            } else {
                oddArray[oddIndex++] = num;
            }
            if (num > 0) {
                positiveArray[posIndex++] = num;
            } else if (num < 0) {
                negativeArray[negIndex++] = num;
            }
        }
        System.out.println("\n ");
        System.out.println("Even numbers (" + evenCount + "): " + Arrays.toString(evenArray));
        System.out.println("Odd numbers (" + oddCount + "): " + Arrays.toString(oddArray));
        System.out.println("Positive numbers (" + positiveCount + "): " + Arrays.toString(positiveArray));
        System.out.println("Negative numbers (" + negativeCount + "): " + Arrays.toString(negativeArray));
        
        scanner.close();
    }
}