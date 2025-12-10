package basics.practice;

import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter start number: ");
        int start = scanner.nextInt();
        
        System.out.print("Enter end number: ");
        int end = scanner.nextInt();
        
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }
        
        System.out.println("\nMultiplication table from " + start + " to " + end + ":\n");
        
        for (int i = start; i <= end; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.printf("%d * %d = %d\t", i, j, i * j);
            }
            System.out.println();
        }
        
        scanner.close();
    }
}