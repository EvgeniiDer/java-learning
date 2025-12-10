package basics.practice;

import java.util.Scanner;

public class OddNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter first number: ");
        int a = scanner.nextInt();
        
        System.out.print("Enter second number: ");
        int b = scanner.nextInt();
        
        int start = Math.min(a, b);
        int end = Math.max(a, b);
        
        System.out.println("\nOdd numbers between " + start + " and " + end + ":");
        
        for (int i = start; i <= end; i++) {
            if (i % 2 != 0) { 
                System.out.print(i + " ");
            }
        }
        
        scanner.close();
    }
}